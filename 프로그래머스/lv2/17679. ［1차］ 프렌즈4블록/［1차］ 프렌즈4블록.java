import java.util.*;

class Solution {
    char[][] map;
    boolean[][] isExploded;
    boolean hasExplode = true;
    int count = 0;
    char EMPTY = 'e';
    
    public int solution(int m, int n, String[] board) {
        init(board);
       
        while(hasExplode){
            // printArr(map);
            findExplosion();
            countExplodedBlocks();
            fallBlocks();
        }
        
        return count;
    }
    public void printArr(char map[][]){
         for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                System.out.print(map[i][j]+" ");
            }    
            System.out.println();
        }
        System.out.println();
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
        // System.out.println("execute findExplosion");
        isExploded = new boolean[map.length][map[0].length];
        hasExplode=false;
        
        for(int row=0;row<map.length-1;row++){
            for(int col=0;col<map[row].length-1;col++){
                Set<Character> blocks = new HashSet<>();
                blocks.add(map[row][col]);
                blocks.add(map[row][col+1]);
                blocks.add(map[row+1][col]);
                blocks.add(map[row+1][col+1]);
                
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
        // System.out.println("execute countExplodedBlocks");
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
        // System.out.println("execute fallBlocks");
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