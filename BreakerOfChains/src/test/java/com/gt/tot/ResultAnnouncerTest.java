package com.gt.tot;

import com.gt.tot.ballot.ResultAnnouncer;
import com.gt.tot.kingdom.Air;
import com.gt.tot.kingdom.Kingdom;
import com.gt.tot.kingdom.Land;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ResultAnnouncerTest {

    private Air air = Air.kingdom();
    private Land land = Land.kingdom();

    @Before
    public void init() {
        air.resetState();
        land.resetState();
    }

    @Test
    public void shouldAnnounceTieAsDefaultResult() throws Exception {
        ResultAnnouncer resultAnnouncer = getResultAnnouncer();
        Map<String, String> result = resultAnnouncer.announce();

        assertEquals("tie", result.get("result"));
        assertEquals("Allies for Air: 0", result.get(air.getName()));
        assertEquals("Allies for Land: 0", result.get(land.getName()));
    }

    @Test
    public void shouldProvideListOfKingdomsInCaseOfTie() throws Exception {
        ResultAnnouncer resultAnnouncer = getResultAnnouncer();

        Map<String, String> result = resultAnnouncer.announce();
        assertEquals("tie", result.get("result"));

        List<Kingdom> tiedKingdoms = resultAnnouncer.tiedKingdoms();
        assertEquals(Arrays.asList(air, land), tiedKingdoms);
    }

    private ResultAnnouncer getResultAnnouncer() {
        return ResultAnnouncer.forKingdoms(Arrays.asList(air, land));
    }
}
