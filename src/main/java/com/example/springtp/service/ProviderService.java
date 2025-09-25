package com.example.springtp.service;

import com.example.springtp.domain.ArticleEntity;
import com.example.springtp.domain.ProviderEntity;
import com.example.springtp.facade.IProvider;
import com.example.springtp.repository.ArticleRepository;
import com.example.springtp.repository.ProviderRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProviderService implements IProvider {

    private final ArticleRepository articleRepo;
    private final ProviderRepository providerRepo;

    public ProviderService(ArticleRepository articleRepo, ProviderRepository providerRepo) {
        this.articleRepo = articleRepo;
        this.providerRepo = providerRepo;
    }

    public List<ProviderEntity> getAll() { return providerRepo.findAll(); }

    @Override
    public double getPrice(String articleRef) {
        ArticleEntity a = articleRepo.findByReference(articleRef);
        return a != null ? a.getPrice() : 0.0;
    }

    @Override
    public void order(String articleRef, int quantity) {
        ArticleEntity a = articleRepo.findByReference(articleRef);
        if(a != null){
            a.setStock(a.getStock() + quantity);
            articleRepo.save(a);
        }
    }
}