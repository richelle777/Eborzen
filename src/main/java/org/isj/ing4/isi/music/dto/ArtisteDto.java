package org.isj.ing4.isi.music.dto;

import org.isj.ing4.isi.music.annotation.CheckEmail;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class ArtisteDto {
    @Max(Integer.MAX_VALUE)
    private Integer id;
    @Size(max = 50)
    @NotBlank
    private String nom;
    @Size(max = 50)
    @NotBlank
    private String prenom;
    @Size(max = 50)
    @NotBlank
    private String surnom;
    @CheckEmail
    @Size(max = 50)
    @NotBlank
    private String email;
    @Size(max = 50)
    @NotBlank
    private String tel;
    @NotNull
    private byte[] profil;

    public ArtisteDto() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setSurnom(String surnom) {
        this.surnom = surnom;
    }

    public String getSurnom() {
        return this.surnom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel() {
        return this.tel;
    }

    public void setProfil(byte[] profil) {
        this.profil = profil;
    }

    public byte[] getProfil() {
        return this.profil;
    }
}