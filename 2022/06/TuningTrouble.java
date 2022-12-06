import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TuningTrouble {

    public static void main(String args[]) throws FileNotFoundException {

        FileInputStream fis = new FileInputStream("./input.txt");
        Scanner sc = new Scanner(fis);
        String input = sc.nextLine();

        char[] marker = new char[4];
        char[] starter = new char[14];
        int processedMarker = 0;

        for (int i = 0; i < input.length(); i++) {
            marker[i % 4] = input.charAt(i);
            if ((i >= 3) &&
                    !isSame(marker)) {
                processedMarker = ++i;
                break;
            }
        }

        int processedStarter = 0;

        for (int i = 0; i < input.length(); i++) {
            starter[i % 14] = input.charAt(i);

            if ((i >= 14) &&
                    !isSame(starter)) {
                processedStarter = ++i;
                break;
            }
        }
        System.out.println("processed until marker: " + processedMarker);
        System.out.println("processed until starter: " + processedStarter);
    }

    private static boolean isSame(char[] array) {
        boolean isSame = false;
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length; j++) {
                if (i == j) {
                    continue;
                }
                if (array[i] == array[j]) {
                    isSame = true;
                }
            }
        }
        return isSame;
    }
}