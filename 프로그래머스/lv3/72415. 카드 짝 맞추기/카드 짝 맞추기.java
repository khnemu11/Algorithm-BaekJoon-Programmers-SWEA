
/*
    1) 모든 아이콘끼리의 최소거리 구하기
    2) 현재 좌표와 모든 아이콘끼리의 최소거리 구하기
    3) 아이콘끼리의 최소거리 + 좌표와 아이콘의 최소거리중 최소거리를 선택 후 이동
*/

import java.util.*;

class Solution {
    static int upDown[] = {-1,1,0,0};
    static int leftRight[] = {0,0,-1,1};
    static Coordinate coords[][] = new Coordinate[7][2];
    static boolean removed[] = new boolean[7];
    static int ICON_NUM = 0;
    static int min = Integer.MAX_VALUE;
    public int solution(int[][] board, int r, int c) {
        int answer = 0;
        findIconCoord(board);
        select(0,0,new Coordinate(r,c),board);
        
        return min;
    }
    public void findIconCoord(int[][] board){
         Arrays.fill(removed,true);
         
         for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]==0){
                    continue;
                }
                Coordinate icon = new Coordinate(i,j);
                if(coords[board[i][j]][0]==null){
                    removed[board[i][j]]=false;
                    ICON_NUM++;
                    coords[board[i][j]][0]=icon;
                }else{
                    coords[board[i][j]][1]=icon;
                }
            }   
         }
    }
      public void select(int cnt,int idx,Coordinate curr,int board[][]){
          if(idx == ICON_NUM){
              min = Math.min(min,cnt);
          }else{
              for(int i=1;i<removed.length;i++){
                  if(removed[i]){
                      continue;
                  }
                  // System.out.println(i+" 제거");
                  
                  // System.out.println("#"+(idx+1)+Arrays.toString(removed)+" cnt : "+cnt);
                  // int iconDistance = findMinIconDistance(board,coords[i][0],coords[i][1]);
                  int coordDistanceFirst = findMinIconDistance(board,curr,coords[i][0]) +findMinIconDistance(board,coords[i][0],coords[i][1]) ;
                  int coordDistanceSecond = findMinIconDistance(board,curr,coords[i][1]) + findMinIconDistance(board,coords[i][1],coords[i][0]);
                  removed[i]=true;
                  // System.out.println(coordDistanceSecond +" vs "+coordDistanceFirst+" curr: "+curr+"\n");
                  
                  if(coordDistanceFirst > coordDistanceSecond){
                      select(cnt+coordDistanceSecond+2,idx+1,coords[i][0],board);
                  }else{
                      select(cnt+coordDistanceFirst+2,idx+1,coords[i][1],board);
                  }
                  
                  removed[i]=false;
              }
          }
      }
      public int findMinIconDistance(int[][] board, Coordinate start, Coordinate target){
             // System.out.println(start+" ===> "+target);
             Queue<Coordinate> q = new LinkedList<>();
             q.add(start);
             boolean visited[][] = new boolean[board.length][board[0].length];
             int cnt = 0;
             boolean find = false;
             while(!q.isEmpty()){
                 int loop = q.size();
                 while(loop-->0){
                     // System.out.println("depth : "+cnt);
                     Coordinate curr = q.poll();
                     // System.out.println("현재 위치 : "+curr);
                     visited[curr.row][curr.col]=true;
                     
                     if(curr.row == target.row && curr.col == target.col){
                         // System.out.println("발견 cnt : "+cnt);
                         find = true;
                         break;
                     }
                     
                     for(int i=0;i<upDown.length;i++){
                         Coordinate next = new Coordinate(curr.row + upDown[i],curr.col + leftRight[i]);
                         if(!isValid(next,board)){
                             continue;
                         }
                         if(!visited[next.row][next.col]){
                             visited[next.row][next.col]=true;
                             // System.out.println(curr+"->"+next);
                             q.add(new Coordinate(next.row,next.col));
                         }
                         
                         while(isValid(new Coordinate(next.row + upDown[i],next.col + leftRight[i]),board)){
                             // if(curr.row == 3 && curr.col==0){
                             //     System.out.println(next);
                             // }
                             if(board[next.row][next.col] > 0 && !removed[board[next.row][next.col]]){
                                 break;
                             }
                             next.row +=upDown[i];
                             next.col +=leftRight[i];
                         }
                         
                         if(!visited[next.row][next.col]){
                             visited[next.row][next.col]=true;
                             // System.out.println(curr+"->"+next);
                             q.add(new Coordinate(next.row,next.col));
                         }
                     }
                 }
                 
                 if(find){
                     break;
                 }
                 cnt++;
             }
            // System.out.println("거리 : "+cnt);
            return cnt;
         }
    
    
    
    public boolean isValid(Coordinate coord, int board[][]){
        if(coord.row<0 || coord.col<0 || coord.row>=board.length || coord.col>=board[0].length){
            return false;
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
    
    public String toString(){
        return row+" , "+col;
    }
}