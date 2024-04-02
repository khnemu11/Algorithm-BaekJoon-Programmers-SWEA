import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<Ball> ballList = new ArrayList<>();

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());

            ballList.add(new Ball(i,color,size));
        }

        Collections.sort(ballList, new Comparator<Ball>() {
            @Override
            public int compare(Ball o1, Ball o2) {
                return o1.size - o2.size;
            }
        });

        int[] colorSum = new int[N+1];
        int[] answer = new int[N];
        int sum = 0;
        int nextBallIdx = 0;
        Ball next = ballList.get(nextBallIdx);

        for(int i=0;i<ballList.size();i++){
            Ball curr = ballList.get(i);

            while(next.size < curr.size){
                sum+=next.size;
                colorSum[next.color] +=next.size;
                next = ballList.get(++nextBallIdx);
            }

            answer[curr.idx] = sum - colorSum[curr.color];
        }

        for(int a : answer){
            System.out.println(a);
        }
    }
}

class Ball{
    int idx;
    int color;
    int size;

    public Ball(int idx,int color,int size){
        this.idx = idx;
        this.color = color;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Ball{" +
                "color=" + color +
                ", size=" + size +
                '}';
    }
}