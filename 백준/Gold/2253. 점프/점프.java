import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[] invalid = new boolean[N+1];

        for(int i=0;i<M;i++){
            int stone = Integer.parseInt(br.readLine());

            invalid[stone] = true;
        }

        int minTime = -1;
        boolean[][] visited = new boolean[N+1][N+1];

        Queue<Person> q= new LinkedList<>();
        q.add(new Person(1,0,0));

        while(!q.isEmpty()){
            Person curr = q.poll();

            visited[curr.speed][curr.location] = true;

            if(curr.location == N){
                minTime = curr.time;
                break;
            }

            //현재 속도 -1 점프
            if(curr.speed - 1 > 0
                    && curr.location + curr.speed - 1 > 0
                    && curr.location + curr.speed - 1 <= N
                    && !visited[curr.speed - 1][curr.location + curr.speed - 1]
                    && !invalid[curr.location + curr.speed - 1]){
                visited[curr.speed - 1][curr.location + curr.speed - 1] = true;
                q.add(new Person(curr.location + curr.speed - 1,curr.time+1, curr.speed -1));
            }
            //현재 속도 점프
            if(curr.speed > 0
                    && curr.location + curr.speed > 0
                    && curr.location + curr.speed <= N
                    && !visited[curr.speed][curr.location + curr.speed]
                    && !invalid[curr.location + curr.speed]){
                visited[curr.speed][curr.location + curr.speed] = true;
                q.add(new Person(curr.location + curr.speed,curr.time+1, curr.speed));
            }
            //현재 속도 + 1 점프
            if(curr.location + curr.speed + 1 <= N
                    && !visited[curr.speed + 1][curr.location + curr.speed + 1]
                    && !invalid[curr.location + curr.speed + 1]){
                visited[curr.speed + 1][curr.location + curr.speed+1] = true;
                q.add(new Person(curr.location + curr.speed + 1,curr.time+1, curr.speed+1));
            }
        }

        System.out.println(minTime);
    }
}

class Person{
    int location;
    int time;
    int speed;

    public Person(int location, int time,int speed){
        this.location = location;
        this.time = time;
        this.speed = speed;
    }
}
