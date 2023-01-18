package org.isj.ing4.isi.music.presentation.controller;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.io.resource.Resource;
import com.stripe.exception.StripeException;
import com.stripe.model.File;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.isj.ing4.isi.music.commons.Response;
import org.isj.ing4.isi.music.dto.CreatePayment;

import org.isj.ing4.isi.music.dto.TitreDto;
import org.isj.ing4.isi.music.exception.IsjException;
import org.isj.ing4.isi.music.model.Titre;
import org.isj.ing4.isi.music.service.StripeService;
import org.isj.ing4.isi.music.service.TitreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;

@Controller
public class PaymentController {

    @Value("${stripe.keys.public}")
    private String API_PUBLIC_KEY;
    @Autowired
    private StripeService stripeService;
    @Autowired
    private TitreService titreService;

/*    @PostMapping("/create-payment-intent")
    public CreatePaymentResponse createPaymentIntent(@RequestBody @Valid CreatePayment createPayment)throws StripeException {
        PaymentIntentCreateParams createParams = new
                PaymentIntentCreateParams.Builder()
                .setCurrency("usd")
                .putMetadata("featureRequest", createPayment.getFeatureRequest())
                .setAmount(createPayment.getAmount() * 100L)
                .build();

        PaymentIntent intent = PaymentIntent.create(createParams);
        return new CreatePaymentResponse(intent.getClientSecret());
    }*/

    @GetMapping("/paiement/{idt}")
    public String chargePage(Model model, @PathVariable("idt") int id) throws IsjException {
        TitreDto titre = titreService.findById(id);
        model.addAttribute("titre", titre);
        model.addAttribute("stripePublicKey", API_PUBLIC_KEY);
        return "charge";
    }

    @GetMapping("/paiementparole/{idt}")
    public String chargePageParole(Model model, @PathVariable("idt") int id) throws IsjException {
        TitreDto titre = titreService.findById(id);
        model.addAttribute("titre", titre);
        model.addAttribute("stripePublicKey", API_PUBLIC_KEY);
        return "chargeparole";
    }

    @PostMapping("/create-charge")
    public @ResponseBody
    Response createCharge(String email, String token, int amount, int idt, HttpServletResponse response) throws IsjException, IOException {
        TitreDto titre = titreService.findById(idt);
        //validate data
        System.out.println(amount);
        if (token == null) {
            return new Response(false, "Stripe payment token is missing. Please, try again later.");
        }

        //create charge
        String chargeId = stripeService.createCharge(email, token, amount*100); //$9.99 USD
        if (chargeId == null) {
            return new Response(false, "An error occurred while trying to create a charge.");
        }



        // You may want to store the charge id along with order information

        return new Response(true, "Votre paiement a été effectué avec succes");
    }

    @GetMapping("/print/{id}")
    public void downloadFile(@PathVariable("id") int idt, HttpServletResponse response) throws IsjException, IOException {
        TitreDto titre = titreService.findById(idt);
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "inline; filename = "+titre.getAudio();
        response.setHeader(headerKey, headerValue);
        ServletOutputStream outputStream = response.getOutputStream();
        //File file = new File(getClass().getResource("jsonschema.json").getFile());
        Resource resource = new ClassPathResource("classpath:static"+titre.getAudio());
        response.setContentType("application/octet-stream");
        outputStream.write(resource.readBytes());
        outputStream.close();
        /*File file = new File();
        file.setUrl("D:/ISI/IS4/gestion de projet/Gestion_Projet2/gestion-de-projet/src/main/resources/static/musique/"+ titre.getAudio());
        String mimeType = URLConnection.guessContentTypeFromName(file.getFilename());
        response.setContentType(mimeType);

        System.out.println(resource.readBytes());
        response.setContentLength(file.getSize().intValue());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file.getUrl()));

        FileCopyUtils.copy(inputStream, response.getOutputStream());*/
    }

    @GetMapping("/printparole/{id}")
    public void downloadParoles(@PathVariable("id") int idt, HttpServletResponse response) throws IsjException, IOException {
        TitreDto titre = titreService.findById(idt);
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "inline; filename = "+titre.getParoles();
        response.setHeader(headerKey, headerValue);
        ServletOutputStream outputStream = response.getOutputStream();
        //File file = new File(getClass().getResource("jsonschema.json").getFile());
        Resource resource = new ClassPathResource("classpath:static"+titre.getParoles());
        response.setContentType("application/octet-stream");
        outputStream.write(resource.readBytes());
        outputStream.close();
        /*File file = new File();
        file.setUrl("D:/ISI/IS4/gestion de projet/Gestion_Projet2/gestion-de-projet/src/main/resources/static/musique/"+ titre.getAudio());
        String mimeType = URLConnection.guessContentTypeFromName(file.getFilename());
        response.setContentType(mimeType);

        System.out.println(resource.readBytes());
        response.setContentLength(file.getSize().intValue());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file.getUrl()));

        FileCopyUtils.copy(inputStream, response.getOutputStream());*/
    }

}
