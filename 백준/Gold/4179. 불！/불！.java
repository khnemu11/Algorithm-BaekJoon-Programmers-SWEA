import java.io.*;
import java.util.*;

public class Main {
    final static int IMPOSSIBLE = -1;
    final static char WALL = '#';
    final static char ROAD = '.';
    final static char FIRE = 'F';
    final static char JIHUN = 'J';
    static int time = IMPOSSIBLE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        int[] inputInt = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int R = inputInt[0];
        int C = inputInt[1];

        char[][] maze = new char[R][C];

        Queue<Coordinate> fireQ = new LinkedList<>();
        Queue<Coordinate> personQ = new LinkedList<>();

        for(int i=0;i<R;i++){
            String inputStr = br.readLine();
            for(int j=0;j<inputStr.length();j++){
                maze[i][j] = inputStr.charAt(j);

                switch (maze[i][j]){
                    case FIRE :
                        fireQ.add(new Coordinate(i,j,0));
                        break;
                    case JIHUN :
                        maze[i][j] = ROAD;
                        personQ.add(new Coordinate(i,j,0));
                        break;
                }
            }
        }

        boolean[][] visited = new boolean[R][C];

        while (!personQ.isEmpty() && time == IMPOSSIBLE) {
            fireQ = fireMove(fireQ, maze);
            personQ = personMove(personQ, maze, visited);
//            System.out.println(personQ);
//            printArr(maze);
        }

        System.out.println(time == IMPOSSIBLE ? "IMPOSSIBLE" : time);
    }
    public static void printArr(char [][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static Queue<Coordinate> fireMove(Queue<Coordinate> q,char[][] maze){
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        Queue<Coordinate> nextQ = new LinkedList<>();

        while(!q.isEmpty()){
            Coordinate fire = q.poll();

            for(int i=0;i<dx.length;i++){
                Coordinate nextFire = new Coordinate(fire.row+dx[i], fire.col+dy[i],fire.time+1);

                if(!canGo(maze, nextFire)){
                    continue;
                }

                maze[nextFire.row][nextFire.col] = FIRE;
                nextQ.add(nextFire);
            }
        }

        return nextQ;
    }
    public static Queue<Coordinate> personMove(Queue<Coordinate> q,char[][] maze,boolean[][] visited){
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        Queue<Coordinate> nextQ = new LinkedList<>();

        while(!q.isEmpty()){
            Coordinate person = q.poll();

            visited[person.row][person.col] = true;

            if(person.row == 0 || person.row == maze.length-1 || person.col ==0 || person.col == maze[0].length-1){
                time = person.time + 1;

                return new LinkedList<>();
            }

            for(int i=0;i<dx.length;i++){
                Coordinate nextPerson = new Coordinate(person.row+dx[i], person.col+dy[i],person.time+1);

                if(!canGo(maze, nextPerson)){
                    continue;
                }else if(visited[nextPerson.row][nextPerson.col]){
                    continue;
                }

                visited[nextPerson.row][nextPerson.col] = true;
                nextQ.add(nextPerson);
            }
        }

        return nextQ;
    }
    public static boolean canGo(char[][]arr, Coordinate coordinate){
        if(coordinate.row <0 || coordinate.col<0 || coordinate.row >= arr.length || coordinate.col>=arr[0].length){
            return false;
        }else if(arr[coordinate.row][coordinate.col] == ROAD){
            return true;
        }else{
            return false;
        }
    }
}

class Coordinate{
    int row;
    int col;
    int time;

    public Coordinate(int row, int col, int time){
        this.row = row;
        this.col = col;
        this.time = time;
    }

    public String toString(){
        return row+" , "+col +" time : "+time;
    }
}