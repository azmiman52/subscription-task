package id.ac.ui.cs.advprog.subscription.service.implementations;import id.ac.ui.cs.advprog.subscription.domains.entities.Subscription;import id.ac.ui.cs.advprog.subscription.domains.enums.SubscriptionStatus;import id.ac.ui.cs.advprog.subscription.domains.models.SubscriptionData;import id.ac.ui.cs.advprog.subscription.domains.models.responses.ResponseData;import id.ac.ui.cs.advprog.subscription.domains.states.PendingState;import id.ac.ui.cs.advprog.subscription.repository.SubscriptionRepository;import id.ac.ui.cs.advprog.subscription.service.interfaces.SubscriptionService;import lombok.RequiredArgsConstructor;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.stereotype.Service;import org.springframework.web.client.RestTemplate;import org.springframework.web.server.ResponseStatusException;import java.time.LocalDate;import java.time.ZoneId;import java.util.*;import java.util.logging.Logger;@Service@RequiredArgsConstructorpublic class SubscriptionServiceImpl implements SubscriptionService {    private static final Logger logger = Logger.getLogger(SubscriptionService.class.getName());    private final RestTemplate restTemplate;    private final SubscriptionRepository subscriptionRepository;    @Override    public ResponseEntity<List<Subscription>> getSubscriptions(){        try {            return new ResponseEntity<>(subscriptionRepository.findAll(), HttpStatus.OK);        }        catch (ResponseStatusException e) {            throw new ResponseStatusException(e.getStatusCode(), e.getReason());        }    }    @Override    public ResponseEntity<Subscription> getSubscriptionById(String subscription_id) {        try {            Optional<Subscription> subscriptionOptional = subscriptionRepository.findById(subscription_id);            if(subscriptionOptional.isEmpty()){                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscription with ID: " + subscription_id + " not found");            }            return new ResponseEntity<>(subscriptionOptional.get(), HttpStatus.OK);        } catch (ResponseStatusException e) {            throw new ResponseStatusException(e.getStatusCode(), e.getReason());        }    }    @Override    public ResponseEntity<Subscription> createSubscription(SubscriptionData subscriptionData) {        try {            UUID uuid = UUID.randomUUID();            Subscription subscription = new Subscription();            int subs_month = switch (subscriptionData.getType()) {                case MONTHLY -> {                    subscription.setId("MTH-" + uuid);                    yield 1;                }                case QUARTERLY -> {                    subscription.setId("QTR-" + uuid);                    yield 4;                }                case SEMI_ANNUAL -> {                    subscription.setId("SAA-" + uuid);                    yield 6;                }            };            subscription.setType(subscriptionData.getType());            subscription.setStartDate(LocalDate.now(ZoneId.of("Asia/Jakarta")));            subscription.setEndDate(subscription.getStartDate().plusMonths(subs_month));            subscription.setSubscriptionBox(subscriptionData.getSubscriptionBox());            subscription.setSubscriptionState(new PendingState(subscription));            subscriptionRepository.save(subscription);            return new ResponseEntity<>(subscription, HttpStatus.OK);        } catch (ResponseStatusException e) {            throw new ResponseStatusException(e.getStatusCode(), e.getReason());        }    }    @Override    public ResponseEntity<ResponseData<Subscription>> updateSubscription(String subscription_id, SubscriptionData subscriptionData) {        try {            Optional<Subscription> subscriptionOptional = subscriptionRepository.findById(subscription_id);            if(subscriptionOptional.isEmpty()){                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscription with ID: " + subscription_id + " not found");            }            Subscription subscription = subscriptionOptional.get();            String subs_id = subscription_id.substring(4);            int subs_month = switch (subscriptionData.getType()) {                case MONTHLY -> {                    subscription.setId("MTH-" + subs_id);                    yield 1;                }                case QUARTERLY -> {                    subscription.setId("QTR-" + subs_id);                    yield 4;                }                case SEMI_ANNUAL -> {                    subscription.setId("SAA-" + subs_id);                    yield 6;                }            };            subscription.setEndDate(subscription.getStartDate().plusMonths(subs_month));            subscriptionRepository.save(subscription);            return new ResponseEntity<>(new ResponseData<>(subscriptionOptional.get(), "Subscription updated successfully!"), HttpStatus.OK);        } catch (ResponseStatusException e) {            throw new ResponseStatusException(e.getStatusCode(), e.getReason());        }    }    @Override    public ResponseEntity<String> deleteSubscriptionById(String subscription_id) {        try {            Optional<Subscription> subscriptionOptional = subscriptionRepository.findById(subscription_id);            if(subscriptionOptional.isEmpty()){                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscription with ID: " + subscription_id + " not found");            }            subscriptionRepository.deleteById(subscription_id);            return new ResponseEntity<>("Deleted Subscription with ID " + subscription_id, HttpStatus.OK);        } catch (ResponseStatusException e) {            throw new ResponseStatusException(e.getStatusCode(), e.getReason());        }    }    @Override    public ResponseEntity<Subscription> cancelSubscriptionById(String subscription_id) {        try {            Optional<Subscription> subscriptionOptional = subscriptionRepository.findById(subscription_id);            if(subscriptionOptional.isEmpty()){                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscription with ID: " + subscription_id + " not found");            }            Subscription subscription = subscriptionOptional.get();            subscription.cancelSubscription();            subscriptionRepository.save(subscription);            return new ResponseEntity<>(subscription, HttpStatus.OK);        } catch (ResponseStatusException e) {            throw new ResponseStatusException(e.getStatusCode(), e.getReason());        }    }    public void updateSubscriptionStatus(){        try {            LocalDate now = LocalDate.now(ZoneId.of("Asia/Jakarta"));            List<Subscription> subscriptions = subscriptionRepository.findAll().stream()                    .filter(subscription -> subscription.getEndDate().isBefore(now))                    .toList();            for (Subscription subscription : subscriptions) {                subscription.setStatus(SubscriptionStatus.FINISHED);                subscriptionRepository.save(subscription);            }        }        catch (ResponseStatusException e) {            throw new ResponseStatusException(e.getStatusCode(), e.getReason());        }    }}