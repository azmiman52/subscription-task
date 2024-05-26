package id.ac.ui.cs.advprog.subscription.service.implementations;

import id.ac.ui.cs.advprog.subscription.domains.entities.SubscriptionBox;
import id.ac.ui.cs.advprog.subscription.repository.SubscriptionBoxRepository;
import id.ac.ui.cs.advprog.subscription.repository.SubscriptionRepository;
import id.ac.ui.cs.advprog.subscription.service.interfaces.SubscriptionBoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionBoxServiceImpl implements SubscriptionBoxService {
    SubscriptionBoxRepository subscriptionBoxRepository;

    @Override
    public ResponseEntity<List<SubscriptionBox>> getAllBoxes() {
        return null;
    }

    @Override
    public ResponseEntity<List<SubscriptionBox>> getAvailableBoxes() {
        return null;
    }

    @Override
    public ResponseEntity<SubscriptionBox> createBox(SubscriptionBox subscriptionBox){
        subscriptionBoxRepository.save(subscriptionBox);
        return new ResponseEntity<>(subscriptionBox, HttpStatus.OK);
    }
}
