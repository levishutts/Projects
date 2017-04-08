package assignment6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;



public class Assignment6 {
	
	static Set<String> dict = new HashSet<String>();
    
    public static Set<String> makeDict(String file) throws Exception{
        Set<String> dict = new HashSet<String>();
        BufferedReader in = new BufferedReader(new FileReader(file));
        String line = "";
        while ((line = in.readLine()) != null) {
            dict.add(line);
        }
        in.close();
		return dict;
    }
    
    static ArrayList<String> valid;
    static Boolean[] table;
    
    public static boolean memSplit(int i, String s){
        int size = s.length();
        valid = new ArrayList<String>(size);

        if (i == size){
            return true;
        }

        if (table[i] == true) {
            return true;
        }

        for (int j = i; j < size; j++) {
            if (dict.contains(s.substring(i, j+1)) && memSplit(j+1, s)) {
                table[i] = true;
                valid.add(s.substring(i, j+1));
                return true;
            }
        }

        return false;
    }

	public static boolean iterSplit(String s) {
        int size = s.length();
        Boolean[] table = new Boolean[size+1];
        valid = new ArrayList<String>(size+1);
        int[] splitHere = new int[size+1];

        for (int i = 0; i <= size; i++) {
            table[i] = false;
        }

        table[0] = true;

        for (int i = 0; i < size; i++) {
            if (table[i] == true) {
                for (int j = i; j < size; j++) {
                    if (dict.contains(s.substring(i, j+1))) {
                        table[j+1] = true;
                        splitHere[j+1] = i;
                    }
                }
            }
        }

        if (table[size]) {
        	int i = size;
        	while (i > 0) {
        		valid.add(s.substring(splitHere[i], i));
        		i = splitHere[i];
        	}
        }

        return table[size];
    }
	
    public static void main(String[] args) throws Exception {
    	dict = makeDict("diction10k.txt");
        Scanner input = new Scanner(System.in);
        int loop = input.nextInt();
        ArrayList<String> strings = new ArrayList<>(loop);
        String string = "";
        int i;
        for(i = 0; i < loop; i++){ //input
        	string = input.next();
        	strings.add(string);
        	input.nextLine();
        }
        input.close();
        
        System.out.println();
        for(i = 0; i < loop; i++){
        	table = new Boolean[strings.get(i).length() + 1];
        	Arrays.fill(table, false);
        	System.out.println("phrase number: " + (i+1));
        	System.out.println(strings.get(i) + '\n');
        	// do stuff here
        	System.out.println("memoized attempt:");
        	if(memSplit(0, strings.get(i))){
            	System.out.println("YES, can be split");
                for (int j = valid.size() - 1; j >= 0; j--) {
                    System.out.print(valid.get(j) + " ");
                }
                System.out.println('\n');
        	}else{    	
        		System.out.println("NO, cannot be split" + '\n');
        	}
        	System.out.println("iterative attempt:");
        	if(iterSplit(strings.get(i))){
            	System.out.println("YES, can be split");
                for (int j = valid.size() - 1; j >= 0; j--) {
                    System.out.print(valid.get(j) + " ");
                }
                System.out.println('\n');
        	}else{    	
        		System.out.println("NO, cannot be split" + '\n');
        	}
        }
    }
}
