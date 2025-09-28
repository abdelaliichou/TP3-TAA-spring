package com.example.springtp.mapper;

import com.example.springtp.domain.Player;
import com.example.springtp.domain.Question;
import com.example.springtp.dto.PlayerDto;
import com.example.springtp.dto.QuestionDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    QuestionDto toDto(Question question);

    Question toEntity(QuestionDto dto);

    List<QuestionDto> toDtoList(List<Question> questions);

    List<Question> toEntityList(List<QuestionDto> dtos);
}
