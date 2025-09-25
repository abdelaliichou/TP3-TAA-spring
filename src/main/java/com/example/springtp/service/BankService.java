package com.example.springtp.service;

import com.example.springtp.domain.BankEntity;
import com.example.springtp.facade.IBank;
import com.example.springtp.repository.BankRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BankService implements IBank {

    private final BankRepository repo;

    public BankService(BankRepository repo) { this.repo = repo; }

    public List<BankEntity> getAll() { return repo.findAll(); }

    @Override
    public synchronized boolean transfert(String fromAccount, String toAccount, double amount){
        BankEntity from = repo.findById(fromAccount).orElse(null);
        BankEntity to = repo.findById(toAccount).orElse(null);
        if(from == null || to == null || from.getBalance() < amount) return false;

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);
        repo.save(from); repo.save(to);
        return true;
    }
}