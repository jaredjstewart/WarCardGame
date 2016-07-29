package com.jaredjstewart;

import java.util.Scanner;

public class GameConfiguration {
    public int numberOfPlayers;
    public int numberOfSuits;
    public int numberOfRanks;

    public static GameConfiguration initializeFromUserInput() {
        Scanner scanner = new Scanner(System.in);
        GameConfiguration gameConfiguration = new GameConfiguration();
        System.out.println("How many players? ");
        gameConfiguration.numberOfPlayers = scanner.nextInt();
        System.out.println("How many suits in the deck? ");
        gameConfiguration.numberOfSuits = scanner.nextInt();
        System.out.println("How many ranks in the deck? ");
        gameConfiguration.numberOfRanks = scanner.nextInt();

        return gameConfiguration;
    }

    private GameConfiguration() {
    }
}
