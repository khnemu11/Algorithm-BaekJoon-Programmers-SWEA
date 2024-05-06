import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        PriorityQueue<Hole> pq = new PriorityQueue<>((a,b)->(a.start - b.start));

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            pq.add(new Hole(start, end));
        }

        int start = 0;
        int total = 0;

        while(!pq.isEmpty()){
            Hole curr = pq.poll();

            if(curr.end < start){
                continue;
            }

            start = Math.max(curr.start,start);
            int length = curr.end - start;

            int count = length % L != 0 ? length/L + 1 : length/L;

            total +=count;
            start += count*L;
        }

        System.out.println(total);
    }
}

class Hole{
    int start;
    int end;

    public Hole(int start, int end){
        this.start = start;
        this.end = end;
    }
}