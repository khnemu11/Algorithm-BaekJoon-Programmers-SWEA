import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][]arr = new int[N+1][N+1];
        int[][]visited = new int[N][N];

        for(int i=0;i<N;i++){
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.fill(visited[i],Integer.MAX_VALUE);
        }

        visited[0][0] = 0;
        Person start = new Person(0,0,0);
        PriorityQueue<Person> pq = new PriorityQueue<>((a,b) -> a.cost - b.cost);
        pq.add(start);

        int[]dx = {1,0};
        int[]dy = {0,1};

        while(!pq.isEmpty()){
            Person curr = pq.poll();

            if(curr.cost > visited[curr.row][curr.col]){
                continue;
            }

            for(int i=0;i<dx.length;i++){
                Person next = new Person(curr.row+dx[i],curr.col+dy[i],curr.cost);

                if(next.row>=N || next.col >=N){
                    continue;
                }

                int differ = arr[next.row][next.col] - arr[curr.row][curr.col];
                next.cost =  (differ < 0 ? 0 : differ + 1) + curr.cost;

                if(visited[next.row][next.col] <= next.cost){
                    continue;
                }

                visited[next.row][next.col] = next.cost;
                pq.add(next);
            }
        }

        System.out.println(visited[N-1][N-1]);
    }
}
class Person{
    int row;
    int col;
    int cost;

    public Person(int row, int col,int cost){
        this.row = row;
        this.col = col;
        this.cost = cost;
    }
}