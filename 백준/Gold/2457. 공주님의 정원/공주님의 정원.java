import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Flower[] flowers = new Flower[N+1];
        int idx = 0;

        PriorityQueue<Flower> pq = new PriorityQueue<>();

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            pq.add(new Flower(
                    LocalDate.of(2020,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()))
                    ,LocalDate.of(2020,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()))));
        }


        LocalDate start = LocalDate.of(2020,3,1);
        LocalDate end = LocalDate.of(2020,12,1);
        boolean valid = true;
        int count = 0;

        //출발 꽃 찾기
        while(start.isBefore(end) ){
            boolean find = false;

            LocalDate tempEnd = start;

            while(!pq.isEmpty()){
                if(pq.peek().start.isAfter(start)){
                    break;
                }

                Flower curr = pq.poll();

                if(curr.end.isAfter(tempEnd)) {
                    tempEnd  = curr.end;
                    find = true;
                }
            }

            if(find){
                start = tempEnd;
                count++;
            }else{
                valid= false;
                break;
            }
        }

        if(valid){
            System.out.println(count);
        }else{
            System.out.println(0);
        }

    }
}

class Flower implements Comparable<Flower>{
    LocalDate start;
    LocalDate end;

    public Flower(LocalDate start, LocalDate end){
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Flower o) {
        if(this.start.equals(o.start)) {
            if(this.end.isBefore(o.end)){
                return -1;
            }
            return 1;
        }else if(start.isBefore(o.start)){
            return -1;
        }

        return 1;
    }
}
