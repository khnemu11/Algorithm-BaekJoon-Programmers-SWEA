import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        ArrayDeque<Character> stack = new ArrayDeque<>();

        String ppap = "PPAP";

        for(int i=0;i<input.length();i++){
            stack.addLast(input.charAt(i));

            if(stack.size() < 4){
               continue;
            }

            StringBuilder sb = new StringBuilder();

            for(int j=0;j<4;j++){
                sb.append(stack.pollLast());
            }

            String target = sb.reverse().toString();

            if(!target.equals(ppap)){
                for(int j =0;j< target.length();j++){
                    stack.addLast(sb.charAt(j));
                }
            }else{
                stack.addLast('P');
            }
        }

        if(stack.size() == 1 && stack.peekLast() =='P'){
            System.out.println("PPAP");
        }else{
            System.out.println("NP");
        }
    }
}