package com.gt.tot;

import com.gt.tot.kingdom.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class SpaceKingdomTest {

    private Kingdom spaceKingdom = Space.kingdom();

    @Test
    public void shouldDisplayEmblem() throws Exception {
        assertEquals("Gorilla", spaceKingdom.emblemName());
    }

    @Test
    public void shouldDisplayRulerName() throws Exception {
        assertEquals("King Shan", spaceKingdom.rulerName());
    }


    @Test
    public void shouldBeAbleToSendSecretMessageAndConfirmAlliance() throws Exception {
        Set<String> allies = new HashSet<>();
        allies.add("Land");
        allies.add("Air");
        allies.add("Ice");

        spaceKingdom.resetAllies();

        spaceKingdom.sendSecretMessageAndRequestAlliance(Air.kingdom(), "oaaawaala");
        spaceKingdom.sendSecretMessageAndRequestAlliance(Land.kingdom(), "a1d22n333a4444p");
        spaceKingdom.sendSecretMessageAndRequestAlliance(Ice.kingdom(), "zmzmzmzaztzozh");
        spaceKingdom.sendSecretMessageAndRequestAlliance(Water.kingdom(), "Winter is comming");

        String alliesNames[] = spaceKingdom.alliesNames().split(",");
        Set<String> formedAllies = new HashSet<>(Arrays.asList(alliesNames));

        assertEquals(allies, formedAllies);
    }

    @Test
    public void shouldNotBeAbleToFormSelfAlliance() throws Exception {
        spaceKingdom.resetAllies();
        spaceKingdom.sendSecretMessageAndRequestAlliance(Space.kingdom(), "gorilla");
        assertEquals("None", spaceKingdom.alliesNames());
    }

    @Test
    public void shouldBeAbleToGetherSufficientAlliancesAndRuleSoutherosUniverse() throws Exception {
        spaceKingdom.resetAllies();

        spaceKingdom.sendSecretMessageAndRequestAlliance(Air.kingdom(), "Let’s swing the sword together");
        spaceKingdom.sendSecretMessageAndRequestAlliance(Land.kingdom(), "Die or play the tame of thrones");
        spaceKingdom.sendSecretMessageAndRequestAlliance(Ice.kingdom(), "Ahoy! Fight for me with men and money");
        spaceKingdom.sendSecretMessageAndRequestAlliance(Water.kingdom(), "Summer is comming");
        spaceKingdom.sendSecretMessageAndRequestAlliance(Fire.kingdom(), "Drag on Martin!");

        assertEquals(spaceKingdom.rulerName(), UniverseOfSoutheros.universeOfSoutheros().rulerName());
    }

    @Test
    public void shouldNotGatherAlliancesWhenSentWrongSecretMessages() throws Exception {
        spaceKingdom.resetAllies();

        spaceKingdom.sendSecretMessageAndRequestAlliance(Air.kingdom(), "Let’s swing the knife");
        spaceKingdom.sendSecretMessageAndRequestAlliance(Land.kingdom(), "Die or win the tame of thrones");
        spaceKingdom.sendSecretMessageAndRequestAlliance(Ice.kingdom(), "Ahoy! Fight for king with men and honey");
        spaceKingdom.sendSecretMessageAndRequestAlliance(Water.kingdom(), "Summer is comming");
        spaceKingdom.sendSecretMessageAndRequestAlliance(Fire.kingdom(), "Drag Martin!");

        assertEquals(NullKingdom.kingdom().rulerName(), UniverseOfSoutheros.universeOfSoutheros().rulerName());
    }
}
