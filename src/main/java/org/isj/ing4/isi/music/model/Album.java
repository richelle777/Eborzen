package org.isj.ing4.isi.music.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "album")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_album", nullable = false)
    private Integer id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "producteur", nullable = false)
    private String producteur;


    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "image", nullable = false)
    private byte[] image;

}