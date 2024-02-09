import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        int N = Integer.parseInt(br.readLine());
        int[] next = new int[N];
        String[] input = br.readLine().split(" ");

        for(int i=0;i<next.length;i++){
            next[i] = Integer.parseInt(input[i]);
        }

        Deque<Integer> balloonQ = new ArrayDeque<>();

        for(int i=0;i<N;i++){
            balloonQ.add(i+1);
        }

        while(!balloonQ.isEmpty()){
            Integer balloon = balloonQ.pollFirst();
            System.out.print(balloon+" ");

            if(balloonQ.size()==0){
                break;
            }

            if(next[balloon-1] < 0){
                for(int i=0;i<next[balloon-1]*-1;i++){
                    balloonQ.addFirst(balloonQ.pollLast());
                }
            }else{
                for(int i=1;i<next[balloon-1];i++){
                    balloonQ.addLast(balloonQ.pollFirst());
                }
            }
        }
    }
}