import java.util.*;

/*
    출발지 : 0,0
    도착지 : N-1,N-1
    
    bfs로 가장 먼저 도착한 depth의 최소값 출력
    직선/곡선 판단을 위해 이전 방향 필요   
*/

import java.util.*;

class Solution {
    int UP = 0;
    int DOWN = 2;
    int LEFT = 1;
    int RIGHT = 3;
    int DIRECT_COST = 100;
    int CORNER_COST = 500;
    int DIRECT=0;
    int CORNER=1;
    int INF = 0;
    
    public int solution(int[][] board) {
        int dx[] = {-1,0,1,0}; //0: 상, 1:좌, 2:하 : 3:우
        int dy[] = {0,-1,0,1};
        
        INF = (board.length*board[0].length)*CORNER_COST;
        int minCost [][][] = new int[dx.length][board.length][board[0].length];
        
        for(int i=0;i<minCost.length;i++){
            for(int j=0;j<minCost[i].length;j++){
                Arrays.fill(minCost[i][j],INF);
            }   
            minCost[i][0][0]=100;
        }
        
        PriorityQueue<Car> q = new PriorityQueue<>();
        
        q.add(new Car(0,0,0,0,RIGHT));
        q.add(new Car(0,0,0,0,DOWN));

        while(!q.isEmpty()){
            Car curr =q.poll();
            // System.out.println(curr);
            if(minCost[curr.direction%2][curr.row][curr.col] < (curr.direct+curr.corner*5)*100){
                // System.out.println("pass: "+curr);
                continue;
            }

            for(int k=0;k<dx.length;k++){
                Car next = null;
                
                if(k%2 == curr.direction%2){
                    // System.out.println(k+" == "+curr.direction);
                    next = new Car(curr.row+dx[k],curr.col+dy[k],curr.direct+1,curr.corner,k);
                }else{
                    // System.out.println(k+" != "+curr.direction);
                    next = new Car(curr.row+dx[k],curr.col+dy[k],curr.direct+1,curr.corner+1,k);
                }
                
                if(next.row<0||next.col<0 || next.row>=board.length || next.col>=board[0].length){
                    // System.out.println(next+" 맵 나감");
                    continue;
                }
                if(board[next.row][next.col]==1){
                    // System.out.println(next+" 벽");
                    continue; 
                }
                int cost = (next.direct+next.corner*5)*100;
                if(minCost[next.direction%2][next.row][next.col] <= cost){
                    // System.out.println(next+" 최소값보다 큼");
                    continue; 
                }
                // System.out.println(curr+"->"+next);
                minCost[next.direction%2][next.row][next.col] = cost;
                q.add(next);
            }
        }    
        
        int min = INF;
        // System.out.println(Arrays.deepToString(minCost));
        for(int i=0;i<minCost.length;i++){
            min = Math.min(min,minCost[i][minCost[0].length-1][minCost[0][0].length-1]);
        }
        
        return min;
    }
}
class Car implements Comparable<Car>{
    int row;
    int col;
    int direct;
    int corner;
    int direction;
    
    public Car(int row, int col, int direct,int corner,int direction){
        this.row=row;
        this.col=col;
        this.direct=direct;
        this.corner=corner;
        this.direction=direction;
    }
    public int compareTo(Car o){
        return (this.direct+this.corner*5) - (o.direct+o.corner*5);
    }
    public String toString(){
        return row+" , "+col +" direction : "+direction+" cost : "+(this.direct+this.corner*5);
    }
}