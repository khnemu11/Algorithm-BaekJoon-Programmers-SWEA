import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Lecture> pq = new PriorityQueue<>((x,y) -> (x.day == y.day ? y.price - x.price : y.day - x.day));

        int lastDay = 0;

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int price = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken());

            lastDay = Math.max(lastDay,day);

            pq.add(new Lecture(day,price));
        }

        int day = lastDay;
        int sum = 0;

        PriorityQueue<Lecture> readyQ = new PriorityQueue<>((x,y) -> (y.price - x.price));

        while(day > 0){
            //현재 날짜에 가능한 강의 탐색
            while(!pq.isEmpty()){
                if(pq.peek().day != day){
                    break;
                }

                readyQ.add(pq.poll());
            }
            
            //가능한 강의 중 가장 금액이 큰 강의 선택
            if(!readyQ.isEmpty()){
                Lecture lecture = readyQ.poll();
                sum += lecture.price;
            }

            day --;
        }

        System.out.println(sum);
    }
}

class Lecture{
    int day;
    int price;

    public Lecture(int day, int price){
        this.day = day;
        this.price =price;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "day=" + day +
                ", price=" + price +
                '}';
    }
}