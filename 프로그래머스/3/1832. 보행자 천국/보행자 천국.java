import java.util.*;
    
class Solution {
    int MOD = 20170805;
    
    int ALL_ACCESS = 0;
    int LIMIT = 1;
    int STRAIGHT_ACCESS = 2;
    
    int HEAD_RIGHT = 0;
    int HEAD_DOWN = 1;
    
    int MOVE_RIGHT = 0;
    int MOVE_DOWN = 1;
    
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        int[] dx = {0,1};
        int[] dy = {1,0};
        boolean[][][] visited = new boolean[2][m][n];
        int[][][] dp = new int[2][m][n];
        dp[HEAD_RIGHT][0][0] = 1;
        dp[HEAD_DOWN][0][0] = 1;
        
        Queue<Car> q = new LinkedList<>();
        q.add(new Car(0,0,HEAD_RIGHT)); //자동차 시작 방향은 어디로 시작해도 상관 없다.
        
        while(!q.isEmpty()){
            Car curr = q.poll();
            // System.out.println(curr.row+" , "+curr.col+" direction : "+(curr.direction == HEAD_RIGHT ? "RIGHT" : "DOWN"));
            visited[curr.direction][curr.row][curr.col] = true;
            
            for(int i=0;i<dx.length;i++){
                //현재 위치에서 우회전/좌회전이 안되는 경우 스킵
                if(cityMap[curr.row][curr.col] == STRAIGHT_ACCESS
                  && curr.direction != i){
                    continue;
                }
                
                Car next = new Car(curr.row + dx[i], 
                                          curr.col + dy[i],
                                          curr.direction == i ? curr.direction : (curr.direction+1)%2);
                //다음 위치가 없는 좌표인 경우 스킵
                if(next.row <0 || next.row >=m || next.col <0 || next.col >=n){
                    continue;
                }
                //다음 위치가 갈 수 없는 곳인 경우 스킵
                if(cityMap[next.row][next.col] == LIMIT){
                     continue;
                }
                
                //다음 위치로 갈 수 있는 경우의 수 추가
                dp[next.direction][next.row][next.col] 
                    = (dp[curr.direction][curr.row][curr.col] + dp[next.direction][next.row][next.col]) % MOD;
                
                if(visited[next.direction][next.row][next.col]){
                   continue; 
                }
                //다음 좌표 방문처리
                visited[next.direction][next.row][next.col]= true;
                //다음 좌표 넣기
                q.add(next);
            }    
            // printArr(dp);
        }
        return (dp[HEAD_RIGHT][m-1][n-1] + dp[HEAD_DOWN][m-1][n-1]) % MOD;
    }
    
    public void printArr(int[][][] arr){
        for(int a=0;a<arr.length;a++){
            if(a ==0){
                System.out.println("RIGHT");
            }else{
                System.out.println("DOWN");
            }
            for(int i=0;i<arr[a].length;i++){
                for(int j=0;j<arr[a][i].length;j++){
                    System.out.print(arr[a][i][j]+" ");
                }   
                System.out.println();
            }
             System.out.println();
        }
        System.out.println();
    }
}
class Car{
    int row;
    int col;
    int direction;
    
    public Car(int row, int col,int direction){
        this.row = row;
        this.col = col;
        this.direction = direction;
    }
}