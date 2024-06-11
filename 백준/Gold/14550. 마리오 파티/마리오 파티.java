import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int N = 0;

        while((N = sc.nextInt()) != 0){
            int S = sc.nextInt();
            int T = sc.nextInt();

            int[] board = new int[N+1];
            int[][] intervalMaxScore = new int[T+1][N+1];
            int maxScore = Integer.MIN_VALUE;

            for(int i=1;i<=N;i++){
                board[i] = sc.nextInt();
            }

            for(int i=0;i<=T;i++){
                Arrays.fill(intervalMaxScore[i],Integer.MIN_VALUE);
            }

            intervalMaxScore[0][0] = 0;

            PriorityQueue<Score> pq = new PriorityQueue<>((a,b)-> a.time - b.time);
            pq.add(new Score(0,0,0));

            while(!pq.isEmpty()){
                Score score = pq.poll();
                //현재 값이 구간 최대값이 아닌 경우
                if(intervalMaxScore[score.time][score.location] > score.value){
                    continue;
                }

                //앞으로 이동할 수 있는 횟수가 남아 있다면
                if(score.time <T){
                    for(int i=1;i<=S;i++){
                        int nextLocation= score.location + i;
                        //별을 먹을 수 있는 위치인 경우
                        if(nextLocation >= intervalMaxScore[score.time].length){
                            maxScore = Math.max(maxScore,score.value);
                        }else if(intervalMaxScore[score.time+1][nextLocation] < score.value + board[nextLocation]){
                            Score next = new Score(score.time + 1, nextLocation, score.value + board[nextLocation]);

                            intervalMaxScore[next.time][next.location] = next.value;
                            pq.add(next);
                        }
                    }
                }
            }
            System.out.println(maxScore);
        }
    }
}

class Score{
    int time;
    int location;
    int value;

    public Score(int time, int location, int value){
        this.time = time;
        this.location = location;
        this.value = value;
    }
}