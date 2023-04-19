/*
    조건 1) 후공이 선공보다 많이 한 경우 or 선공이 후공보다 2번 더 한 경우
    조건 2) 선공,후공 둘다 이긴 경우
*/

import java.util.*;

class Solution {
    int FIRST = 0;
    int SECOND = 1;
    ArrayList<Coordinate> coords[] = new ArrayList[2];
    boolean visited[][]= new boolean[3][3];
    boolean canPlay=false;
    char map[][] = new char[3][3];
    int count=0;
    public int solution(String[] board) {
        for(int i=0;i<2;i++){
            coords[i] =new ArrayList<Coordinate>();
        }
        
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length();j++){
                if(board[i].charAt(j)=='O'){
                    coords[0].add(new Coordinate(i,j));
                    count++;
                }else if(board[i].charAt(j)=='X'){
                    coords[1].add(new Coordinate(i,j));
                    count++;
                }
                map[i][j]='.';
            }   
        }
        
        // System.out.println(Arrays.toString(coords)+" count : "+count);
        play(0,0);
        
        if(canPlay){
            return 1;
        }
        
        return 0;
    }
    
    public void play(int turn,int seq){ //seq(0:선공, 1:후공)
        //      System.out.println("turn : "+turn);    
        // for(int i=0;i<map.length;i++){
        //     for(int j=0;j<map.length;j++){
        //         System.out.print(map[i][j]+" ");
        //     }     
        //     System.out.println();
        // }
        
        // System.out.println();
        if(finish() || turn==count){
           
            // System.out.println("finish!");
            
            if(turn==count){
                 canPlay=true;
            }

            return;
        }else{
           for(int i=0;i<coords[seq].size();i++){
                if(map[coords[seq].get(i).row][coords[seq].get(i).col]!='.'){
                    continue;
                }
               
                if(seq==0){
                    map[coords[seq].get(i).row][coords[seq].get(i).col]='O';
                }else{
                    map[coords[seq].get(i).row][coords[seq].get(i).col]='X';
                }
                
                play(turn+1,seq == 1 ? 0 : 1);
               
                map[coords[seq].get(i).row][coords[seq].get(i).col]='.';
            } 
        }
    }
    
    
    public boolean finish(){
        for(int i=0;i<map.length;i++){
            if(map[i][0]!='.' &&map[i][0]==map[i][1] && map[i][1]==map[i][2]){
                // System.out.println("가로 끝");
                return true;
            }
        }
        for(int i=0;i<map.length;i++){
            if(map[0][i]!='.'&&map[0][i]==map[1][i] && map[0][i]==map[2][i]){
                // System.out.println("세로 끝");
                return true;
            }
        }
        
        if(map[1][1]!='.' && map[0][0]==map[1][1] && map[2][2]==map[0][0]){
            // System.out.println("왼 대각 끝");
            return true;
        }
        if(map[1][1]!='.' && map[0][2]==map[1][1] && map[2][0]==map[0][2]){
            // System.out.println("오른 대각 끝");
            return true;
        }

        
        return false;
    }
}
class Coordinate{
    int row;
    int col;
    
    public Coordinate(int row, int col){
        this.row=row;
        this.col=col;
    }
    
    public String toString(){
        return row +" , "+col;
    }
}