import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Stack<Integer> skylines = new Stack<>();
        long count = 0;

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());

            while(!skylines.isEmpty() && skylines.peek() >= height){
                if(skylines.peek() == height){
                    skylines.pop();
                }else if(skylines.peek() > height){
                    skylines.pop();
                    count++;
                }
            }

            if(height > 0){
                skylines.add(height);
            }
        }

        int height = 0;

        while(!skylines.isEmpty() && skylines.peek() >= height){
            if(skylines.peek() == height){
                skylines.pop();
            }else if(skylines.peek() > height){
                skylines.pop();
                count++;
            }
        }

        System.out.println(count);
    }
}