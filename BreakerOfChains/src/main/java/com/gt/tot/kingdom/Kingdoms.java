package com.gt.tot.kingdom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kingdoms {

    private static final Kingdoms KINGDOMS = new Kingdoms();
    private Space space = Space.kingdom();
    private Air air = Air.kingdom();
    private Fire fire = Fire.kingdom();
    private Ice ice = Ice.kingdom();
    private Land land = Land.kingdom();
    private Water water = Water.kingdom();
    private List<Kingdom> kingdomList = new ArrayList<>();

    private Kingdoms() {
        kingdomList.add(space);
        kingdomList.add(air);
        kingdomList.add(fire);
        kingdomList.add(ice);
        kingdomList.add(land);
        kingdomList.add(water);
    }

    public static Kingdoms all() {
        return KINGDOMS;
    }

    public List<Kingdom> allKingdoms() {
        return kingdomList;
    }

    public Kingdom withHighestAllies() {
        Kingdom ruler = NullKingdom.kingdom();
        List<Kingdom> kgdms = new ArrayList<>(kingdomList);
        kgdms.sort((k1, k2) -> k2.alliesCount() - k1.alliesCount());
        if (ruler.alliesCount() < kgdms.get(0).alliesCount())
            return kgdms.get(0);
        else
            return ruler;
    }
}
