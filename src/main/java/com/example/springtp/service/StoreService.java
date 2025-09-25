package com.example.springtp.service;

import com.example.springtp.domain.ArticleEntity;
import com.example.springtp.domain.StoreEntity;
import com.example.springtp.facade.IFastLane;
import com.example.springtp.facade.IJustHaveALook;
import com.example.springtp.facade.ILane;
import com.example.springtp.repository.ArticleRepository;
import com.example.springtp.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService implements IJustHaveALook, IFastLane, ILane {

    private final StoreRepository storeRepo;
    private final ArticleRepository articleRepo;
    private final BankService bankService;
    private final ProviderService providerService;
    private final List<CartItem> cart = new ArrayList<>();
    private static final String STORE_ACCOUNT = "STORE-ACCOUNT";

    public StoreService(
            StoreRepository storeRepo,
            ArticleRepository articleRepo,
            BankService bankService,
            ProviderService providerService
    ) {
        this.storeRepo = storeRepo;
        this.articleRepo = articleRepo;
        this.bankService = bankService;
        this.providerService = providerService;
    }

    public List<StoreEntity> getAll() { return storeRepo.findAll(); }

    @Override
    public void oneShotOrder(
            String articleRef,
            int quantity,
            String clientAccount,
            String clientAddress
    ) {
        ArticleEntity a = articleRepo.findByReference(articleRef);
        if(a == null || a.getStock() < quantity){
            providerService.order(articleRef, Math.max(quantity, 10));
            a = articleRepo.findByReference(articleRef);
        }
        double amount = a.getPrice() * quantity;
        boolean paid = bankService.transfert(clientAccount, STORE_ACCOUNT, amount);
        if(paid){
            a.setStock(a.getStock() - quantity);
            articleRepo.save(a);
        }
    }

    @Override
    public double getPrice(String articleRef) {
        return providerService.getPrice(articleRef);
    }

    @Override
    public boolean isAvailable(String articleRef, int quantity) {
        ArticleEntity a = articleRepo.findByReference(articleRef);
        return a != null && a.getStock() >= quantity;
    }

    @Override
    public void addItemToCart(String articleRef, int quantity) {
        cart.add(new CartItem(articleRef, quantity));
    }

    @Override
    public void pay(String clientAccount, String clientAddress) {
        for(CartItem ci: cart){
            oneShotOrder(ci.articleRef, ci.quantity, clientAccount, clientAddress);
        }
        cart.clear();

    }

    private static class CartItem{
        String articleRef;
        int quantity;
        CartItem(String ref,int q) {
        articleRef=ref;quantity=q;
        }
    }
}