import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> findSmallerWomanQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> findTallerWomanQ = new PriorityQueue<>();
        PriorityQueue<Integer> findSmallerManQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> findTallerManQ = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            int height = Integer.parseInt(st.nextToken());

            if(height < 0){
                findSmallerWomanQ.add(height);
            }else{
                findTallerWomanQ.add(height);
            }
        }

        st = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            int height = Integer.parseInt(st.nextToken());

            if(height < 0){
                findSmallerManQ.add(height);
            }else{
                findTallerManQ.add(height);
            }
        }

        int count = 0;

        while(!findSmallerWomanQ.isEmpty()){
            int availableMaxHeight = Math.abs(findSmallerWomanQ.poll()) - 1;

            if(!findTallerManQ.isEmpty() && findTallerManQ.peek() <= availableMaxHeight){
                findTallerManQ.poll();
                count++;
            }
        }

        while(!findSmallerManQ.isEmpty()){
            int availableMaxHeight = Math.abs(findSmallerManQ.poll()) - 1;

            if(!findTallerWomanQ.isEmpty() && findTallerWomanQ.peek() <= availableMaxHeight){
                findTallerWomanQ.poll();
                count++;
            }
        }

        System.out.println(count);
    }
}
