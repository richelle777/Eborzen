package org.isj.ing4.isi.music.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "artiste")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artiste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artiste", nullable = false)
    private Integer id;

    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 50)
    private String prenom;

    @Column(name = "surnom", nullable = false, length = 50)
    private String surnom;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "tel", nullable = false, length = 50)
    private String tel;

    @Column(name = "profil", nullable = false)
    private byte[] profil;

}