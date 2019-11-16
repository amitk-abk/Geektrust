package com.gt.tot;

import com.gt.tot.kingdom.*;
import java.util.HashSet;
import java.util.Set;

public class UniverseOfSoutheros {

    private static final UniverseOfSoutheros SOUTHEROS = new UniverseOfSoutheros();
    private final Set<Kingdom> kingdomsInSoutheros;
    private Kingdom rulingKingdomOfSoutheros;
    private Kingdoms kingdomsList = Kingdoms.all();

    private UniverseOfSoutheros() {
        kingdomsInSoutheros = new HashSet<>();
        kingdomsInSoutheros.addAll(kingdomsList.allKingdoms());
        rulingKingdomOfSoutheros = NullKingdom.kingdom();
    }

    public static UniverseOfSoutheros universeOfSoutheros() {
        return SOUTHEROS;
    }

    public String rulerName() {
        Kingdom ruler = kingdomsList.withHighestAllies();
        this.rulingKingdomOfSoutheros = ruler;
        return ruler.rulerName();
    }

    public Kingdom getRulingKingdomOfSoutheros() {
        return rulingKingdomOfSoutheros;
    }

    public String allies() {
        return rulingKingdomOfSoutheros.alliesNames();
    }

    public Set<Kingdom> kingdoms() {
        return kingdomsInSoutheros;
    }

    public void resetRuler() {
        for (Kingdom kingdom : kingdoms())
            kingdom.resetAllies();
    }
}
