package id.ac.ui.cs.advprog.subscription.model;

import id.ac.ui.cs.advprog.subscription.model.SubscriptionState;
import id.ac.ui.cs.advprog.subscription.model.Subscription;

import java.util.concurrent.Flow;

public class PendingState implements SubscriptionState {
    private final Subscription subscription;

    public PendingState(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void activateSubscription() {
        subscription.setSubscriptionState(new ActivateState(subscription));
    }

    @Override
    public void cancelSubscription() {
        subscription.setSubscriptionState(new CancelState(subscription));
    }
}