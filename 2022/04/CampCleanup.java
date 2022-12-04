import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class CampCleanup {

    public static void main(String args[]) {


        int sumFirst = 0;
        int sumSecond = 0;
        try {

            FileInputStream fis = new FileInputStream("./input.txt");
            Scanner sc = new Scanner(fis);

            while(sc.hasNextLine()) {
                String curLine = sc.nextLine().trim();
                String first = curLine.substring(0, curLine.indexOf(','));
                String second = curLine.substring(curLine.indexOf(',') + 1);

                int beginFirst = Integer.parseInt(first.substring(0, first.indexOf('-')));
                int endFirst = Integer.parseInt(first.substring(first.indexOf('-') + 1));
                int beginSecond = Integer.parseInt(second.substring(0, second.indexOf('-')));
                int endSecond = Integer.parseInt(second.substring(second.indexOf('-') + 1));

                boolean isEqual = false;
                for(int i = beginFirst; i <= endFirst; i++) {
                    if(isEqual) {
                        break;
                    }
                    for(int j = beginSecond; j <= endSecond; j++) {
                        if(i == j) {
                            isEqual = true;
                            break;
                        }
                    }
                }

                if(isEqual) {
                    sumSecond++;
                }

                if(beginFirst <= beginSecond && endSecond <= endFirst) {
                    sumFirst++;
                    continue;
                }
                if(beginSecond <= beginFirst && endFirst <= endSecond) {
                    sumFirst++;
                    continue;
                }



// this is not working for first question
//
//                String firstElf = "";
//                String secondElf = "";
//
//                while(beginFirst <= endFirst) {
//                    firstElf += beginFirst;
//                    beginFirst++;
//                }
//                while(beginSecond <= endSecond) {
//                    secondElf += beginSecond;
//                    beginSecond++;
//                }
//
//                if(firstElf.contains(secondElf) || secondElf.contains(firstElf)) {
//                    sum++;
//                } else {
//                    System.out.println("first: " + firstElf);
//                    System.out.println("second: " + secondElf);
//                }

            }
            sc.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        System.out.println("Fully contained assignments for first Star: " + sumFirst);
        System.out.println("Fully contained assignments for Second Star: " + sumSecond);

    }


}
