import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[]maze = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        boolean[]visited = new boolean[N];

        Queue<Person> q = new LinkedList<>();
        q.add(new Person(0,0));

        int time = -1;

        while(!q.isEmpty()){
            Person person = q.poll();
            visited[person.location] = true;

            if(person.location == N-1){
                time = person.time;
                break;
            }

            for(int i=0;i<=maze[person.location];i++){
                if(i+person.location >= maze.length || visited[i+person.location]){
                   continue;
                }

                visited[i+person.location] = true;
                q.add(new Person(i+ person.location, person.time+1));
            }
        }

        System.out.println(time);
    }
}
class Person{
    int location;
    int time;

    public Person(int location, int time){
        this.location = location;
        this.time = time;
    }
}