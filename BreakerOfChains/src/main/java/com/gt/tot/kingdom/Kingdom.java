package com.gt.tot.kingdom;

import com.gt.tot.ballot.Ballot;
import com.gt.tot.message.Message;
import com.gt.tot.message.SecretsRepository;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class Kingdom {

    protected String emblem;
    protected String name;
    protected String rulerName;

    protected Ballot holyBallot = Ballot.holyBallot();
    protected boolean isCompeting = false;
    protected boolean canGiveAllegiance = true;

    private Set<Kingdom> allies = new LinkedHashSet<>();
    private SecretsRepository secretsRepository;

    public String rulerName() {
        return rulerName;
    }

    public String getName() {
        return name;
    }

    public String alliesNames() {
        if (allies.size() <= 0)
            return "None";
        return allies
                .stream()
                .map(kingdom -> kingdom.name)
                .collect(Collectors.joining(","));
    }

    public boolean isCompeting() {
        return isCompeting;
    }

    public boolean canGiveAlligiance() {
        return canGiveAllegiance;
    }

    private void resetAllies() {
        allies.clear();
    }

    public void enterCompetition() {
        this.isCompeting = true;
        this.canGiveAllegiance = false;
        resetAllies();
    }

    public void resetState() {
        this.isCompeting = false;
        this.canGiveAllegiance = true;
        resetAllies();
    }

    public void accessSecretRepoAt(SecretsRepository repository) {
        this.secretsRepository = repository;
    }

    public void sendMessagesToKingdoms(Set<Kingdom> kingdoms) {
        if (this.isCompeting)
            kingdoms.stream()
                    .filter(otherThanSelf())
                    .forEach(this::sendMessage);
        else
            throw new RuntimeException("Can not send message without entering into competition");
    }

    private Predicate<Kingdom> otherThanSelf() {
        return kingdom -> !this.emblem.equals(kingdom.emblem);
    }

    private void sendMessage(Kingdom kingdom) {
        this.dropMessageForCompetition(new Message(this, kingdom, secretsRepository.secretMessage()));
    }

    private void dropMessageForCompetition(Message message) {
        holyBallot.addMessage(message);
    }

    public void receiveAllegianceMessageFrom(Kingdom sender, String secret) {
        if (canGiveAllegianceToSenderFor(secret)) {
            this.registerAsAnAllyTo(sender);
            this.canGiveAllegiance = false;
        }
    }

    private boolean canGiveAllegianceToSenderFor(String secret) {
        return !this.isCompeting && this.canGiveAllegiance && this.canDecode(secret);
    }

    private boolean canDecode(String secreteMessage) {
        String[] emblemLetters = lettersOf(emblem);
        String[] secreteLetters = lettersOf(secreteMessage);

        Map<String, Integer> emblemCounts = letterCountsOf(emblemLetters);
        Map<String, Integer> secreteCounts = letterCountsOf(secreteLetters);

        Set<String> emblemKeys = emblemCounts.keySet();
        for (String em : emblemKeys) {
            if (secreteCounts.getOrDefault(em, 0) < emblemCounts.get(em))
                return false;
        }
        return true;
    }

    private String[] lettersOf(String emblem) {
        return emblem.toLowerCase().split("");
    }

    private Map<String, Integer> letterCountsOf(String[] letters) {
        Map<String, Integer> counts = new HashMap<>();
        for (String letter : letters) {
            if (counts.computeIfPresent(letter, (k, v) -> v + 1) == null) {
                counts.put(letter, 1);
            }
        }
        return counts;
    }

    private void registerAsAnAllyTo(Kingdom kingdom) {
        kingdom.allies.add(this);
    }

    public int alliesCount() {
        return allies.size();
    }
}
