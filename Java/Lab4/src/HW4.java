import java.util.Scanner;

public class HW4 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String input;
        String task;
        int num;
        AVL<Integer> myAVL = new AVL<Integer>();

        while (sc.hasNextLine()) {
        	boolean end = false;
            input = sc.nextLine();
            String[] phrases = input.split(" ");
            task = phrases[0];
            switch(task) {
                case "insert" :
                    num = Integer.parseInt(phrases[1]);
                    myAVL.insert(num);
                    break;
                case "delete" :
                    num = Integer.parseInt(phrases[1]);
                    myAVL.delete(num);
                    break;
                case "search" :
                    num = Integer.parseInt(phrases[1]);
                    Node<Integer> found = myAVL.find(num);
                    if (found == null){
                        System.out.println("Not Found");
                    } else {
                        System.out.println("Found");
                    }
                    break;
                case "traverse" :
                    myAVL.traverse("preorder", myAVL.getRoot());
                    System.out.println();
                    break;
                case "exit" :
                    System.out.println("Successful Exit");
                    end = true;
                    break;
                default:
                    break;
            }
            if (end) {
                break;
            }
        }
        sc.close();
    }
}