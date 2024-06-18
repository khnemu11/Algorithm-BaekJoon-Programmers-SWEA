import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] chargeTime = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(chargeTime);

        int currentTime = 0;

        PriorityQueue<Integer> flug = new PriorityQueue<>();

        for(int i=chargeTime.length-1;i>=0;i--){
            while(flug.size()==M){
                currentTime = flug.poll();
            }

            flug.add(currentTime + chargeTime[i]);
        }

        while(!flug.isEmpty()){
            currentTime = flug.poll();
        }

        System.out.println(currentTime);
    }
}