import java.io.*;
import java.util.*;


/*
*
*  dp[i][j] = Math.min(dp[i][j-1],dp[i-1][j], dp[i-1][j-1]) (if arr[i][j] == 1)
*             0 (if arr[i][j] == 0)
* */
public class Main {
    final static char EMPTY = '.';
    final static char WALL = '#';
    final static char PEOPLE = '@';
    final static char FIRE = '*';

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        int T = Integer.parseInt(br.readLine());

        for(int testCase = 1; testCase<=T; testCase++){
            String input = br.readLine();
            String[] splitInput = input.split(" ");

            int w = Integer.parseInt(splitInput[0]);
            int h = Integer.parseInt(splitInput[1]);

            char[][] buildingMap = new char[h][w];

            Queue<Coordinate> peopleQ = new LinkedList<>();
            Queue<Coordinate> fireQ = new LinkedList<>();

            for(int i=0;i<h;i++){
                input = br.readLine();

                for(int j=0;j<input.length();j++){
                    buildingMap[i][j] = input.charAt(j);

                    if(buildingMap[i][j] == PEOPLE){
                        peopleQ.add(new Coordinate(i,j));
                    }
                    else if(buildingMap[i][j] == FIRE){
                        fireQ.add(new Coordinate(i,j));
                    }
                }
            }

            int[]dx = {-1,1,0,0};
            int[]dy = {0,0,-1,1};
            int time = 0;
            boolean find = false;

            while(!peopleQ.isEmpty()){
                Queue<Coordinate> nextFireQ = new LinkedList<>();

                while(!fireQ.isEmpty()){
                    Coordinate fire = fireQ.poll();

                    for(int i=0;i<dx.length;i++){
                        Coordinate nextFire = new Coordinate(fire.row + dx[i],fire.col+ dy[i]);

                        if(outOfArray(nextFire,buildingMap)){
                            continue;
                        }
                        if(buildingMap[nextFire.row][nextFire.col] == WALL){
                            continue;
                        }
                        if(buildingMap[nextFire.row][nextFire.col] == FIRE){
                            continue;
                        }

                        buildingMap[nextFire.row][nextFire.col] = FIRE;

                        nextFireQ.add(nextFire);
                    }
                }

                fireQ = nextFireQ;

                Queue<Coordinate> nextPeopleQ = new LinkedList<>();

                while(!peopleQ.isEmpty()){
                    Coordinate people = peopleQ.poll();

                    if(canEscape(people,buildingMap)){
                        find = true;
                        break;
                    }

                    for(int i=0;i<dx.length;i++){
                        Coordinate nextPeople = new Coordinate(people.row + dx[i],people.col+ dy[i]);

                        if(outOfArray(nextPeople,buildingMap)){
                            continue;
                        }
                        if(buildingMap[nextPeople.row][nextPeople.col] != EMPTY){
                            continue;
                        }

                        buildingMap[nextPeople.row][nextPeople.col] = PEOPLE;

                        nextPeopleQ.add(nextPeople);
                    }
                }

                peopleQ = nextPeopleQ;

                if(find){
                    time++; //맵 밖으로 나가는 시간 포함
                    break;
                }

                time++;
            }
            
            String result = find ? String.valueOf(time) : "IMPOSSIBLE";
            System.out.println(result);
        }
    }

    public static boolean outOfArray(Coordinate coordinate, char[][] map){
        if(coordinate.row < 0 || coordinate.col < 0 || coordinate.row >= map.length || coordinate.col >=map[0].length){
            return true;
        }
        return false;
    }
    public static boolean canEscape(Coordinate coordinate, char[][] map){
        if(coordinate.row == 0 || coordinate.col == 0 || coordinate.row == map.length-1 || coordinate.col == map[0].length-1){
            return true;
        }
        return false;
    }
}

class Coordinate{
    int row;
    int col;

    public Coordinate(){}

    public Coordinate(int row, int col){
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}