import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class SupplySnacks {

    public static void main(String args[]) throws FileNotFoundException {

        StringBuilder one = new StringBuilder();
        StringBuilder two = new StringBuilder();
        StringBuilder three = new StringBuilder();
        StringBuilder four = new StringBuilder();
        StringBuilder five = new StringBuilder();
        StringBuilder six = new StringBuilder();
        StringBuilder seven = new StringBuilder();
        StringBuilder eight = new StringBuilder();
        StringBuilder nine = new StringBuilder();

        FileInputStream fis = new FileInputStream("/Users/sebi/IdeaProjects/AoC#1/src/ltd/FireEvening/inputs/05.txt");
        Scanner sc = new Scanner(fis);



        // reading input stack
        while(sc.hasNextLine()) {
                String curLine = sc.nextLine();

                for(int i = 0; i < curLine.length(); i++) {
                    char cur = curLine.charAt(i);
                    if(cur == '1') {
                        break;
                    }
                    if(cur == ' ' || cur == '[' || cur == ']') {
                        continue;
                    }
                    switch (i) {
                        case 1: {
                            one.append(cur);
                            break;
                        }
                        case 5: {
                            two.append(cur);
                            break;
                        }
                        case 9: {
                            three.append(cur);
                            break;
                        }
                        case 13: {
                            four.append(cur);
                            break;
                        }
                        case 17: {
                            five.append(cur);
                            break;
                        }
                        case 21: {
                            six.append(cur);
                            break;
                        }
                        case 25: {
                            seven.append(cur);
                            break;
                        }
                        case 29: {
                            eight.append(cur);
                            break;
                        }
                        case 33: {
                            nine.append(cur);
                            break;
                        }
                    }
                }
                if(curLine.isEmpty())
                    break;
            }


        // filling stacks

        Stack<Character> stackOne = new Stack<>();
        Stack<Character> stackTwo = new Stack<>();
        Stack<Character> stackThree = new Stack<>();
        Stack<Character> stackFour = new Stack<>();
        Stack<Character> stackFive = new Stack<>();
        Stack<Character> stackSix = new Stack<>();
        Stack<Character> stackSeven = new Stack<>();
        Stack<Character> stackEight = new Stack<>();
        Stack<Character> stackNine = new Stack<>();

        Stack<Character> stackOneQ2 = new Stack<>();
        Stack<Character> stackTwoQ2 = new Stack<>();
        Stack<Character> stackThreeQ2 = new Stack<>();
        Stack<Character> stackFourQ2 = new Stack<>();
        Stack<Character> stackFiveQ2 = new Stack<>();
        Stack<Character> stackSixQ2 = new Stack<>();
        Stack<Character> stackSevenQ2 = new Stack<>();
        Stack<Character> stackEightQ2 = new Stack<>();
        Stack<Character> stackNineQ2 = new Stack<>();

        for(int i = one.length()-1; i >= 0; i--) {
            stackOne.push(one.charAt(i));
            stackOneQ2.push(one.charAt(i));
        }
        for(int i = two.length()-1; i >= 0; i--) {
            stackTwo.push(two.charAt(i));
            stackTwoQ2.push(two.charAt(i));
        }
        for(int i = three.length()-1; i >= 0; i--) {
            stackThree.push(three.charAt(i));
            stackThreeQ2.push(three.charAt(i));
        }
        for(int i = four.length()-1; i >= 0; i--) {
            stackFour.push(four.charAt(i));
            stackFourQ2.push(four.charAt(i));
        }
        for(int i = five.length()-1; i >= 0; i--) {
            stackFive.push(five.charAt(i));
            stackFiveQ2.push(five.charAt(i));
        }
        for(int i = six.length()-1; i >= 0; i--) {
            stackSix.push(six.charAt(i));
            stackSixQ2.push(six.charAt(i));
        }
        for(int i = seven.length()-1; i >= 0; i--) {
            stackSeven.push(seven.charAt(i));
            stackSevenQ2.push(seven.charAt(i));
        }
        for(int i = eight.length()-1; i >= 0; i--) {
            stackEight.push(eight.charAt(i));
            stackEightQ2.push(eight.charAt(i));
        }
        for(int i = nine.length()-1; i >= 0; i--) {
            stackNine.push(nine.charAt(i));
            stackNineQ2.push(nine.charAt(i));
        }

        // read commands
        while(sc.hasNextLine()) {
            String curLine = sc.nextLine();
            String countSub = curLine.substring(5);
            String fromSub = countSub.substring(7);
            String toSub = fromSub.substring(5);

            int count = Integer.parseInt(""+countSub.trim().substring(0,2).trim());
            int from = Integer.parseInt(""+fromSub.trim().substring(0,2).trim());
            int to = Integer.parseInt(""+toSub.trim().charAt(0));


            for(int i = count; i > 0; i--) {
                char cur = ' ';
                switch (from) {
                    case 1: {
                        cur = stackOne.pop();
                        break;
                    }
                    case 2: {
                        cur = stackTwo.pop();
                        break;
                    }
                    case 3: {
                        cur = stackThree.pop();
                        break;
                    }
                    case 4: {
                        cur = stackFour.pop();
                        break;
                    }
                    case 5: {
                        cur = stackFive.pop();
                        break;
                    }
                    case 6: {
                        cur = stackSix.pop();
                        break;
                    }
                    case 7: {
                        cur = stackSeven.pop();
                        break;
                    }
                    case 8: {
                        cur = stackEight.pop();
                        break;
                    }
                    case 9: {
                        cur = stackNine.pop();
                        break;
                    }
                }

                switch (to) {
                    case 1: {
                        stackOne.push(cur);
                        break;
                    }
                    case 2: {
                        stackTwo.push(cur);
                        break;
                    }
                    case 3: {
                        stackThree.push(cur);
                        break;
                    }
                    case 4: {
                        stackFour.push(cur);
                        break;
                    }
                    case 5: {
                        stackFive.push(cur);
                        break;
                    }
                    case 6: {
                        stackSix.push(cur);
                        break;
                    }
                    case 7: {
                        stackSeven.push(cur);
                        break;
                    }
                    case 8: {
                        stackEight.push(cur);
                        break;
                    }
                    case 9: {
                        stackNine.push(cur);
                        break;
                    }
                }
            }

            StringBuilder curSen = new StringBuilder();

            for(int i = count; i > 0; i--) {
                switch (from) {
                    case 1: {
                        curSen.append(stackOneQ2.pop());
                        break;
                    }
                    case 2: {
                        curSen.append(stackTwoQ2.pop());
                        break;
                    }
                    case 3: {
                        curSen.append(stackThreeQ2.pop());
                        break;
                    }
                    case 4: {
                        curSen.append(stackFourQ2.pop());
                        break;
                    }
                    case 5: {
                        curSen.append(stackFiveQ2.pop());
                        break;
                    }
                    case 6: {
                        curSen.append(stackSixQ2.pop());
                        break;
                    }
                    case 7: {
                        curSen.append(stackSevenQ2.pop());
                        break;
                    }
                    case 8: {
                        curSen.append(stackEightQ2.pop());
                        break;
                    }
                    case 9: {
                        curSen.append(stackNineQ2.pop());
                        break;
                    }
                }
            }


            for(int j = curSen.length()-1; j >= 0; j--) {
                switch (to) {
                    case 1: {
                        stackOneQ2.push(curSen.charAt(j));
                        break;
                    }
                    case 2: {
                        stackTwoQ2.push(curSen.charAt(j));
                        break;
                    }
                    case 3: {
                        stackThreeQ2.push(curSen.charAt(j));
                        break;
                    }
                    case 4: {
                        stackFourQ2.push(curSen.charAt(j));
                        break;
                    }
                    case 5: {
                        stackFiveQ2.push(curSen.charAt(j));
                        break;
                    }
                    case 6: {
                        stackSixQ2.push(curSen.charAt(j));
                        break;
                    }
                    case 7: {
                        stackSevenQ2.push(curSen.charAt(j));
                        break;
                    }
                    case 8: {
                        stackEightQ2.push(curSen.charAt(j));
                        break;
                    }
                    case 9: {
                        stackNineQ2.push(curSen.charAt(j));
                        break;
                    }
                }
            }

        }


        String message = ""+ stackOne.pop() + stackTwo.pop() + stackThree.pop() + stackFour.pop() + stackFive.pop()
                + stackSix.pop() + stackSeven.pop() + stackEight.pop() + stackNine.pop();

        String messageQ2 = ""+ stackOneQ2.pop() + stackTwoQ2.pop() + stackThreeQ2.pop() + stackFourQ2.pop() + stackFiveQ2.pop()
                + stackSixQ2.pop() + stackSevenQ2.pop() + stackEightQ2.pop() + stackNineQ2.pop();

        System.out.println("message question one: " + message);
        System.out.println("message question two: " + messageQ2);
    }


}
