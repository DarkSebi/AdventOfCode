import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class RucksackReorganization {



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

            FileInputStream fis = new FileInputStream("./input.txt");
            Scanner sc = new Scanner(fis);

            while (sc.hasNextLine()) {
                String curLine = sc.nextLine().trim();
                String compartmentOne = curLine.substring(0, (curLine.length()/2));
                String compartmentTwo = curLine.substring((curLine.length()/2));

                for(int i = 0; i < compartmentOne.length(); i++) {
                    char cur = compartmentOne.charAt(i);
                    if(compartmentTwo.indexOf(cur) >= 0) {
                        sum += prio.get(cur);
                        continue;
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
