import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());

            if(command == 1){
                int val = Integer.parseInt(st.nextToken());

                stack.add(val);
            }else if(command == 2){
                int val = stack.isEmpty() ? -1 : stack.pollLast();

                sb.append(val).append("\n");
            }else if(command == 3){
                sb.append(stack.size()).append("\n");
            }else if(command == 4){
                int val = stack.isEmpty() ? 1 : 0;

                sb.append(val).append("\n");
            }else if(command == 5){
                int val = stack.isEmpty() ? -1 : stack.peekLast();

                sb.append(val).append("\n");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
    }
}
