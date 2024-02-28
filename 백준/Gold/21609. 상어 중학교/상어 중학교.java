import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int RAINBOW = 0;
    static final int BLACK = -1;
    static final int EMPTY = 100;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0];
        int M = input[1];

        int[][] map = new int[N][N];

        for(int i=0;i<N;i++){
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        long score = 0L;

        while(true){
            //가장 큰 블록 그룹 찾기(그룹이 없으면 종료)
            Group largestGroup = getLargestGroup(map);
//            System.out.println(largestGroup);

            if(largestGroup == null){
                break;
            }

            //블록 그룹 제거 및 점수 획득(블록의 수*블록의 수)
            score += (long) largestGroup.size *largestGroup.size;
            removeGroup(map,largestGroup.criteria);
//            System.out.println(score);
//            printArr(map);

            //중력 작용
            gravity(map);
//            printArr(map);

            //반시계 90도 회전
            rotate(map);
//            printArr(map);

            //중력 작용
            gravity(map);
//            printArr(map);
        }

        System.out.println(score);
    }
    public static void gravity(int[][] map){
        for(int i=map.length-1;i>=1;i--){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j] != EMPTY){
                    continue;
                }

                int targetRow = i-1;

                while(targetRow>=0){
                    if(map[targetRow][j] !=EMPTY){
                        break;
                    }

                    targetRow--;
                }

                if(targetRow >=0 && map[targetRow][j] != BLACK && map[targetRow][j] != EMPTY){
                    map[i][j] = map[targetRow][j];
                    map[targetRow][j] = EMPTY;
                }
            }
        }
    }
    public static void printArr(int[][] map){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                System.out.print(map[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void rotate(int[][] map){
        int[][] temp = new int[map.length][map[0].length];

        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                temp[i][j] = map[j][map.length-1 -i];
            }
        }
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                map[i][j] = temp[i][j];
            }
        }
    }
    public static void removeGroup(int[][]map,Coordinate start){
        Queue<Coordinate> q = new LinkedList<>();
        q.add(start);

        boolean[][] visited = new boolean[map.length][map[0].length];
        int[]dx = {-1,1,0,0};
        int[]dy = {0,0,-1,1};

        List<Coordinate> removeBlockList = new ArrayList<>();

        while(!q.isEmpty()){
            Coordinate curr = q.poll();

            visited[curr.row][curr.col] = true;
            removeBlockList.add(curr);

            for(int i=0;i<dx.length;i++){
                Coordinate next = new Coordinate(curr.row + dx[i], curr.col + dy[i]);

                if(next.row < 0 || next.col < 0 || next.row >= map.length || next.col >=map[0].length){
                    continue;
                }else if(visited[next.row][next.col]){
                    continue;
                }else if(map[next.row][next.col] == BLACK || map[next.row][next.col] == EMPTY){
                    continue;
                }else if(map[next.row][next.col]!=RAINBOW && map[next.row][next.col] != map[start.row][start.col]){
                    continue;
                }

                visited[next.row][next.col] = true;
                q.add(next);
            }
        }

        for(Coordinate block : removeBlockList){
            map[block.row][block.col] = EMPTY;
        }
    }
    public static Group getLargestGroup(int[][] map){
        PriorityQueue<Group> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[map.length][map[0].length];

        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j] == BLACK || map[i][j] == RAINBOW || map[i][j] == EMPTY){
                    continue;
                }
                if(visited[i][j]){
                    continue;
                }

                Group group = new Group();
                group.criteria = new Coordinate(i,j);
                group = searchGroup(map,visited,group);

                if(group.size < 2){
                    continue;
                }

                pq.add(group);
            }
        }

        return pq.isEmpty() ? null : pq.poll();
    }
    public static Group searchGroup(int[][] map,boolean[][] visited,Group group){
        Queue<Coordinate> q = new LinkedList<>();
        q.add(group.criteria);

        int[]dx = {-1,1,0,0};
        int[]dy = {0,0,-1,1};

        List<Coordinate> rainbowList = new ArrayList<>();

        //그룹 탐색
        while(!q.isEmpty()){
            Coordinate curr = q.poll();

            visited[curr.row][curr.col] = true;

            group.size++;

            if(map[curr.row][curr.col] == RAINBOW){
                group.rainbowCount++;
                rainbowList.add(new Coordinate(curr.row, curr.col));
            }

            for(int i=0;i<dx.length;i++){
                Coordinate next = new Coordinate(curr.row + dx[i], curr.col + dy[i]);

                if(next.row < 0 || next.col < 0 || next.row >= map.length || next.col >=map[0].length){
                    continue;
                }else if(visited[next.row][next.col]){
                    continue;
                }else if(map[next.row][next.col] == BLACK || map[next.row][next.col] == EMPTY){
                    continue;
                }else if(map[next.row][next.col]!=RAINBOW && map[next.row][next.col] != map[group.criteria.row][group.criteria.col]){
                    continue;
                }

                visited[next.row][next.col] = true;
                q.add(next);
            }
        }

        //무지개 블록 탐색 취소
        for(Coordinate rainbow : rainbowList){
            visited[rainbow.row][rainbow.col] = false;
        }

        return group;
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
class Group implements Comparable<Group>{
    Coordinate criteria;
    int size;
    int rainbowCount;

    @Override
    public int compareTo(Group o) {
        if(this.size == o.size){
            if(this.rainbowCount == o.rainbowCount){
                if(this.criteria.row == o.criteria.row){
                    return o.criteria.col - this.criteria.col;
                }
                return o.criteria.row - this.criteria.row;
            }
            return o.rainbowCount - this.rainbowCount;
        }
        return o.size - this.size;
    }

    @Override
    public String toString() {
        return "Group{" +
                "criteria=" + criteria +
                ", size=" + size +
                ", rainbowCount=" + rainbowCount +
                '}';
    }
}
