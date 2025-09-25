package com.example.springtp.service;


import com.example.springtp.domain.ClientEntity;
import com.example.springtp.facade.IClient;
import com.example.springtp.facade.IFastLane;
import com.example.springtp.facade.ILane;
import com.example.springtp.facade.IRun;
import com.example.springtp.repository.ClientRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientService implements IRun, IClient {

    private final IFastLane fastLane;
    private final ILane lane;
    private final ClientRepository clientRepo;

    public ClientService(
            ClientRepository clientRepo,
            IFastLane fastLane,
            ILane lane
    ) {
        this.clientRepo = clientRepo;
        this.lane = lane;
        this.fastLane = fastLane;
    }

    public List<ClientEntity> getAll() { return clientRepo.findAll(); }

    @Override
    public void run() {
        // Scenario 1
        fastLane.oneShotOrder(
                "A1",
                2,
                "CLIENT-0001",
                "123 Main St"
        );
        // Scenario 2
        lane.addItemToCart("A2", 1);
        lane.addItemToCart("A3", 3);
        lane.pay("CLIENT-0001", "123 Main St");
    }
}