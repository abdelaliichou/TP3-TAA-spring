package com.example.springtp.rest;

import com.example.springtp.dto.ProviderDto;
import com.example.springtp.service.ProviderService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/provider")
public class ProviderResource {

    private final ProviderService service;

    public ProviderResource(ProviderService service) { this.service = service; }

    @GetMapping("/price/{ref}")
    public double getPrice(@PathVariable String ref) {
        return service.getPrice(ref);
    }

    @PostMapping("/order")
    public void order(@RequestParam String ref, @RequestParam int quantity) {
        service.order(ref, quantity);
    }

    // TODO use mapReduce for better mapping

    @GetMapping("/all")
    public List<ProviderDto> getAll() {
        return service.getAll().stream().map(p -> {
            ProviderDto dto = new ProviderDto();
            dto.setName(p.getName());
            dto.setCatalogue(p.getCatalogue());
            return dto;
        }).toList();
    }
}
