import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        System.out.println(validSameStone(A,B,C));
    }

    public static int validSameStone(int A, int B, int C){
        if((A+B+C)%3 !=0){
            return 0;
        }

        int target = (A+B+C)/3;
        int sum = A+B+C;

        boolean[][]visited = new boolean[1501][1501];

        Queue<int []> q = new LinkedList<>();
        q.add(new int[]{A,B,C});

        visited[A][B] = true;

        while(!q.isEmpty()){
            int[] curr = q.poll();

            if(curr[0] == curr[1] && curr[1] == curr[2]){
                return 1;
            }

            for(int i=0;i<curr.length;i++){
                for(int j=0;j<curr.length;j++){
                    int X = curr[i];
                    int Y = curr[j];

                    if(X >= Y){
                        continue;
                    }

                    int k = 3 - i - j;

                    int[] next = new int[3];
                    next[i] = X+X;
                    next[j] = Y-X;
                    next[k] = curr[k];

                    if(isValid(next,visited)){
                        visited[next[0]][next[1]] = true;
                        q.add(next);
                    }
                }
            }
        }

        return 0;
    }

    public static boolean isValid(int[] stone,boolean[][] visited){
        if(stone[0] <0 || stone[1] <0){
            return false;
        }
        if(stone[0] >= visited.length || stone[1] >= visited.length){
            return false;
        }
        if(visited[stone[0]][stone[1]]){
            return false;
        }
        return true;
    }
}