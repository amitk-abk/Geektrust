package com.gt.tot;

import com.gt.tot.kingdom.Kingdom;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UniverseOfSoutherosTest {

    private UniverseOfSoutheros southeros = UniverseOfSoutheros.universeOfSoutheros();

    @Test
    public void shouldReturnNoneAsRulerWhenNoOneRulesSoutheros() throws Exception {
        resetRuler();
        assertEquals("None", southeros.rulerName());
    }

    @Test
    public void shouldReturnNoneAsAlliesOfRulerWhenNoOneRulesSoutheros() throws Exception {
        resetRuler();
        assertEquals("None", southeros.getRulingKingdomOfSoutheros().alliesNames());
        assertEquals("None", southeros.allies());
    }

    private void resetRuler() {
        for (Kingdom kingdom: southeros.kingdoms())
            kingdom.resetState();
    }

}
