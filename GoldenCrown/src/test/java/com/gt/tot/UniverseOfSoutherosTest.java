package com.gt.tot;

import com.gt.tot.kingdom.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UniverseOfSoutherosTest {

    private UniverseOfSoutheros southeros = UniverseOfSoutheros.universeOfSoutheros();

    @Test
    public void shouldReturnTotalKingdomsInSoutherosUniverse() throws Exception {
        assertEquals(6, southeros.kingdoms().size());
    }

    @Test
    public void shouldReturnNoneAsRulerWhenNoOneRulesSoutherosAndNoAlliesExists() throws Exception {
        southeros.resetRuler();
        assertEquals("None", southeros.rulerName());
    }


    @Test
    public void shouldReturnNoneAsAlliesOfRulerWhenNoOneRulesSoutheros() throws Exception {
        southeros.resetRuler();
        assertEquals("None", southeros.getRulingKingdomOfSoutheros().alliesNames());
        assertEquals("None", southeros.allies());
    }

    @Test
    public void shouldReturnRulerNameOfSoutherosWhenIsClaimed() throws Exception {
        Kingdom spaceKingdom = Space.kingdom();

        spaceKingdom.resetAllies();

        spaceKingdom.sendSecretMessageAndRequestAlliance(Air.kingdom(), "Let’s swing the sword together");
        spaceKingdom.sendSecretMessageAndRequestAlliance(Land.kingdom(), "Die or play the tame of thrones");
        spaceKingdom.sendSecretMessageAndRequestAlliance(Ice.kingdom(), "Ahoy! Fight for me with men and money");
        spaceKingdom.sendSecretMessageAndRequestAlliance(Water.kingdom(), "Summer is comming");
        spaceKingdom.sendSecretMessageAndRequestAlliance(Fire.kingdom(), "Drag on Martin!");

        assertEquals("King Shan", southeros.rulerName());
    }
}
