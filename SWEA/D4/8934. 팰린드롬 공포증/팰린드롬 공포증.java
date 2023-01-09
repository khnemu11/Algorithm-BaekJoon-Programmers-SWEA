import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
 
class Solution { 
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int test_case = 1; test_case <= T; test_case++)
        {
            String word = sc.next();
            String result ="YES";
            int [] abc = new int [3];
 
            for(int i=0;i<word.length();i++){
                abc[word.charAt(i)-'a']++;
            }
 
 
            if(Math.abs(abc[0]-abc[1])>1) result ="NO";
            else if(Math.abs(abc[1]-abc[2])>1) result ="NO";
            else if(Math.abs(abc[0]-abc[2])>1) result ="NO";
 
            bw.write("#" + test_case + " "+result+"\n");
        }
        bw.flush();
        bw.close();
    } 
}