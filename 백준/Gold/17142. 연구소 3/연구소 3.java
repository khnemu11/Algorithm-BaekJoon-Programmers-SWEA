import java.io.*;
import java.util.*;

public class Main {
    final static int EMPTY = 0;
    final static int WALL = 1;
    final static int INACTIVE_VIRUS = 2;
    final static int ACTIVE_VIRUS = 3;
    final static int INF = Integer.MAX_VALUE;

    static int minTime = INF;
    static int N;
    static int M;
    static List<Virus> virusStartList = new ArrayList<>();
    static int[][] lab;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = input[0];
        M = input[1];
        lab = new int[N][N];

        for(int i=0;i<lab.length;i++){
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j=0;j<input.length;j++){
                lab[i][j] = input[j];

                if(lab[i][j] == INACTIVE_VIRUS){
                    virusStartList.add(new Virus(i,j,0));
                }
            }
        }

//        System.out.println(virusStartList);

        selectVirus(0,new ArrayList<>());

        System.out.println(minTime == INF ? -1 : minTime);
    }

    public static void selectVirus(int idx, List<Virus> selected){
//        System.out.println(selected);
        if(selected.size() == M){
            spread(selected);
//            System.out.println(selected);
            return;
        }
        if(virusStartList.size() <= idx){
            return ;
        }
        selectVirus(idx+1,selected);

        selected.add(virusStartList.get(idx));
        selectVirus(idx+1,selected);
        selected.remove(selected.size()-1);
    }

    public static void spread(List<Virus> virusList){
//        System.out.println("선택됨 : "+virusList);

        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        int[][] simulatedLab = new int[N][N];
        boolean[][] visited = new boolean[N][N];

        for(int i=0;i<N;i++){
            System.arraycopy(lab[i],0,simulatedLab[i],0,N);
        }

        Queue<Virus> q = new LinkedList<>(virusList);
        int time = 0;

        while(!q.isEmpty()){
            int size = q.size();
//            System.out.println("현재시간 : "+time);

            while(size-->0){
                Virus curr = q.poll();
//              System.out.println(curr);
//                time = Math.max(time,curr.time);

                visited[curr.row][curr.col] = true;
                simulatedLab[curr.row][curr.col] = ACTIVE_VIRUS;

                for(int i=0;i< dx.length;i++){
                    Virus next = new Virus(curr.row + dx[i],curr.col+dy[i],curr.time+1);

                    if(!canSpread(next,simulatedLab,visited)){
                        continue;
                    }

                    visited[next.row][next.col] = true;
                    q.add(next);
                }
            }

            if(checkAllVirus(simulatedLab)){
                minTime = Math.min(minTime,time);
                break;
            }

            time++;
//            printArr(simulatedLab);
        }

//        System.out.println("걸린시간 : "+time);
    }

    public static boolean checkAllVirus(int[][] lab){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(lab[i][j] == EMPTY){
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean canSpread(Virus virus, int[][] lab,boolean[][] visited){
        if(virus.row<0 || virus.col<0 || virus.row>= N || virus.col>=N){
            return false;
        }
        if(visited[virus.row][virus.col]){
            return false;
        }
        if(lab[virus.row][virus.col]==EMPTY || lab[virus.row][virus.col]==INACTIVE_VIRUS){
            return true;
        }
        return false;
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

class Virus{
    int row;
    int col;
    int time;

    public Virus(int row, int col,int time){
        this.row = row;
        this.col = col;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "row=" + row +
                ", col=" + col +
                ", time="+time+
                '}';
    }
}