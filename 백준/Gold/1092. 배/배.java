import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    final static int INF = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        int N = Integer.parseInt(br.readLine());
        int[] craine = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(craine);

        int M = Integer.parseInt(br.readLine());
        List<Integer> readyExpressList = new ArrayList<>(List.of(Arrays.stream(br.readLine().split(" ")).map(Integer::valueOf).toArray(Integer[]::new)));
        Collections.sort(readyExpressList,Collections.reverseOrder());

        int time = 0;

        while(!readyExpressList.isEmpty()){
            boolean move = false;

            for(int i = N-1; i>=0;i--){
                for(int j=0;j<readyExpressList.size();j++){
                    if(readyExpressList.get(j) <= craine[i]){
                        readyExpressList.remove(j);
                        move = true;
                        break;
                    }
                }
            }

            if(!move){
                time =INF;
                break;
            }
            time++;
        }

        System.out.println(time);
    }
}
