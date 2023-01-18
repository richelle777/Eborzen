package org.isj.ing4.isi.music.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "titre")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Titre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_titre", nullable = false)
    private Integer id;

    @Column(name = "intitule", nullable = false)
    private String intitule;

    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_album", nullable = false)
    private Album idAlbum;

    @Column(name = "audio", nullable = false)
    private String audio;

    @Column(name = "duree", nullable = false)
    private String duree;

    @Lob
    @Column(name = "paroles", nullable = false)
    private String paroles;

    @Column(name = "video")
    private String video;

    @Column(name = "image")
    private String image;

    @Column(name = "prix")
    private String prix;
}