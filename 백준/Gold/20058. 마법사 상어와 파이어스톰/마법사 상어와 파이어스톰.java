import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int EMPTY = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0];
        int Q = input[1];

        int[][] map = new int[(int)Math.pow(2,N)][(int)Math.pow(2,N)];

        for(int i=0;i<map.length;i++){
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[] L = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int l=0;l<L.length;l++){
            int length = (int)Math.pow(2,L[l]);
            for(int startRow=0;startRow<map.length;startRow += length){
                for(int startCol=0;startCol<map[startRow].length;startCol+= length){
                    //돌릴 영역 구하기
                    int[][] subMap = new int[length][length];

                    for(int i=0;i<length;i++){
                        for(int j=0;j<length;j++){
                            subMap[i][j] = map[startRow+i][startCol+j];
                        }
                    }

                    //시계방향 90도를 돌리기 위해 반시계방향 270도 회전
                    rotate(subMap);
                    rotate(subMap);
                    rotate(subMap);

                    //돌린 값 저장
                    for(int i=0;i<length;i++){
                        for(int j=0;j<length;j++){
                            map[startRow+i][startCol+j] = subMap[i][j];
                        }
                    }
                }
            }
            reduceIce(map);
        }

        int sum = getSum(map);
        int largestSize = getLargestSize(map);
        
        System.out.println(sum);
        System.out.println(largestSize);
    }
    //가장 큰 영역의 크기를 찾는 메소드
    public static int getLargestSize(int[][]map){
        int max =0 ;
        int[]dx = {-1,1,0,0};
        int[]dy = {0,0,-1,1};
        boolean[][] visited = new boolean[map.length][map[0].length];

        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                if(visited[i][j]){
                    continue;
                }
                if(map[i][j] == EMPTY){
                    continue;
                }

                int size = 0;
                Queue<Coordinate> q = new LinkedList<>();
                q.add(new Coordinate(i,j));

                while(!q.isEmpty()){
                    Coordinate curr = q.poll();

                    visited[curr.row][curr.col] = true;
                    size++;

                    for(int k=0;k<dx.length;k++){
                        Coordinate next = new Coordinate(curr.row + dx[k],curr.col + dy[k]);

                        if(next.row <0 || next.col <0 || next.row >= map.length || next.col>=map[0].length){
                            continue;
                        }
                        if(visited[next.row][next.col]){
                            continue;
                        }
                        if(map[next.row][next.col] == EMPTY){
                            continue;
                        }

                        visited[next.row][next.col] = true;
                        q.add(next);
                    }
                }

                max = Math.max(max,size);
            }
        }

        return max;
    }
    //모든 얼음의 합을 구하는 메소드
    public static int getSum(int[][] map){
        int sum = 0;

        for(int i=0;i<map.length;i++){
            for(int j=0;j<map.length;j++){
                sum += map[i][j];
            }
        }

        return sum;
    }

    //주변에 3개 이상의 얼음이 없을때 얼음의 양을 1 줄이는 메소드
    public static void reduceIce(int[][]map){
        int[]dx = {-1,1,0,0};
        int[]dy = {0,0,-1,1};

        List<Coordinate> reduceIceList = new ArrayList<>();

        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                int adjacentCount = 0;

                if(map[i][j] == 0){
                    continue;
                }

                for(int k=0;k<dx.length;k++){
                    Coordinate next = new Coordinate(i+dx[k],j+dy[k]);

                    if(next.row <0 || next.col <0 || next.row >= map.length || next.col>=map[0].length){
                        continue;
                    }if(map[next.row][next.col] > 0){
                        adjacentCount++;
                    }
                }

                if(adjacentCount < 3){
                    reduceIceList.add(new Coordinate(i,j));
                }
            }
        }

        for(Coordinate ice : reduceIceList){
           map[ice.row][ice.col] = map[ice.row][ice.col] > 0 ? map[ice.row][ice.col]-1: 0;
        }
    }

    //반시계방향 90도를 돌리는 메소드
    public static void rotate(int[][] arr){
        int[][] temp = new int[arr.length][arr[0].length];

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                temp[i][j] = arr[j][arr.length -1 -i];
            }
        }

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                arr[i][j] = temp[i][j];
            }
        }
    }

    public static void printArr(int[][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
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