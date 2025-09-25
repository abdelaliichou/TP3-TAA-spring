package com.example.springtp.rest;

import com.example.springtp.dto.ClientDto;
import com.example.springtp.facade.IRun;
import com.example.springtp.service.ClientService;
import org.springframework.web.bind.annotation.*;
        import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientResource {

    private final ClientService service;

    private final IRun clientBean;

    public ClientResource(IRun clientBean, ClientService service) {
        this.service = service;
        this.clientBean = clientBean;
    }

    @PostMapping("/run-scenarios")
    public String runScenarios() {
        new Thread(clientBean).start(); // run scenarios asynchronously
        return "Scenarios triggered!";
    }

    // TODO use mapReduce for better mapping

    @GetMapping("/all")
    public List<ClientDto> getAll() {
        return service.getAll().stream().map(c -> {
            ClientDto dto = new ClientDto();
            dto.setName(c.getName());
            dto.setAddress(c.getAddress());
            dto.setBankAccount(c.getBankAccount());
            return dto;
        }).toList();
    }
}