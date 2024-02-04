import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    final static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        int S = Integer.parseInt(br.readLine());

        int[][] time = new int[S+1][S+1];

        for(int i=0;i<=S;i++){
            Arrays.fill(time[i],INF);
        }

        time[0][1] = 0;

        PriorityQueue<Imoj> q = new PriorityQueue<>();
        q.add(new Imoj(0,1,0));

        while(!q.isEmpty()){
            Imoj imoj = q.poll();
            if(time[imoj.clipBoard][imoj.length] < imoj.time){
                continue;
            }
            if(imoj.length == S){
                System.out.println(imoj.time);
                break;
            }

            //삭제한 경우
            if(imoj.length - 1 > 0 && time[imoj.clipBoard][imoj.length-1] > imoj.time + 1){
                Imoj next = new Imoj(imoj.time+1, imoj.length - 1, imoj.clipBoard);
                time[imoj.clipBoard][next.length] = next.time;
                q.add(next);
            }

            //기존 클립보드 사용한 경우
            if(imoj.clipBoard != 0 && imoj.length + imoj.clipBoard <=S
                    && time[imoj.clipBoard][imoj.length + imoj.clipBoard] > imoj.time + 1){
                Imoj next = new Imoj(imoj.time + 1, imoj.length + imoj.clipBoard,imoj.clipBoard);
                time[imoj.clipBoard][next.length] = next.time;
                q.add(next);
            }

            //복사하는 경우
            if(time[imoj.length][imoj.length] > imoj.time+1){
                Imoj next = new Imoj(imoj.time+1, imoj.length,imoj.length);
                time[next.clipBoard][next.length] = next.time;
                q.add(next);
            }

        }
    }
}
class Imoj implements Comparable<Imoj>{
    int time;
    int length;
    int clipBoard;
    public Imoj(int time, int length,int clipBoard){
        this.time = time;
        this.length = length;
        this.clipBoard = clipBoard;
    }

    @Override
    public String
    toString() {
        return "Imoj{" +
                "time=" + time +
                ", length=" + length +
                ", clipBoard=" + clipBoard +
                '}';
    }

    @Override
    public int compareTo(Imoj o) {
        return this.time - o.time;
    }
}

