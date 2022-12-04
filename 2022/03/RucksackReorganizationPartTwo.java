import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RucksackReorganizationPartTwo {

    public static void main(String args[]) {

        Map<Character, Integer> prio = new HashMap<>();

        int idx = 1;
        for(char key = 'a'; key <= 'z'; key++) {
            prio.put(key, idx);
            idx++;
        }
        for(char key = 'A'; key <= 'Z'; key++) {
            prio.put(key, idx);
            idx++;
        }

        int sum = 0;

        try {

            FileInputStream fis = new FileInputStream("/Users/sebi/IdeaProjects/AoC#1/src/ltd/FireEvening/inputs/03.txt");
            Scanner sc = new Scanner(fis);

            while (sc.hasNextLine()) {
                String first = sc.nextLine().trim();
                String second = sc.nextLine().trim();
                String third = sc.nextLine().trim();

                for(int i = 0; i <= first.length(); i++) {
                    char firstChar = first.charAt(i);
                    if(second.indexOf(firstChar) != -1 && third.indexOf(firstChar) != -1) {
                        sum += prio.get(firstChar);
                        break;
                    }
                }

            }
            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("prios: " + sum);

    }


}
