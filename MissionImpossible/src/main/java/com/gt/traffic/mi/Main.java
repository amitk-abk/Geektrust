package com.gt.traffic.mi;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0)
            throw new RuntimeException("Input file is missing");
        new TourOfLengaburu("/initialize.txt")
                .doTour(args[0]);
    }
}
