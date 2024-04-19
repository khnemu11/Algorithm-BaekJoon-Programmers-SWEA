import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][]map = new char[N][M];

        Person person = new Person(0,0);

        for(int i=0;i<map.length;i++){
            String input = br.readLine();

            for(int j=0;j<map[i].length;j++){
                map[i][j] = input.charAt(j);

                if(map[i][j] == 'I'){
                    person = new Person(i,j);
                }
            }
        }

        int count = 0;
        int[]dx = {-1,1,0,0};
        int[]dy = {0,0,-1,1};
        boolean[][] visited = new boolean[N][M];
        Queue<Person> q = new LinkedList<>();
        q.add(person);
        visited[person.row][person.col] = true;

        while(!q.isEmpty()){
            Person curr = q.poll();

            if(map[curr.row][curr.col] == 'P'){
                count++;
            }

            for(int k=0;k<dx.length;k++){
                Person next = new Person(curr.row + dx[k], curr.col + dy[k]);

                if(next.row <0 || next.row>=N || next.col < 0 || next.col >=M){
                    continue;
                }
                if(visited[next.row][next.col]){
                    continue;
                }
                if(map[next.row][next.col] == 'X'){
                    continue;
                }

                visited[next.row][next.col] = true;
                q.add(next);
            }
        }

        System.out.println(count == 0 ? "TT" : count);
    }
}

class Person {
    int row;
    int col;

    public Person(int row, int col){
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "Person{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}
