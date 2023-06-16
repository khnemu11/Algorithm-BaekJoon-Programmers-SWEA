import java.util.*;

class Solution {
    public int[] solution(String[] park, String[] routes) {
        int[] answer = new int[2];
        char map[][] = new char[park.length][park[0].length()];
        
        //<방향, 좌표 이동> 맵
        Map<String,Move> directionMap = new HashMap<>();
        
        //방향 별 좌표 이동 등록
        directionMap.put("N",new Move(-1,0));
        directionMap.put("S",new Move(1,0));
        directionMap.put("W",new Move(0,-1));
        directionMap.put("E",new Move(0,1));
        
        //현재 위치 좌표
        //0,0 시작은 아니지만 시작좌표가 무조건 있으므로 초기 변수를 의미해주기 위해 0,0 좌표를 임의로 입력
        Coordinate curr = new Coordinate(0,0);
        
        //맵 초기화
        for(int i=0;i<park.length;i++){
            for(int j=0;j<park[i].length();j++){
                map[i][j] = park[i].charAt(j);
                
                //시작위치라면 좌표를 등록하고 이동이 가능한 통로로 변경
                if(map[i][j] == 'S'){
                    curr = new Coordinate(i,j);
                    map[i][j] = 'O';
                }
            }   
        }
       
        //모든 경로를 탐색하고 가능하면 좌표를 변경
        for(int i=0;i<routes.length;i++){
            StringTokenizer st = new StringTokenizer(routes[i]);
            String direction = st.nextToken(); //현재 방향
            Move move = directionMap.get(direction);    //현재 방향에 따른 가중치
            int weight = Integer.parseInt(st.nextToken());  //이동량
            
            Coordinate next = new Coordinate(curr.row,curr.col);
            
            boolean valid = true; //움직이는게 가능한지 판단 변수
            
            //1칸씩 이동하여 가능한지 판단
            for(int w=1;w<=weight;w++){
                next=new Coordinate(next.row + move.row, next.col+move.col);
                
                //맵을 벗어나는 경우
                if(next.row < 0 || next.col <0 || next.row >=map.length || next.col >=map[0].length){
                    valid=false;
                    break;
                }
                //벽인 경우
                if(map[next.row][next.col] == 'X'){
                    valid=false;
                    break;
                }
            }
            
            if(valid){
                curr = next;
            }            
        }
        
        return new int[]{curr.row,curr.col};
    }
}
//좌표 클래스
class Coordinate{
    int row;
    int col;
    
    public Coordinate(int row, int col){
        this.row = row;
        this.col =col;
    }
}
//이동 벡터 클래스
class Move{
    int row;
    int col;
    
    public Move(int row, int col){
        this.row = row;
        this.col =col;
    }
}