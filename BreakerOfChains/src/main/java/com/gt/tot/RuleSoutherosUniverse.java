package com.gt.tot;

import com.gt.tot.ballot.Balloting;
import com.gt.tot.ballot.ResultAnnouncer;
import com.gt.tot.kingdom.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RuleSoutherosUniverse {

    private UniverseOfSoutheros southeros = UniverseOfSoutheros.universeOfSoutheros();
    private Balloting balloting = Balloting.using("./src/main/resources/secrets.txt");

    public void ruleSoutheros(String inputFile) {
        String input = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)))) {
            do {
                input = reader.readLine();

                if (!(input.equals("q") || input.equals("clear")))
                    System.out.println(input);

                if (input.equalsIgnoreCase("Who is the ruler of Southeros?")) {
                    System.out.println(southeros.rulerName());
                }

                if (input.equalsIgnoreCase("Allies of Ruler?")) {
                    System.out.println(southeros.getRulingKingdomOfSoutheros().alliesNames());
                }

                if (input.equalsIgnoreCase("Enter the kingdoms competing to be the ruler:")) {
                    List<Kingdom> participatingKingdoms = participatingKingdoms(reader);
                    String overallResult;
                    int round = 1;
                    do {
                        ResultAnnouncer resultAnnouncer = balloting.ballotingSoutherosRuler(participatingKingdoms);
                        Map<String, String> result = resultAnnouncer.announce();

                        declareAlliesOf(participatingKingdoms, round, result);

                        overallResult = result.get("result");
                        if ("tie".equals(overallResult)) {
                            participatingKingdoms = prepareForTieRound(participatingKingdoms, resultAnnouncer);
                        }
                        round++;
                    } while ("tie".equals(overallResult));
                }
            } while (!input.equals("q"));
        } catch (IOException e) {
            System.out.println("Opps, something went wrong regarding input file.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private List<Kingdom> prepareForTieRound(List<Kingdom> participatingKingdoms,
                                             ResultAnnouncer resultAnnouncer) {
        resetAll(participatingKingdoms);
        return resultAnnouncer.tiedKingdoms();
    }

    private void declareAlliesOf(List<Kingdom> participatingKingdoms, int round, Map<String, String> result) {
        System.out.println("Results after round " + round + " ballot count");
        for (Kingdom kingdom : participatingKingdoms) {
            System.out.println(result.get(kingdom.getName()));
        }
    }

    private void resetAll(List<Kingdom> participatingKingdoms) {
        for (Kingdom kingdom : participatingKingdoms) {
            kingdom.resetState();
        }
    }

    private List<Kingdom> participatingKingdoms(BufferedReader reader) throws IOException {
        String messageLine = reader.readLine();
            System.out.println(messageLine + "\n");
            String[] kingdoms = messageLine.split("\\s");
            return Arrays.stream(kingdoms)
                    .map(this::kingdomFromName)
                    .collect(Collectors.toList());
    }

    private Kingdom kingdomFromName(String kingdom) {
        Kingdom paricipatingKingdom = NullKingdom.kingdom();
        switch (kingdom) {
            case "Air":
                paricipatingKingdom = Air.kingdom();
                break;

            case "Land":
                paricipatingKingdom = Land.kingdom();
                break;

            case "Ice":
                paricipatingKingdom = Ice.kingdom();
                break;

            case "Water":
                paricipatingKingdom = Water.kingdom();
                break;

            case "Fire":
                paricipatingKingdom = Fire.kingdom();
                break;

            case "Space":
                paricipatingKingdom = Space.kingdom();
                break;
        }
        return paricipatingKingdom;
    }
}
