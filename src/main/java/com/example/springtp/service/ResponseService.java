package com.example.springtp.service;


import com.example.springtp.domain.Question;
import com.example.springtp.domain.Response;
import com.example.springtp.dto.ResponseDto;
import com.example.springtp.mapper.ResponseMapper;
import com.example.springtp.repository.ResponseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponseService {

    // TODO same communication logic with the dao classes as we did the playerService class
    // TODO apply mapper from Question to QuestionDto as we did the playerService class

    private final ResponseRepository responseRepository;
    private final ResponseMapper responseMapper;

    public ResponseService(ResponseRepository responseDao, ResponseMapper responseMapper) {
        this.responseRepository = responseDao;
        this.responseMapper = responseMapper;
    }

    public ResponseDto findOne(Long id) {
        if (id == null) {
            return null;
        }

        Optional<Response> optionalResponse = responseRepository.findById(id);
        return optionalResponse.map(responseMapper::toDto).orElse(null);
    }

    public List<Response> findAll() {
        return responseRepository.findAll();
    }

    public void save(Response entity) {
        if (entity == null) {
            return;
        }

        responseRepository.save(entity);
    }

    public void delete(Response entity) {
        if (entity == null) {
            return;
        }

        responseRepository.delete(entity);
    }

    public void deleteById(Long entityId) {
        if (entityId == null) {
            return;
        }

        responseRepository.deleteById(entityId);
    }

    public Response update(Response entity) {
        return null;
    }

    public List<Response> findByQuestion(Long questionId) {
        return List.of();
    }

    public List<Response> findCorrectResponsesByQuestion(Long questionId) {
        return List.of();
    }
}
