package com.example.springtp.mapper;

import com.example.springtp.dto.PlayerDto;
import com.example.springtp.domain.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    @Mapping(target = "quizzes", ignore = true)  // ignore quizzes when mapping player
    PlayerDto toDto(Player player);

    @Mapping(target = "quizzes", ignore = true)  // ignore quizzes when mapping player
    Player toEntity(PlayerDto dto);

    @Mapping(target = "quizzes", ignore = true)  // ignore quizzes when mapping player
    List<PlayerDto> toDtoList(List<Player> players);

    @Mapping(target = "quizzes", ignore = true)  // ignore quizzes when mapping player
    List<Player> toEntityList(List<PlayerDto> dtos);
}
