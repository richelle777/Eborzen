package org.isj.ing4.isi.music.mapper;

import org.isj.ing4.isi.music.dto.HistoriqueDto;
import org.isj.ing4.isi.music.model.Historique;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface HistoriqueMapper extends EntityMapper<HistoriqueDto, Historique> {
    void copy(HistoriqueDto historiqueDto, @MappingTarget Historique historique);
}
