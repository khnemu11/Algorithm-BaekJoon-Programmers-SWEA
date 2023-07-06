import java.util.*;

class Solution {
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    List<Area> puzzleAreas = new ArrayList<>();
    List<Area> emptyAreas = new ArrayList<>();
    boolean puzzleVisited[];
    int maxCount = 0;
    
    public int solution(int[][] game_board, int[][] table) {
        int answer = -1;
        setAreaList(puzzleAreas,table,1);
        setAreaList(emptyAreas,game_board,0);
        
        puzzleVisited = new boolean[puzzleAreas.size()];
        selectPuzzle(0,0);
        return maxCount;
    }
    public void selectPuzzle(int idx,int count){
        // System.out.println("Depth : "+idx);
        if(idx >= emptyAreas.size()){
            maxCount = Math.max(maxCount,count);
            return;
        }
        boolean findPuzzle = false;
        for(int i=0;i<puzzleAreas.size();i++){
            if(puzzleVisited[i]){
                continue;
            }else if(puzzleAreas.get(i).count !=emptyAreas.get(idx).count){
                continue;
            }
            // printArea(emptyAreas.get(idx).map);
            // printArea(puzzleAreas.get(i).map);
            // System.out.println("==========");
            for(int j=0;j<5;j++){
                if(puzzleAreas.get(i).equals(emptyAreas.get(idx))){
                    puzzleVisited[i]=true;
                    findPuzzle=true;
                    selectPuzzle(idx+1,puzzleAreas.get(i).count+count);
                    break;
                }
                puzzleAreas.get(i).rotate();
            }
        }
        if(!findPuzzle){
            selectPuzzle(idx+1,count);
        }
    }
    public void setAreaList(List<Area> areas,int map[][],int value){
        boolean visited[][] = new boolean[map.length][map[0].length];
        
        for(int row=0;row<map.length;row++){
            for(int col=0;col<map[0].length;col++){
                if(map[row][col] != value){
                    continue;
                }else if(visited[row][col]){
                    continue;
                }
                // System.out.println(row+" , "+col);
                Area area = new Area();
                findArea(new Coordinate(row,col),map,area,visited,value);
                setArea(area,map,value);
                areas.add(area);
            }   
        }
    }

    public void setArea(Area area,int board[][],int value){
        int map [][] = new int[area.maxRow-area.minRow+1][area.maxCol-area.minCol+1];
        int row=0;
        int col=0;
        int count = 0;
        
        for(int i = area.minRow; i<=area.maxRow;i++){
            col=0;
            for(int j = area.minCol; j<=area.maxCol;j++){
                map[row][col++] = board[i][j];
                if(board[i][j]==value){
                    count++;
                }
            }    
            row++;
        }
        
        area.map = map;
        area.count = count;
    }
    public void printArea(int[][] map){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                System.out.print(map[i][j]+" ");
            }   
            System.out.println();
        }
        System.out.println();
    }
    public void findArea(Coordinate start, int[][] map, Area area,boolean visited[][],int value){
        Queue<Coordinate> q = new LinkedList<>();
        q.add(start);
        
        while(!q.isEmpty()){
            Coordinate curr = q.poll();
            visited[curr.row][curr.col] = true;
            
            area.minRow = Math.min(curr.row, area.minRow);
            area.minCol = Math.min(curr.col, area.minCol);
            area.maxRow = Math.max(curr.row, area.maxRow);
            area.maxCol = Math.max(curr.col, area.maxCol);
                    
            for(int i=0;i<dx.length;i++){
                Coordinate next = new Coordinate(curr.row+dx[i],curr.col+dy[i]);
            
                if(isOutOfMap(next,map)){
                    continue;
                }else if(map[next.row][next.col]!=value){
                    continue;
                }else if(visited[next.row][next.col]){
                    continue;
                }
                
                q.add(next);
            }
        }
    }
    public boolean isOutOfMap(Coordinate coord, int[][] map){
        if(coord.row<0 || coord.col<0 || coord.row>=map.length || coord.col>=map[0].length){
            return true;
        }
        return false;
    }
}
class Area{
    int minRow;
    int maxRow;
    int minCol;
    int maxCol;
    int map[][];
    int count;
    
    public Area(){
        this.minRow = Integer.MAX_VALUE;
        this.maxRow = -1;
        this.minCol = Integer.MAX_VALUE;
        this.maxCol = -1;
    }
    public Area(int minRow,int maxRow,int minCol,int maxCol){
        this.minRow = minRow;
        this.maxRow = maxRow;
        this.minCol = minCol;
        this.maxCol = maxCol;
    }
    public void rotate(){
        int[][] temp = new int[map[0].length][map.length];
        
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                temp[j][i] = map[i][map[0].length-1-j];
            }    
        }
        
        this.map = temp;
    }
    public boolean equals(Area area){
        if(area.map.length != map.length || area.map[0].length != map[0].length){
            return false;
        }
        
        for(int i=0;i<area.map.length;i++){
            for(int j=0;j<area.map[0].length;j++){
                if(area.map[i][j]==map[i][j]){
                    return false;
                }        
            }   
        }
        return true;
    }
}
class Coordinate{
    int row;
    int col;
    
    public Coordinate(int row, int col){
        this.row=row;
        this.col=col;
    }
}