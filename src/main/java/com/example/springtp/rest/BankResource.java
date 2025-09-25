package com.example.springtp.rest;

import com.example.springtp.domain.BankEntity;
import com.example.springtp.dto.BankDto;
import com.example.springtp.service.BankService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bank")
public class BankResource {

    private final BankService service;

    public BankResource(BankService service) { this.service = service; }

    @GetMapping("/balance/{account}")
    public double getBalance(@PathVariable String account) {
        // BankEntity acc = service.getAccount(account);
        // return acc != null ? acc.getBalance() : 0.0;
        return 0.0;
    }

    @PostMapping("/transfer")
    public boolean transfer(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam double amount
    ) {
        return service.transfert(from, to, amount);
    }

    // TODO use mapReduce for better mapping

    @GetMapping("/all")
    public List<BankDto> getAll() {
        return service.getAll().stream().map(b -> {
            BankDto dto = new BankDto();
            dto.setAccountNumber(b.getAccountNumber());
            dto.setBalance(b.getBalance());
            return dto;
        }).toList();
    }
}
