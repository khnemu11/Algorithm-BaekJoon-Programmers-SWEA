import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        int[]input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int N = input[0];
        int K = input[1];

        int[]num = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        Stack<Integer> stack = new Stack<>();

        int deleteCount = 0;

        stack.add(num[0]);

        for(int i=1;i<num.length;i++){
            if(stack.peek() >= num[i]){
                stack.add(num[i]);
            }else{
                while(deleteCount < K && !stack.isEmpty() && stack.peek() < num[i]){
                    deleteCount++;
                    stack.pop();
                }
                stack.add(num[i]);
            }
        }

        while(deleteCount < K){
            deleteCount++;
            stack.pop();
        }

        StringBuilder sb = new StringBuilder();

        String result = Arrays.stream(stack.toArray(new Integer[0])).map(String::valueOf).collect(Collectors.joining("","",""));
        System.out.println(result);
    }
}
