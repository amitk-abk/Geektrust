package com.gt.tot;

import com.gt.tot.kingdom.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class BidsToRuleSoutheros {

    private UniverseOfSoutheros southeros = UniverseOfSoutheros.universeOfSoutheros();
    private Kingdom spaceKingdom = Space.kingdom();

    public void kingShanBidsToRuleSoutheros(String inputFile) {

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

                if (input.equals("Allies of King Shan?")) {
                    System.out.println(spaceKingdom.alliesNames());
                }

                if (input.equalsIgnoreCase("Input Messages to kingdoms from King Shan:")) {
                    Map<String, String> messages = messagesForKingdomsFromKingShan(reader);
                    sendSecretMessagesToKingdoms(messages);
                }

                if (input.equals("clear")) {
                    spaceKingdom.resetAllies();
                }
            } while (!input.equals("q"));
        } catch (Exception e) {
            System.out.println("Opps, something went wrong regarding input file.");
        }
    }

    private Map<String, String> messagesForKingdomsFromKingShan(BufferedReader reader) throws IOException {
        Map<String, String> messages = new LinkedHashMap<>();
        String messageLine = "options";
        while (messageLine.length() != 0) {
            messageLine = reader.readLine();
            if (messageLine.contains(",")) {
                System.out.println(messageLine);
                String messageInfo[] = messageLine.split(",");
                String toKingdom = messageInfo[0].trim();
                String secrete = messageInfo[1].trim();
                messages.put(toKingdom, secrete);
            }
        }
        System.out.println();
        return messages;
    }

    private void sendSecretMessagesToKingdoms(Map<String, String> messages) {
        Set<Map.Entry<String, String>> kingdomMessageEntries = messages.entrySet();

        for (Map.Entry<String, String> kingdomMessageEntry : kingdomMessageEntries) {
            Kingdom recipientKingdom = NullKingdom.kingdom();
            String kingdom = kingdomMessageEntry.getKey();
            switch (kingdom) {
                case "Air":
                    recipientKingdom = Air.kingdom();
                    break;

                case "Land":
                    recipientKingdom = Land.kingdom();
                    break;

                case "Ice":
                    recipientKingdom = Ice.kingdom();
                    break;

                case "Water":
                    recipientKingdom = Water.kingdom();
                    break;

                case "Fire":
                    recipientKingdom = Fire.kingdom();
                    break;
            }
            spaceKingdom.sendSecretMessageAndRequestAlliance(recipientKingdom,
                    kingdomMessageEntry.getValue());
        }
    }
}
