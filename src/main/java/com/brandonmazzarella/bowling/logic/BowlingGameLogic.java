package com.brandonmazzarella.bowling.logic;

import com.brandonmazzarella.bowling.objects.BowlingGame;
import com.brandonmazzarella.bowling.utilities.Utilities;

import java.util.Arrays;
import java.util.Scanner;

public class BowlingGameLogic {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        BowlingGame game = new BowlingGame();

        while(game.frame < 10) {
            System.out.println("Enter the result of the next roll or enter options: ");
            String roll = scanner.next();
            if (game.utility.isValidRoll(roll)) {
                if (roll.equalsIgnoreCase("strike")) game.strike();
                else if (roll.equalsIgnoreCase("spare")) game.spare();
                else game.updateScore(roll);
                game.nextFrame();
                game.printScore();
            } else System.out.println("Not a valid roll");
        }
        game.over();
    }


}
