import java.util.*;

class Solution {
    int MAX_WIDTH = 52*2; //ㄷ모양의 경우 최단거리를 구하면 꺾지 않고 직진하므로 이를 위해 2배를 곱한다.
    boolean map[][] = new boolean[MAX_WIDTH][MAX_WIDTH];
    boolean visited[][] = new boolean[map.length][map[0].length];
    
    public int solution(int[][] rectangles, int characterX, int characterY, int itemX, int itemY) {
        //테두리 저장
        for(int []rectangle:rectangles){
            Coordinate leftBottomCoordinate = new Coordinate(2*rectangle[0],2*rectangle[1]);
            Coordinate rightTopCoordinate = new Coordinate(2*rectangle[2],2*rectangle[3]); 
            saveBorderMap(leftBottomCoordinate,rightTopCoordinate);
        }
        //테두리 아닌 도형 안의 내용 저장
        for(int []rectangle:rectangles){
            Coordinate leftBottomCoordinate = new Coordinate(2*rectangle[0],2*rectangle[1]);
            Coordinate rightTopCoordinate = new Coordinate(2*rectangle[2],2*rectangle[3]); 
            saveNotBorderMap(leftBottomCoordinate,rightTopCoordinate);
        }
        int dx[] = {-1,1,0,0};
        int dy[] = {0,0,-1,1};
        
        Queue<Coordinate> nextCoordinates = new LinkedList<>();
        nextCoordinates.add(new Coordinate(2*characterX,2*characterY));
        
        //bfs로 빨리 도착하는 것 리턴
        while(!nextCoordinates.isEmpty()){
            Coordinate curr = nextCoordinates.poll();
            
            //방문처리
            if(visited[curr.x][curr.y]){
                continue;
            }
            
            visited[curr.x][curr.y]=true;
            
            //도착처리
            if(curr.x == itemX*2 && curr.y == itemY*2){
                return curr.count/2;
            }  
            //4방향 탐색
            for(int i=0;i<dx.length;i++){
                Coordinate next= new Coordinate(curr.x+dx[i],curr.y+dy[i]);
                next.count = curr.count+1;
                
                if(next.x<0 || next.y>map.length || next.x>=map.length || next.y<0){
                    continue;
                }
                
                if(!map[next.x][next.y] || visited[next.x][next.y]){
                    continue;
                }
                nextCoordinates.add(next);
            }
        }
        return 0;
    }
    
    public void saveNotBorderMap(Coordinate leftBottomCoordinate,Coordinate rightTopCoordinate){
        for(int x=leftBottomCoordinate.x+1;x<rightTopCoordinate.x;x++){
            for(int y=leftBottomCoordinate.y+1;y<rightTopCoordinate.y;y++){
                map[x][y]=false;
            }
        }        
    } 
    public void saveBorderMap(Coordinate leftBottomCoordinate,Coordinate rightTopCoordinate){
        //높이 테두리 저장
        for(int x=leftBottomCoordinate.x;x<=rightTopCoordinate.x;x++){
            map[x][leftBottomCoordinate.y]=true;
            map[x][rightTopCoordinate.y]=true;
        }
        //가로 테두리 저장
        for(int y=leftBottomCoordinate.y;y<=rightTopCoordinate.y;y++){
            map[leftBottomCoordinate.x][y]=true;
            map[rightTopCoordinate.x][y]=true;
        }
    } 
}
class Coordinate{
    int x;
    int y;
    int count;
    
    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }
}