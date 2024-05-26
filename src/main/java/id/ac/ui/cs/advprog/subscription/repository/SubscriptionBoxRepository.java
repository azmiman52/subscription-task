package id.ac.ui.cs.advprog.subscription.repository;

import id.ac.ui.cs.advprog.subscription.domains.entities.SubscriptionBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionBoxRepository extends JpaRepository<SubscriptionBox, String> {
}
