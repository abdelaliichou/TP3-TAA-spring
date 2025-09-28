package com.example.springtp.mapper;

import com.example.springtp.dto.PlayerDto;
import com.example.springtp.domain.Player;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    PlayerDto toDto(Player player);

    Player toEntity(PlayerDto dto);

    List<PlayerDto> toDtoList(List<Player> players);

    List<Player> toEntityList(List<PlayerDto> dtos);
}
