import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Lecture> pq = new PriorityQueue<>();

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq.add(new Lecture(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }

        PriorityQueue<Integer> readyQ = new PriorityQueue<>();

        while(!pq.isEmpty()){
            Lecture curr = pq.poll();

            if(readyQ.isEmpty()){
                readyQ.add(curr.end);
            }else if(readyQ.peek() > curr.start){
                readyQ.add(curr.end);
            }else{
                readyQ.poll();
                readyQ.add(curr.end);
            }
        }

        System.out.println(readyQ.size());
    }
}

class Lecture implements Comparable<Lecture>{
    int seq;
    int start;
    int end;

    public Lecture(int seq, int start, int end){
        this.seq = seq;
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Lecture o) {
        if(this.start == o.start){
            return this.end - o.end;
        }

        return this.start - o.start;
    }
}