package com.gt.tot.kingdom;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Kingdom {

    protected String emblem;
    protected String name;
    protected String rulerName;
    protected Set<Kingdom> allies;

    public String rulerName() {
        return rulerName;
    }

    public String emblemName() {
        return emblem;
    }

    public Set<Kingdom> getAllies() {
        return allies;
    }

    public String alliesNames() {
        if (allies.size() <= 0)
            return "None";
        return allies
                .stream()
                .map(kingdom -> kingdom.name)
                .collect(Collectors.joining(","));
    }

    public void resetAllies() {
        allies.clear();
    }

    public void sendSecretMessageAndRequestAlliance(Kingdom kingdom, String secreteMessage) {
        if (kingdom.emblem.equals(this.emblem))
            return;

        if (kingdom.canDecode(secreteMessage))
            kingdom.registerAsAnAllyTo(this);
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

    private void registerAsAnAllyTo(Kingdom kingdom) {
        kingdom.allies.add(this);
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
}
