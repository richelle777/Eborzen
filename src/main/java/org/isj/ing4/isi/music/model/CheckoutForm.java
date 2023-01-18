package org.isj.ing4.isi.music.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CheckoutForm {

    @NotNull
    @Min(4)
    private Integer amount;

    @NotNull
    @Size(min = 5,max = 200)
    private String featureRequest;

}
