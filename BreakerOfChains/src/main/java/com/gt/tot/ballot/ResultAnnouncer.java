package com.gt.tot.ballot;

import com.gt.tot.kingdom.Kingdom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultAnnouncer {
    private final List<Kingdom> competingKingdoms;

    private ResultAnnouncer(List<Kingdom> competingKingdoms) {
        this.competingKingdoms = competingKingdoms;
    }

    public static ResultAnnouncer forKingdoms(List<Kingdom> competingKingdoms) {
        return new ResultAnnouncer(competingKingdoms);
    }

    public Map<String, String> announce() {
        Map<String, String> results = new HashMap<>();
        for (Kingdom kingdom : competingKingdoms) {
            results.put(kingdom.getName(),
                    "Allies for " + kingdom.getName() + ": " + kingdom.alliesCount());
        }
        results.put("result", result());
        return results;

    }

    private String result() {
        competingKingdoms.sort((k1, k2) -> k2.alliesCount() - k1.alliesCount());
        if (competingKingdoms.size() >= 2) {
            int first = competingKingdoms.get(0).alliesCount();
            int second = competingKingdoms.get(1).alliesCount();
            if (first == second)
                return "tie";
        }
        return competingKingdoms.get(0).getName();
    }

    public List<Kingdom> tiedKingdoms() {
        List<Kingdom> tiedKingdomsList = new ArrayList<>();
        if ("tie".equals(result())) {
            tiedKingdomsList.add(competingKingdoms.get(0));
            int highestAlliesCount = competingKingdoms.get(0).alliesCount();
            if (competingKingdoms.size() > 1) {
                for (int i = 1; i < competingKingdoms.size(); i++) {
                    int alliesCount = competingKingdoms.get(i).alliesCount();
                    if (highestAlliesCount == alliesCount) {
                        tiedKingdomsList.add(competingKingdoms.get(i));
                    }
                }
            }
        }
        return tiedKingdomsList;
    }
}
