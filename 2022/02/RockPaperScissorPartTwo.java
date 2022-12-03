import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RockPaperScissorPartTwo {

    // Enemy (first Row):
    // A Rock 1
    // B Paper 2
    // C Scissor 3

    // Ending (Second Row):
    // X Rock Loose 0
    // Y Paper Draw 3
    // Z Scissor Win 6

    // total score is sum of what player selects
    // 1 Rock
    // 2 Paper
    // 3 Scissor
    // plus outcome of round
    // 0 loose
    // 3 draw
    // 6 win

    final static int ROCK = 1;
    final static int PAPER = 2;
    final static int SCISSOR = 3;
    public static void main(String args[]) {

        int totalScore = 0;



        try {
            //the file to be opened for reading
            FileInputStream fis = new FileInputStream("./input.txt");
            Scanner sc = new Scanner(fis);    //file to be scanned
            //returns true if there is another line to read
            while(sc.hasNextLine()) {
                String curLine = sc.nextLine().trim();
                int enemy = getNum(curLine.charAt(0));
                int outcome = getNum(curLine.charAt(curLine.length() - 1));
                totalScore += calculate(outcome, enemy);
            }
            sc.close();     //closes the scanner
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("totalScore: " + totalScore);

    }

    private static int getNum(char input) throws Exception {
        if(input == 'A')
            return 1;
        if(input == 'B')
            return 2;
        if(input == 'C' || input == 'Y')
            return 3;
        if(input == 'X')
            return 0;
        if(input == 'Z')
            return 6;
        throw new Exception("Wrong input");
    }

    private static int calculate(int outcome, int enemy) throws Exception {
        switch (outcome) {
            // loose
            case 0: {
                switch (enemy) {
                    case ROCK: return outcome + SCISSOR;
                    case PAPER: return outcome + ROCK;
                    case SCISSOR: return outcome + PAPER;
                }
            }
            // draw
            case 3: return outcome + enemy;
            // win
            case 6:
                switch (enemy) {
                    case ROCK: return outcome + PAPER;
                    case PAPER: return outcome + SCISSOR;
                    case SCISSOR: return outcome + ROCK;
                }
            default: throw new Exception("path missing");
            }
        }

    }



