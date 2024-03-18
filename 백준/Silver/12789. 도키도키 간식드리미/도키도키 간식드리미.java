import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
       int N = Integer.parseInt(br.readLine());
       int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
       Stack<Integer> line = new Stack<>();
       Queue<Integer> readyQ = new LinkedList<>();
       
       for(int num : input) {
    	   readyQ.add(num);
       }
       
       Arrays.sort(input);
       int targetIdx = 0;
       List<Integer> result = new ArrayList<>();
       
       while(!readyQ.isEmpty()) {
    	   if(!line.isEmpty() && line.peek() == input[targetIdx]) {
    		   result.add(line.pop());
    		   targetIdx++;
    	   }else if(readyQ.peek() == input[targetIdx]) {
    		   result.add(readyQ.poll());
    		   targetIdx++;
    	   }else {
    		   line.add(readyQ.poll());
    	   }
       }
       
       while(!line.isEmpty()) {
    	   if(line.peek() != input[targetIdx]) {
    		   break;
    	   }
    	   result.add(line.pop());
    	   targetIdx++;
       }
       
       if(result.size() == N) {
    	   System.out.println("Nice");
       }else {
    	   System.out.println("Sad");
       }
	}
}