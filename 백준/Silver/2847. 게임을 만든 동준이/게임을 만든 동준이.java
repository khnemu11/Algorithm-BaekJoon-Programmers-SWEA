import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for(int i=0;i<N;i++){
            stack.add(Integer.parseInt(br.readLine()));
        }

        int lastStage = stack.pollLast();

        int count = 0;

        while(!stack.isEmpty()){
            int currentStage = stack.pollLast();

            if(currentStage >=lastStage){
                count = count + currentStage - lastStage + 1;
                currentStage = lastStage - 1;
            }

            lastStage = currentStage;
        }

        System.out.println(count);
    }
}