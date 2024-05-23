package id.ac.ui.cs.advprog.subscription.domains.models;

import id.ac.ui.cs.advprog.subscription.domains.entities.SubscriptionBox;
import id.ac.ui.cs.advprog.subscription.domains.enums.SubscriptionStatus;
import id.ac.ui.cs.advprog.subscription.domains.enums.SubscriptionType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class SubscriptionData {
    private SubscriptionType type;
    private SubscriptionStatus status;
    private List<SubscriptionBox> subscriptionBox;
}
