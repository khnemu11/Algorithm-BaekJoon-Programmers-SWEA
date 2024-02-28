import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] directionX = {1,0,-1,0};
        int[] directionY = {0,-1,0,1};

        boolean[][] visited = new boolean[101][101];

        for(int i=0;i<N;i++){
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Coordinate startDirectionVector = new Coordinate(directionX[input[2]],directionY[input[2]]);

            List<Coordinate> directionVectors = getDirection(startDirectionVector,input[3]);

            Coordinate curr = new Coordinate(input[0],input[1]);
            visited[curr.x][curr.y] = true;

            for(Coordinate directionVector : directionVectors){
                Coordinate next = new Coordinate(curr.x + directionVector.x, curr.y + directionVector.y);

                visited[next.x][next.y] = true;
                curr = next;
            }
        }

        int count = 0;

        for(int x=1; x<=100; x++){
            for(int y=1; y<=100; y++){
                if(!visited[x][y]){
                    continue;
                }
                if(!visited[x-1][y]){
                    continue;
                }
                if(!visited[x][y-1]){
                    continue;
                }
                if(!visited[x-1][y-1]){
                    continue;
                }
                count++;
            }
        }

        System.out.println(count);
    }

    public static List<Coordinate> getDirection(Coordinate start,int generation){
        List<Coordinate> directionVectors = new ArrayList<>();
        directionVectors.add(start);

        for(int i=1;i<=generation;i++){
            List<Coordinate> next = new ArrayList<>();

            for(Coordinate vector : directionVectors){
                Coordinate rotateVector = new Coordinate(vector.y,-vector.x);
                rotateVector = new Coordinate(rotateVector.y,-rotateVector.x);
                rotateVector = new Coordinate(rotateVector.y,-rotateVector.x);

                next.add(rotateVector);
            }

            for(int j=next.size()-1;j>=0;j--){
                Coordinate reverseVector = new Coordinate(-next.get(j).x,-next.get(j).y);
                directionVectors.add(reverseVector);
            }
        }

        return directionVectors;
    }
}
class Coordinate{
    int x;
    int y;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }
}
