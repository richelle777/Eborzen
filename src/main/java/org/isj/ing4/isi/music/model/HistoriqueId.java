package org.isj.ing4.isi.music.model;

import lombok.Data;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class HistoriqueId implements Serializable {
    private static final long serialVersionUID = 7554052092920665141L;
    @Column(name = "id_titre", nullable = false)
    private Integer idTitre;
    @Column(name = "id_user", nullable = false)
    private Integer idUser;


}