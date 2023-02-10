import java.util.*;
/*풀이 알고리즘
    d > l > r > u 순으로 우선순위가 높다
    1) 현재 위치에서 남은 이동거리 내로 목표지점까지 가도 이동거리가 남을경우 
       >>  d > l > r > u를 순서대로 선택하여 가능한 좌표인 경우 해당 방향을 이용해 빠른 경로를 만든다(그리디)
    2) 남은 이동거리 내로 목표지점까지 갔을때 이동거리가 남지 않을 경우
       >> 목표지점으로 이동해야하므로  d > l > r > u 순서로 목표지점과 현재 위치가 가까워지는지(도달하는지) 판단하여 경로 선택
    목표지점에 도달할 수 없는 경우
    1) 이동거리 K와 시작지점과 목표지점간의 거리의 차이가 홀수인경우
    2) 이동거리가 시작지점과 목표지점간의 거리보다 짧은경우
    
*/
class Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        
        int map [][]= new int[n+1][m+1]; 
        Coordinate start= new Coordinate(x,y); //시작점
        Coordinate end= new Coordinate(r,c); //끝점
        
        int distance = Math.abs(start.row- end.row) + Math.abs(start.col - end.col); //시작점과 끝점간의 거리 차이
        if(distance > k || Math.abs(distance - k)%2==1){ // 이동이 불가한 경우
            answer="impossible";
        }else{ //가능한 경우
            answer = move(map,start,end,k);
        }
        
        return answer;
    }
    public String move(int map[][],Coordinate start, Coordinate end,int k){ //사전순서상 최단 거리를 구하는 메소드
        StringBuilder sb = new StringBuilder();
        String direction[] = {"d","l","r","u"}; //아래, 왼쪽,오른쪽, 위방향 순서로 이동 예정
        int upDown[] = {1,0,0,-1};
        int leftRight[] = {0,-1,1,0};
        
        Coordinate curr = start;
        int distance = Math.abs(start.row- end.row) + Math.abs(start.col - end.col); //두 좌표간의 거리
        
        for(int cnt=k;cnt>0;cnt--){
            //남는 이동거리가 없으므로 목표지점으로 이동
            if(cnt == distance){
                if(end.row > curr.row){ //현재 좌표가 목표 지점보다 위에 있을 때
                    curr.row++;
                    sb.append("d");
                }else if(end.col<curr.col){ //현재 좌표가 목표 지점보다 오른쪽에 있을 때
                    curr.col--;
                    sb.append("l");
                }else if(end.col>curr.col){ //현재 좌표가 목표 지점보다 왼쪽에 있을 때
                    curr.col++;
                    sb.append("r");
                }else{
                    curr.row--; //현재 좌표가 목표 지점보다 아래에 있을 때
                    sb.append("u");
                }
                distance--;
            }
            //남는 이동거리가 있으므로 최소 이동 방향 선택
            else{
                //4방향 중 가능한 좌표 탐색
                for(int i=0;i<direction.length;i++){
                    Coordinate next = new Coordinate(curr.row + upDown[i],curr.col + leftRight[i]);

                    if(!isValid(next,map)){ //가능하지 않다면 다음 방향 선택
                        continue;
                    }
                    
                    //위치,거리,경로를 최신화하고 반복문 탈출
                    curr = next;
                    distance = Math.abs(curr.row- end.row) + Math.abs(curr.col - end.col);
                    sb.append(direction[i]);
                    break;
                }
            }
        }
        
        return sb.toString();
    }
    
    public boolean isValid(Coordinate coord,int map[][]){ //해당 맵에서 벗어나는 좌표인지 확인하는 메소드
        if(coord.row<=0 || coord.col<=0 || coord.row>=map.length || coord.col>=map[0].length){
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
}