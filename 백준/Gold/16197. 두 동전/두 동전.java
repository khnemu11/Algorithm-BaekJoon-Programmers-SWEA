import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        Coin[] coins = new Coin[2];
        int idx = 0;

        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            for (int j = 0; j < input.length(); j++) {
                map[i][j] = input.charAt(j);

                if (map[i][j] == 'o') {
                    coins[idx++]  = new Coin(i, j);
                    map[i][j] = '.';
                }
            }
        }

        Status start = new Status(coins[0],coins[1],0);

        int count = getMinCount(map,start);
        System.out.println(count);
    }

    public static int getMinCount(char[][]map, Status start){
        Queue<Status> q = new LinkedList<>();
        q.add(start);

        int[] dx = {0,0,-1,1};
        int[] dy = {-1,1,0,0};
        int count = -1;
        boolean[][][][] visited = new boolean[map.length][map[0].length][map.length][map[0].length];
        visited[start.first.row][start.first.col][start.second.row][start.second.col] = true;

        while(!q.isEmpty()){
            Status curr = q.poll();

            if(curr.count >= 10){
                continue;
            }

            for(int i=0;i<dx.length;i++){
                Coin first = new Coin(curr.first.row + dx[i],curr.first.col+ dy[i]);
                boolean firstOut = outOfMap(map,first);

                if(!firstOut && map[first.row][first.col] == '#'){
                    first = new Coin(curr.first.row,curr.first.col);
                }

                Coin second = new Coin(curr.second.row + dx[i],curr.second.col+ dy[i]);
                boolean secondOut = outOfMap(map,second);

                if(!secondOut && map[second.row][second.col] == '#'){
                    second = new Coin(curr.second.row,curr.second.col);
                }

                if(secondOut && firstOut){
                    continue;
                }else if((firstOut && !secondOut) || (!firstOut && secondOut)){
                    return curr.count+1;
                }if(visited[first.row][first.col][second.row][second.col]){
                    continue;
                }

                visited[first.row][first.col][second.row][second.col] = true;
                q.add(new Status(first,second,curr.count+1));
            }
        }

        return count;
    }

    public static boolean outOfMap(char[][] map, Coin coin){
        return coin.row <0 || coin.row >=map.length || coin.col <0 || coin.col >=map[0].length;
    }
}

class Status{
    Coin first;
    Coin second;
    int count;

    public Status(Coin first, Coin second, int count){
        this.first = first;
        this.second = second;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Status{" +
                "first=" + first +
                ", second=" + second +
                ", count=" + count +
                '}';
    }
}

class Coin{
    int row;
    int col;

    public Coin(int row, int col){
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "Coin{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}