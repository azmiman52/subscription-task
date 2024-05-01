package id.ac.ui.cs.advprog.subscription.repository;
import id.ac.ui.cs.advprog.subscription.model.Subscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, String> {
    static int id = 0;

    List<Subscription> subscriptionData = new ArrayList<>();

    public default Iterator<Subscription> findAllSubscription() {
        return subscriptionData.iterator();
    }

    Optional<Subscription> findSubscriptionById(String subscriptionId);
}