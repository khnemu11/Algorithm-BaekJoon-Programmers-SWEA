import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static int START = 0;
    static int END = 1;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Line> readyPQ = new PriorityQueue<>();

        for(int i=0;i<N;i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            readyPQ.add(new Line(input[0],input[1]));
        }

        PriorityQueue<Line> pq = new PriorityQueue<>();

        long sum = 0L;

        while(!readyPQ.isEmpty()){
            Line curr = readyPQ.poll();

            while(!pq.isEmpty()){
                Line min = pq.poll();

                if(curr.start <= min.end){
                    min.end = Math.max(curr.end,min.end);
                    pq.add(min);
                    break;
                }

                sum += min.end - min.start;
            }

            if(pq.isEmpty()){
                pq.add(curr);
            }
        }

        while(!pq.isEmpty()){
            Line min = pq.poll();
            sum += min.end - min.start;
        }

        System.out.println(sum);
    }
}

class Line implements Comparable<Line> {
    int start;
    int end;

    public Line(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Line o) {
        if(this.start == o.start){
            return this.end - o.end;
        }
        return this.start - o.start;
    }
}