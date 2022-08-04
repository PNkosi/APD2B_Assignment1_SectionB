package com.perfectnkosi;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int NUM_OF_JUDGES = 3;
        final int NUM_OF_COMPETITORS = 1;
        double[][] scores = new double[NUM_OF_COMPETITORS][NUM_OF_JUDGES];
        String[] competitors = new String[NUM_OF_COMPETITORS];

        Scanner input = new Scanner(System.in);

        for (int i = 0; i < NUM_OF_COMPETITORS; i++) {
            System.out.print("Enter competitor's name:    ");
            try {
                String name = input.nextLine();
                competitors[i] = name;
                System.out.println("\nJudge scores for " + competitors[i]);
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid name");
                i--;
                continue;
            }
            int judgeNumber = 1;
            for (int j = 0; j < NUM_OF_JUDGES; j++) {
                try {
                    System.out.print("Enter the score of judge " + judgeNumber + ":    ");
                    double score = input.nextDouble();
                    if (score < 0.0 || score > 10.0) {
                        throw new ScoreOutOfRangeException();
                    }
                    scores[i][j] = score;
                    judgeNumber++;
                    input.nextLine();

                } catch (InputMismatchException e) {
                    System.out.println("Invalid. Scores can only be numbers");
                    j--;
                }
                catch (ScoreOutOfRangeException e) {
                    System.out.println(e.getMessage());
                    j--;
                }
            } // End of inner loop
            System.out.println("\n");
        } // End of outer loop
    } // End of main method


    public static double getScoresTotol(double[][] scores, int competitorIndex) {
        final int NUM_OF_SCORES = 3;

        double total = 0.0d;
        for (int i = 0; i < NUM_OF_SCORES; i++) {
            total = total + scores[competitorIndex][i];
        }
        return total;
    }

    public static double getMinimum(double[][] scores, int competitorIndex) {
        final int NUM_OF_SCORES = 3;

        double min = scores[0][0];
        for (int j = 0; j < NUM_OF_SCORES; j++) {
            if (scores[competitorIndex][j] < min)
                min = scores[competitorIndex][j];
        }
        return min;
    }


    public static double getMaximm(double[][] scores, int competitorIndex) {
        final int NUM_OF_SCORES = 3;

        double max = scores[0][0];
        for (int j = 0; j < NUM_OF_SCORES; j++) {
            if (scores[competitorIndex][j] > max)
                max = scores[competitorIndex][j];
        }
        return max;
    }


    public static double[] getAverages(double[][] scores, int numberOfCompetitors) {
        double[] averages = new double[numberOfCompetitors];

        for (int i = 0; i < numberOfCompetitors; i++) {
            double min = getMinimum(scores, i);
            double max = getMaximm(scores, i);
            double total = getScoresTotol(scores, i);

            double newTotal = total - min - max;

            double average = newTotal / 6;
            averages[i] = average;
        }
        return averages;
    }
} // End of class
