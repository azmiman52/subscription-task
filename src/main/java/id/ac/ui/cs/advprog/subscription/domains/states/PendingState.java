package id.ac.ui.cs.advprog.subscription.domains.states;import id.ac.ui.cs.advprog.subscription.domains.entities.Subscription;public class PendingState implements SubscriptionState {    private final Subscription subscription;    public PendingState(Subscription subscription) {        this.subscription = subscription;    }    @Override    public void activateSubscription() {        subscription.setSubscriptionState(new ActivateState(subscription));    }    @Override    public void cancelSubscription() {        subscription.setSubscriptionState(new CancelState(subscription));    }}