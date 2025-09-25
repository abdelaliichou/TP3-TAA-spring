package com.example.springtp.rest;

import com.example.springtp.dto.StoreDto;
import com.example.springtp.service.StoreService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreResource {

    private final StoreService service;

    public StoreResource(StoreService service) { this.service = service; }

    @GetMapping("/price/{ref}")
    public double getPrice(@PathVariable String ref) {
        return service.getPrice(ref);
    }

    @GetMapping("/availability/{ref}/{quantity}")
    public boolean isAvailable(@PathVariable String ref, @PathVariable int quantity) {
        return service.isAvailable(ref, quantity);
    }

    // TODO use mapReduce for better mapping

    @GetMapping("/stock")
    public List<StoreDto> getStock() {
        return service.getAll().stream().map(a -> {
            StoreDto dto = new StoreDto();
            dto.setName(a.getName());
            dto.setStock(a.getStock());
            return dto;
        }).toList();
    }
}
