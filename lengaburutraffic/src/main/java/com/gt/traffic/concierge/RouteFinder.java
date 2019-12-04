package com.gt.traffic.concierge;

import com.gt.traffic.planet.Orbit;
import com.gt.traffic.planet.Suburb;

import java.util.*;
import java.util.stream.Collectors;

class RouteFinder {

    private Set<List<String>> allPaths = new HashSet<>();
    private Map<String, List<String>> pathInfoMap = new HashMap<>();

    RouteFinder(List<Orbit> orbits) {
        for (Orbit orbit : orbits) {
            String source = orbit.getFrom().suburbName();
            String destination = orbit.getTo().suburbName();

            addConnection(source, orbit.getName());
            addConnection(orbit.getName(), source);

            addConnection(destination, orbit.getName());
            addConnection(orbit.getName(), destination);
        }
    }


    List<List<String>> findBetween(Suburb origin, Suburb... destination) {
        allPaths.clear();

        List<String> pathInfos = new ArrayList<>();
        pathInfos.add(origin.suburbName());
        for (Suburb suburb : destination) {
            findAllPaths(origin.suburbName(), suburb.suburbName(), new HashMap<>(), pathInfos);
        }

        List<String> suburbNames = new ArrayList<>();
        suburbNames.add(origin.suburbName());
        for (Suburb suburb : destination)
            suburbNames.add(suburb.suburbName());

        return allPaths.stream()
                .filter(strings -> strings.containsAll(suburbNames))
                .collect(Collectors.toList());
    }

    private void findAllPaths(String source, String destination, Map<String, Boolean> visited, List<String> pathInfos) {
        visited.put(source, true);

        if (source.equalsIgnoreCase(destination)) {
            allPaths.add(new ArrayList<>(pathInfos));
            visited.put(source, false);
            return;
        }

        if (null != pathInfoMap.get(source)) {
            for (String pathInfo : pathInfoMap.get(source)) {
                if (null == visited.get(pathInfo) || !visited.get(pathInfo)) {
                    pathInfos.add(pathInfo);
                    findAllPaths(pathInfo, destination, visited, pathInfos);
                    pathInfos.remove(pathInfo);
                }
            }
        }
        visited.put(source, false);
    }

    private void addConnection(String element, String pathInfo) {
        pathInfoMap.computeIfAbsent(element, s -> new ArrayList<>()).add(pathInfo);
    }
}
