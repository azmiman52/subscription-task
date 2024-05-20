package id.ac.ui.cs.advprog.subscription.domains.models;

import id.ac.ui.cs.advprog.subscription.domains.entities.SubscriptionBox;
import id.ac.ui.cs.advprog.subscription.domains.enums.SubscriptionStatus;
import id.ac.ui.cs.advprog.subscription.domains.enums.SubscriptionType;

import java.util.List;

public class SubscriptionData {
    private String subscriptionID;
    private SubscriptionType subscriptionType;
    private SubscriptionStatus subscriptionStatus;
    private List<SubscriptionBox> subscriptionBox;
}