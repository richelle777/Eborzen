package org.isj.ing4.isi.music.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class HistoriqueDto implements Serializable {
    private final TitreDto idMusique;
    private final UserDto idUser;
}
