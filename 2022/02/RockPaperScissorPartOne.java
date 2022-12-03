import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RockPaperScissorPartOne {

    // Enemy (first Row):
    // A Rock 1
    // B Paper 2
    // C Scissor 3

    // Player (Second Row):
    // X Rock 1
    // Y Paper 2
    // Z Scissor 3

    // total score is sum of what player selects
    // 1 Rock
    // 2 Paper
    // 3 Scissor
    // plus outcome of round
    // 0 loose
    // 3 draw
    // 6 win
    public static void main(String args[]) {

        int totalScore = 0;

        final int LOOSE = 0;
        final int DRAW = 3;
        final int WIN = 6;

        try {
            //the file to be opened for reading
            FileInputStream fis = new FileInputStream("./input.txt");
            Scanner sc = new Scanner(fis);    //file to be scanned
            //returns true if there is another line to read
            while(sc.hasNextLine()) {
                String curLine = sc.nextLine().trim();
                int enemy = getNum(curLine.charAt(0));
                int player = getNum(curLine.charAt(curLine.length() - 1));
                // draw
                if((enemy - player) == 0) {
                    totalScore += (player + DRAW);
                    continue;
                }
                // win
                if((enemy == 1 && player == 2) ||
                        (enemy == 2 && player == 3) ||
                        (enemy == 3 && player == 1)) {
                    totalScore += (player + WIN);
                    continue;
                // loose
                } else {
                    totalScore += (player + LOOSE);
                }
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
        if(input == 'A' || input == 'X')
            return 1;
        if(input == 'B' || input == 'Y')
            return 2;
        if(input == 'C' || input == 'Z')
            return 3;
        throw new Exception("Wrong input");
    }


}
