import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    final static int BASE_FEED = 5; //기본 양분
    final static int AVAILABLE_SPREAD_AGE = 5; //번식 가능 나이 배수
    final static int[] dx = {-1,-1,-1,0,0,1,1,1}; //행 이동 벡터
    final static int[] dy = {-1,0,1,-1,1,-1,0,1}; //열 이동 벡터

    static int N;  //땅 크기
    static int M;  //초기 나무 개수
    static int K;  //시간

    static int[][] feedArr;    //현재 양분 배열
    static int[][] extraFeedArr;    //추가되는 양분 배열
    static PriorityQueue<Tree> growQ = new PriorityQueue<>(); //성장하려는 나무 목록
    static Queue<Tree> deadQ = new LinkedList<>();  //죽은 나무 목록
    static Queue<Tree> spreadQ = new LinkedList<>(); //번식하는 나무 목록

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();

        N = input[0];
        M = input[1];
        K = input[2];

        //기본 양분(=5)으로 초기화
        feedArr = new int[N][N];

        for(int i=0;i<N;i++){
            Arrays.fill(feedArr[i],BASE_FEED);
        }

        extraFeedArr = new int[N][N];
        //추가되는 양분 초기화
        for(int i=0;i<N;i++){
            extraFeedArr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        }
        //초기 나무 등록
        for(int i=0;i<M;i++){
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();

            Tree tree = new Tree(input[0]-1,input[1]-1,input[2]);
            growQ.add(tree);
        }

        for(int i=0;i<K;i++){
            spring();
            summer();
            fall();
            winter();
        }

        System.out.println(growQ.size());
    }

    public static void spring(){
        PriorityQueue<Tree> nextQ = new PriorityQueue<>();

        while(!growQ.isEmpty()){
            Tree tree = growQ.poll();

            if(feedArr[tree.row][tree.col] < tree.age){
                deadQ.add(tree);
                continue;
            }

            feedArr[tree.row][tree.col] -= tree.age;
            tree.age++;

            if(tree.age % AVAILABLE_SPREAD_AGE == 0){
                spreadQ.add(tree);
            }

            nextQ.add(tree);
        }

        growQ = nextQ;
    }

    public static void summer(){
        while(!deadQ.isEmpty()){
            Tree tree = deadQ.poll();

            int feed = tree.age / 2;

            feedArr[tree.row][tree.col] += feed;
        }
    }

    public static void fall(){
        while(!spreadQ.isEmpty()){
            Tree tree = spreadQ.poll();

            for(int i=0;i<dx.length;i++){
                Tree next = new Tree(tree.row + dx[i],tree.col+dy[i],1);

                if(next.row < 0 || next.col < 0 ||next.row >=N || next.col>=N){
                    continue;
                }

                growQ.add(next);
            }
        }
    }

    public static void winter(){
        for(int row=0;row<N;row++){
            for(int col=0;col<N;col++){
                feedArr[row][col] +=extraFeedArr[row][col];
            }
        }
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

class Tree extends Coordinate implements Comparable<Tree>{
    int age;

    public Tree(int row, int col, int age){
        super(row,col);
        this.age = age;
    }

    @Override
    public int compareTo(Tree o) {
        return this.age - o.age;
    }
}