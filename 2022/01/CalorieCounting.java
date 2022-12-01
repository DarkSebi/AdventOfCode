import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CalorieCounting {

    public static void main(String args[]) {

        ArrayList<Integer> elfs = new ArrayList<>();
        int i = 0;
        try {
            //the file to be opened for reading
            FileInputStream fis = new FileInputStream("./input.txt");
            Scanner sc = new Scanner(fis);    //file to be scanned
                //returns true if there is another line to read
            int sum = 0;

            while(sc.hasNextLine()) {
                String curLine = sc.nextLine().trim();
                if(curLine == "") {
                    elfs.add(sum);
                    sum = 0;
                    i++;
                } else {
                    sum += Integer.parseInt(curLine);
                }
            }
            sc.close();     //closes the scanner
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        Collections.sort(elfs);
        int first = elfs.get(--i);
        int second = elfs.get(--i);
        int third = elfs.get(--i);
        // question 1
        System.out.println("first: " + first);
        // question 2
        System.out.println("first three: " + (first + second + third));
    }
}
