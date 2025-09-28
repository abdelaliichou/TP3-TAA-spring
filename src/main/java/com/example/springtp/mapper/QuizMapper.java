package com.example.springtp.mapper;

import com.example.springtp.domain.Player;
import com.example.springtp.domain.Quiz;
import com.example.springtp.dto.PlayerDto;
import com.example.springtp.dto.QuizDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuizMapper {
    QuizMapper INSTANCE = Mappers.getMapper(QuizMapper.class);

    QuizDto toDto(Quiz quiz);

    Quiz toEntity(QuizDto dto);

    List<QuizDto> toDtoList(List<Quiz> quizzes);

    List<Quiz> toEntityList(List<QuizDto> dtos);
}