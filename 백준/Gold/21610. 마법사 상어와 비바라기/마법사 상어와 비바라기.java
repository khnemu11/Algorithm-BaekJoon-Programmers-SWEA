import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static int N,M;
    public static int[] dx = {0,-1,-1,-1,0,1,1,1};
    public static int[] dy = {-1,-1,0,1,1,1,0,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        long[][] map = new long[N][N];

        for(int i=0;i<N;i++){
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        }

        Queue<Coordinate> cloudQ = new LinkedList<>();
        
        //시작 구름 넣기
        cloudQ.add(new Coordinate(N-1,0));
        cloudQ.add(new Coordinate(N-1,1));
        cloudQ.add(new Coordinate(N-2,0));
        cloudQ.add(new Coordinate(N-2,1));

        for(int round=0;round<M;round++){
            input = br.readLine().split(" ");
            int direction = Integer.parseInt(input[0]) - 1;
            int speed = Integer.parseInt(input[1]);
            boolean[][] visited = new boolean[N][N];

            cloudQ = rain(cloudQ,direction,speed,visited,map);
            waterCopyBug(cloudQ,map);
            cloudQ = makeCloud(visited,map);
        }

        long waterSum = getSum(map);

        System.out.println(waterSum);
    }
    public static Queue<Coordinate> rain(Queue<Coordinate> q, int direction, int speed,boolean[][] visited, long[][]map){
        Queue<Coordinate> movedCloudQ = new LinkedList<>();

        while(!q.isEmpty()){
            Coordinate cloud = q.poll();

            int nextRow = (cloud.row + dx[direction]*speed + speed*N) % N;
            int nextCol = (cloud.col + dy[direction]*speed + speed*N) % N;

            Coordinate next = new Coordinate(nextRow,nextCol);
            //방문처리
            visited[next.row][next.col] = true;
            //비내리기
            map[next.row][next.col]++;

            movedCloudQ.add(next);
        }

        return movedCloudQ;
    }
    public static void waterCopyBug(Queue<Coordinate> cloudQ,long[][]map){
        while(!cloudQ.isEmpty()){
            Coordinate cloud = cloudQ.poll();

            //대각 이동 벡터
            int[] diagDX = {-1,-1,1,1};
            int[] diagDy = {-1,1,-1,1};

            //물복사버그 시전
            for(int i=0;i<diagDX.length;i++){
                Coordinate next = new Coordinate(cloud.row+diagDX[i],cloud.col+diagDy[i]);

                //맵 밖으로 넘어가는 경우
                if(next.row < 0 || next.col <0 || next.row >= N || next.col >= N){
                    continue;
                }
                //믈이 없는 바구니인 경우
                if(map[next.row][next.col] ==0){
                    continue;
                }

                map[cloud.row][cloud.col]++;
            }
        }
    }
    public static Queue<Coordinate> makeCloud(boolean[][] visited,long[][]map){
        Queue<Coordinate> cloudQ = new LinkedList<>();

        for(int row=0;row<map.length;row++){
            for(int col=0;col<map[row].length;col++){
                if(visited[row][col]){
                    continue;
                }else if(map[row][col] < 2){
                    continue;
                }

                cloudQ.add(new Coordinate(row,col));
                map[row][col] -= 2;
            }
        }

        return cloudQ;
    }
    public static long getSum(long[][]map){
        long sum =0;

        for(int row=0;row<map.length;row++){
            for(int col=0;col<map[row].length;col++){
                sum += map[row][col];
            }
        }

        return sum;
    }
}

class Coordinate{
    int row;
    int col;

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