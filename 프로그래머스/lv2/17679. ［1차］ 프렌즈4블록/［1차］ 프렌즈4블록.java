import java.util.*;
/*
    크기 30 => 구현

    while(떠트릴 수 있는 블록이 있다면) 
        1) 2*2 터질 수 있는 박스 탐색
        2) 터뜨리기
        3) 비어있는 공간을 위에서 블록 내리기
    
    걸린시간 : 35분 28초
*/
class Solution {
    char[][] map;  
    boolean[][] isExploded; //터지는 영역
    boolean hasExplode = true;  //터트릴 수 있는 블록 판단 변수 
    int count = 0;  //터진 폭탄 개수
    char EMPTY = 'e'; //비어있는 공간 표현
    
    public int solution(int m, int n, String[] board) {
        init(board);
            
        while(hasExplode){
            findExplosion();
            countExplodedBlocks();
            fallBlocks();
        }
        
        return count;
    }

    //필드 default값 넣기
    public void init(String[] board){
        map = new char[board.length][board[0].length()];
        isExploded = new boolean[board.length][board[0].length()];
        hasExplode = true;
        count = 0;
        EMPTY = 'e';
        
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length();j++){
                map[i][j] = board[i].charAt(j);
            }   
        }
    }
    //4칸 모두 똑같으면 폭발
    public void findExplosion(){
        isExploded = new boolean[map.length][map[0].length];
        hasExplode=false;
        
        for(int row=0;row<map.length-1;row++){
            for(int col=0;col<map[row].length-1;col++){
                Set<Character> blocks = new HashSet<>();    //4영역 블록
                blocks.add(map[row][col]);
                blocks.add(map[row][col+1]);
                blocks.add(map[row+1][col]);
                blocks.add(map[row+1][col+1]);
                
                //모두 같은블록이고 빈 영역이 아닐때 터뜨리기 표식
                if(blocks.size()==1 && !blocks.contains(EMPTY)){
                    hasExplode=true;
                    isExploded[row][col]=true;
                    isExploded[row][col+1]=true;
                    isExploded[row+1][col]=true;
                    isExploded[row+1][col+1]=true;
                }
            }
        }
    }
    //폭발하는 칸 세기
    public void countExplodedBlocks(){
        for(int row=0;row<map.length;row++){
            for(int col=0;col<map[row].length;col++){
                if(isExploded[row][col]){
                    map[row][col] = EMPTY;
                    count++;
                }
            }
        }
    }
    
    //비어있는 블록 떨구기
    public void fallBlocks(){
        for(int col=0;col<map[0].length;col++){
            for(int row=map.length-1;row>0;row--){
                int currRow = row;
                while(map[currRow][col] == EMPTY && map[currRow-1][col] != EMPTY){
                    map[currRow][col] = map[currRow-1][col];
                    map[currRow-1][col] = EMPTY;
                    currRow++;
                    
                    if(currRow>=map.length){
                        break;
                    }
                }
            }
        }
    }
}