package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] map = new char[12][6];
        
        //입력값 넣기
        for(int i=0;i<map.length;i++){
            String input = br.readLine();
            for(int j=0;j<map[0].length;j++){
                map[i][j] = input.charAt(j);
            }
        }

       int count = gameStart(map);
       System.out.println(count);
    }
    public static int gameStart(char[][] map){
        int chain = -1;
        boolean chainCheck = true;

        while(chainCheck){
            chain++;
            chainCheck = false;
            Queue<Puyo> q = findAllPuyo(map);
            boolean[][] visited = new boolean[map.length][map[0].length];
            List<Puyo> explosedAllPuyoList = new ArrayList<>();

            while(!q.isEmpty()){
                Puyo puyo = q.poll();

                if(visited[puyo.row][puyo.col]){
                    continue;
                }

                List<Puyo> explosedPuyoList = findExplosedPuyo(puyo,map,visited);

                //같은 색 뿌요 개수가 4개 이상인 경우 폭발 대상 뿌요이므로 전체 폭발 뿌요 리스트에 넣는다.
                if(explosedPuyoList.size()>=4) {
                    chainCheck = true; //폭발이 존재해 다음 체인도 확인해야함
                    for (Puyo p : explosedPuyoList) {
                        explosedAllPuyoList.add(p);
                    }
                }
            }
            //뿌요 터뜨리기
            explosePuyo(map,explosedAllPuyoList);

            //빈 공간에 뿌요 내리기
            dropPuyo(map);
        }
        return chain;
    }
    public static void dropPuyo(char[][] map){
        for(int row=map.length-1;row>=0;row--){
            for(int col=0;col<map[0].length;col++){
                Puyo puyo = new Puyo(row,col,map[row][col]);

                if(puyo.color == '.'){
                    continue;
                }

                //현재 뿌요 지우기
                map[row][col] = '.';
                Queue<Puyo> dropPuyo = new LinkedList<>();
                dropPuyo.add(puyo);

                while(!dropPuyo.isEmpty()){
                    Puyo curr = dropPuyo.poll();
                    Coordinate next = new Coordinate(curr.row +1, puyo.col);

                    if(next.row >= map.length || map[next.row][next.col] != '.'){
                        map[curr.row][curr.col] = puyo.color;
                    }
                    else{
                        dropPuyo.add(new Puyo(next.row,next.col,curr.color));
                    }
                }
            }
        }
    }
    public static void explosePuyo(char[][] map,List<Puyo> puyoList){
        for(Puyo puyo : puyoList){
            map[puyo.row][puyo.col] = '.';
        }
    }
    public static ArrayList<Puyo> findExplosedPuyo(Puyo startPuyo, char[][] map,boolean[][] visited){
        int[] dr = {-1,1,0,0};
        int[] dc = {0,0,-1,1};
        ArrayList<Puyo> explosedPuyoList = new ArrayList<>();
        Queue<Coordinate> puyoQueue = new LinkedList<>();

        explosedPuyoList.add(startPuyo);
        puyoQueue.add(new Coordinate(startPuyo.row,startPuyo.col));

        while(!puyoQueue.isEmpty()){
            Coordinate curr = puyoQueue.poll();
            visited[curr.row][curr.col]=true;

            for(int i=0;i<dr.length;i++){
                //다음 뿌요
                Coordinate next = new Coordinate(curr.row+dr[i],curr.col+dc[i]);

                //맵 밖을 벗어나는 경우
                if(next.row < 0 || next.col < 0 || next.row>=map.length || next.col>=map[0].length){
                    continue;
                }

                //이미 확인한 경우
                if(visited[next.row][next.col]){
                    continue;
                }

                //색깔이 다른 경우
                if(map[next.row][next.col] != startPuyo.color){
                    continue;
                }
                //색이 시작색과 같은 경우만 남는다.
                visited[next.row][next.col]=true;
                explosedPuyoList.add(new Puyo(next.row,next.col,map[next.row][next.col]));
                puyoQueue.add(next);
            }
        }

        return explosedPuyoList;
    }
    //탐색할 뿌요를 찾는 메소드
    public static Queue<Puyo> findAllPuyo(char[][]map){
        Queue<Puyo> q = new LinkedList<>();

        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j] != '.'){
                    q.add(new Puyo(i,j,map[i][j]));
                }
            }
        }

        return q;
    }

    //현재 게임 상태를 보여주는 메소드
    public static void printMap(char[][]map){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                System.out.print(map[i][j]+" ");
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

    public String toString(){
        return row+" , "+col;
    }
}
class Puyo extends Coordinate{
    char color;

    public Puyo(int row, int col,char color){
        super(row,col);
        this.color = color;
    }

    public String toString(){
        return row+" , "+col+" 색 : "+color;
    }
}
