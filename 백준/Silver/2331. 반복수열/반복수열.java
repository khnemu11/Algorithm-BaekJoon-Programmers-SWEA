import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int A = Integer.parseInt(input[0]);
        int P = Integer.parseInt(input[1]);
        long num = A;

        Map<Long,Integer> map = new HashMap<>();

        while(true){
            map.put(num,map.getOrDefault(num,0)+1);

            if(map.get(num) > 2){
                break;
            }

            String numStr = String.valueOf(num);
            long next = 0;

            for(int  i=0;i<numStr.length();i++) {
                next += (long) Math.pow(Character.getNumericValue(numStr.charAt(i)), P);
            }

            num = next;
        }

        int sum = 0;

        for(Long key : map.keySet()){
            if(map.get(key) > 1){
                continue;
            }
            sum++;
        }

        System.out.println(sum);
    }
}