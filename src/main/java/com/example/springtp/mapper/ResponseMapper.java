package com.example.springtp.mapper;

import com.example.springtp.domain.Player;
import com.example.springtp.domain.Response;
import com.example.springtp.dto.PlayerDto;
import com.example.springtp.dto.ResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResponseMapper {
    ResponseMapper INSTANCE = Mappers.getMapper(ResponseMapper.class);

    ResponseDto toDto(Response response);

    Response toEntity(ResponseDto dto);

    List<ResponseDto> toDtoList(List<Response> responses);

    List<Response> toEntityList(List<ResponseDto> dtos);
}
