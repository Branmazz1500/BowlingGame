package com.brandonmazzarella.bowling.utilities;

import java.util.Arrays;
import java.util.Locale;

public class Utilities {
    public boolean isValidRoll(String x){
        String[] valid = new String[]{"1","2","3","4","5","6","7","8","9", "strike", "spare", "miss"};
        x = x.toLowerCase();
        for(String v : valid) if(x.equals(v)) return true;
        return false;
    }





    public String normalRoll(String roll, int ballCountOnFrame, StringBuilder rollsBuilder){
        if(ballCountOnFrame == 1) rollsBuilder.replace(1,2,roll);
        else rollsBuilder.replace(3,4, roll);
        return rollsBuilder.toString();
    }

    public String normalScore(int currScore){
        String score = currScore+"";
        int neededSpaces = 5-score.length();
        StringBuilder sb = new StringBuilder(score);
        for(int i = 0; i<neededSpaces; i++) sb.replace(0,0," ");
        return sb.toString();
    }

    public String strikeScore(int currScore){
        String score = currScore+"";
        int neededSpaces = 5-score.length();
        StringBuilder sb = new StringBuilder(score);
        for(int i = 0; i<neededSpaces; i++) sb.replace(0,0," ");
        return sb.toString();
    }

    public String[] updateScore(String[] scores, int frame, int score, int strikesInARow,
                                int roll, int ballCountOnFrame){
        if(strikesInARow == 1 && ballCountOnFrame == 2){
            scores[frame-1] += score + 10 + roll;
        }
        return scores;
    }


    public void updateScore(int score, int tentativeScore, String roll){

    }
}
