package com.example.springtp.mapper;

import com.example.springtp.domain.Participation;
import com.example.springtp.domain.Player;
import com.example.springtp.dto.ParticipationDto;
import com.example.springtp.dto.PlayerDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParticipationMapper {
    ParticipationMapper INSTANCE = Mappers.getMapper(ParticipationMapper.class);

    ParticipationDto toDto(Participation participation);

    Participation toEntity(ParticipationDto dto);

    List<ParticipationDto> toDtoList(List<Participation> participations);

    List<Participation> toEntityList(List<ParticipationDto> dtos);
}
