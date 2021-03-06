package com.brandonmazzarella.bowling.objects;

import com.brandonmazzarella.bowling.utilities.Utilities;

import java.util.Arrays;

public class BowlingGame {
    public String[] rolls = new String[10];
    public String[] score = new String[10];
    public int frame = 0;
    public int currScore = 0;
    public int ballCountOnFrame = 1;
    public int strikesInARow = 0;
    boolean onSpare = false;
    public int firstRoll = 0;
    public Utilities utility = new Utilities();
    public StringBuilder sb = new StringBuilder();

    /*
    Constructor, load the lists too the right format.
     */
    public BowlingGame(){
        Arrays.fill(rolls, "     ");
        Arrays.fill(score, "     ");
    }

    /*
    Checks if we are on next frame.
     */
    public void nextFrame(){
        if(ballCountOnFrame > 2) {
            ballCountOnFrame = 1;
            frame++;
            firstRoll = 0;
        }
    }

    /*
    Handling any time we get a spare.
     */
    public void spare(){
        if(ballCountOnFrame == 1){
            System.out.println("Can't get a spare on the first roll");
            return;
        }


        ballCountOnFrame++;
        StringBuilder sb = new StringBuilder(rolls[frame]);
        sb.replace(3,4, "/");
        rolls[frame] = sb.toString();
        onSpare = true;

        currScore += 10-firstRoll;
        if(strikesInARow >= 1) strikeCalculator(10-firstRoll+"", true);

    }

    /*
        Checks to see if the roll makes a spare or too many pins knocked down.
     */
    public boolean checkRoll(int increase){
        if(firstRoll+increase == 10){
            spare();
            return false;
        }else if(firstRoll + increase > 10){
            System.out.println("There are only 10 pins... and you already knocked down " + firstRoll);
            return false;
        }
        return true;
    }

    /*
        If we get a regular integer roll increase the score.  This also handles spares.
     */
    public void updateScore(String roll){
        int increase = Integer.valueOf(roll);
        if(!checkRoll(increase)) return;

        currScore += Integer.valueOf(roll);
        if(ballCountOnFrame == 1) firstRoll = increase;

        if(onSpare){
            score[frame-1] = utility.normalScore(currScore);
            currScore += increase;
            updateInfo(roll);
            onSpare = false;
        }else if(strikesInARow < 1) updateInfo(roll);
        else if(strikesInARow >= 1) strikeCalculator(roll, false);

        ballCountOnFrame++;
    }


    /*
    IF you get a strike
     */
    public void strike(){
        if(ballCountOnFrame > 1){
            System.out.println("Can not get a strike on the second roll");
            return;
        }
        strikesInARow++;
        if(strikesInARow >= 3){
            currScore += 30;
            score[frame-2] = utility.strikeScore(currScore);
        }
        if(onSpare){
            currScore += 10;
            score[frame-1] = utility.normalScore(currScore);
            onSpare = false;
        }
        ballCountOnFrame = 1;
        rolls[frame++] = " X   ";
    }

    /*
    Updating the rolls and the score
     */
    public void updateInfo(String roll){
        if(roll.equals("10")) roll = "/";
        sb = new StringBuilder(rolls[frame]);
        rolls[frame] = utility.normalRoll(roll, ballCountOnFrame, sb);
        sb = new StringBuilder(score[frame]);
        score[frame] = utility.normalScore(currScore);
    }

    /*
    Calculating score with strikes
     */
    public void strikeCalculator(String roll, boolean onSpare){
        if(strikesInARow == 1){
            if(ballCountOnFrame == 1) {
                sb = new StringBuilder(rolls[frame]);
                rolls[frame] = utility.normalRoll(roll, ballCountOnFrame, sb);
            }else{
                currScore += 10;
                score[frame-1] = utility.normalScore(currScore);
                currScore += firstRoll + Integer.valueOf(roll);
                if(onSpare) updateInfo("10");
                else updateInfo(roll);
                strikesInARow = 0;
            }
        }else if(strikesInARow >= 2){
            if(ballCountOnFrame == 1){
                sb = new StringBuilder(rolls[frame]);
                rolls[frame] = utility.normalRoll(roll, ballCountOnFrame, sb);
                currScore += 20;
                score[frame-2] = utility.normalScore(currScore);
                rolls[frame] = utility.normalRoll(roll, ballCountOnFrame, sb);
                currScore += Integer.valueOf(roll);

                strikesInARow=1;
            }
        }


    }



    /*
    The below logic formats and prints out the score after each roll
     */
    public void printScore(){
        System.out.print("Input: |");
        for (String r : rolls) System.out.print(r + "|");
        System.out.println();
        System.out.print("Score: |");
        for (String s : score) System.out.print(s + "|");
        System.out.println();
    }

    public void over(){
        System.out.println("Thanks for playing!");
    }
}
