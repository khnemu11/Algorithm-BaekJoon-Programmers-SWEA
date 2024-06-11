import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] beforeVaccine = new int[N][M];
        int[][] afterVaccine = new int[N][M];

        for(int i=0;i<N;i++){
            beforeVaccine[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for(int i=0;i<N;i++){
            afterVaccine[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int vaccineValue = findVaccineValue(beforeVaccine,afterVaccine);

        boolean valid = false;

        if(isSame(beforeVaccine,afterVaccine)){
            valid = true;
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                int[][] result = spread(new Coordinate(i,j),beforeVaccine,vaccineValue);

                if(isSame(result,afterVaccine)){
                    valid=true;
                    break;
                }
            }

            if(valid){
                break;
            }
        }

        System.out.println(valid ? "YES" : "NO");
    }

    public static int findVaccineValue(int[][] a,int[][] b){
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[i].length;j++){
                if(a[i][j] != b[i][j]){
                    return b[i][j];
                }
            }
        }

        return -1;
    }

    public static int[][] spread(Coordinate start, int[][] map,int vaccineValue){
        int[][] result = new int[map.length][map[0].length];
        
        //백신 주입 이전 촬영 사진 복사
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                result[i][j] = map[i][j];
            }
        }
        int changeValue = map[start.row][start.col];
        boolean[][] visited = new boolean[map.length][map[0].length];

        Queue<Coordinate> q = new LinkedList<>();
        q.add(start);

        int[]dx = {-1,1,0,0};
        int[]dy = {0,0,-1,1};
        
        //백신을 상하좌우로 같은 영역만 전파
        while(!q.isEmpty()){
            Coordinate curr = q.poll();

            result[curr.row][curr.col] = vaccineValue;
            visited[curr.row][curr.col] = true;

            for(int k=0;k<dx.length;k++){
                Coordinate next = new Coordinate(curr.row + dx[k],curr.col + dy[k]);

                //맵 밖으로 나가는 경우
                if(next.row < 0 || next.row >=map.length || next.col <0 || next.col >=map[0].length){
                    continue;
                }
                //이미 방문한 경우
                if(visited[next.row][next.col]){
                    continue;
                }
                //다른 영역인 경우
                if(map[next.row][next.col] != changeValue){
                    continue;
                }

                visited[next.row][next.col] = true;
                q.add(next);
            }
        }

        return result;
    }

    public static boolean isSame(int[][]a, int[][]b){
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[i].length;j++){
                if(a[i][j] != b[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}

class Coordinate{
    int row;
    int col;

    public Coordinate(int row, int col){
        this.row = row;
        this.col = col;
    }
}