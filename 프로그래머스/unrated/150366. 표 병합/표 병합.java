import java.util.*;

class Solution {
    String map[][] = new String[51][51];
    Coordinate parents[][] = new Coordinate[51][51];
    ArrayList<String> answer = new ArrayList<>();
    
    public String[] solution(String[] commands) {
        init();
        
        for(String command : commands){
            String [] splited = command.split(" ");
            
            if(splited[0].equals("UPDATE")){
                if(splited.length == 4){
                    update(Integer.valueOf(splited[1]),Integer.valueOf(splited[2]),splited[3]);
                }else{
                    update(splited[1],splited[2]);
                }
            }else if(splited[0].equals("MERGE")){
                merge(Integer.valueOf(splited[1]),Integer.valueOf(splited[2]),Integer.valueOf(splited[3]),Integer.valueOf(splited[4]));
            }else if(splited[0].equals("UNMERGE")){
                unmerge(Integer.valueOf(splited[1]),Integer.valueOf(splited[2]));
            }else if(splited[0].equals("PRINT")){
                print(Integer.valueOf(splited[1]),Integer.valueOf(splited[2]));
            }
            // printArr();
            // System.out.println();
        }
        
        return answer.toArray(new String[0]);
    }
    public void printArr(){
         for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
               System.out.print(map[i][j]+" ");
            }
             System.out.println();
        }
          for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
               System.out.print(parents[i][j]+" ");
            }
             System.out.println();
        }
    }
    public void merge(int r1, int c1, int r2, int c2){
        union(new Coordinate(r1,c1),new Coordinate(r2,c2));
    }
    
    public void unmerge(int r, int c){
        Coordinate curr = new Coordinate(r,c);
        String value = map[r][c];
        Coordinate parent = getParent(curr);
        
        for(int i=0;i<parents.length;i++){
            for(int j=0;j<parents.length;j++){                
                if(parents[i][j].equals(parent)){
            
                    parents[i][j] = new Coordinate(i,j);
                    map[i][j] = "EMPTY";
                }
            }
        } 
        map[r][c] = value;
    }
    public void print(int r, int c){
           Coordinate parent = getParent(new Coordinate(r,c));
           answer.add(map[parent.row][parent.col]);
    }
    
    public void update(int r, int c, String value){
        Coordinate parent = getParent(new Coordinate(r,c));
        
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map.length;j++){
                Coordinate targetParent = getParent(new Coordinate(i,j));
                if(targetParent.equals(parent)){
                    map[i][j]=value;
                }
            }
        }
    }
    
    public void update(String value1, String value2){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map.length;j++){
                if(map[i][j].equals(value1)){
                    map[i][j] = value2;
                }
            }
        }
    }
    
    public void init(){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map.length;j++){
                map[i][j] = "EMPTY";
                parents[i][j] = new Coordinate(i,j);
            }
        }
    }
    
    public Coordinate getParent(Coordinate child){
        if(parents[child.row][child.col].equals(child)){
            return child;
        }
        else{
            parents[child.row][child.col] = getParent(parents[child.row][child.col]);
            return parents[child.row][child.col];
        }
    }
    
    public void union(Coordinate a, Coordinate b){
        Coordinate pa = getParent(a);
        Coordinate pb = getParent(b);
        
        if(map[pa.row][pa.col].equals("EMPTY")){
             parents[pa.row][pa.col] = pb;
             update(pa.row,pa.col,map[pb.row][pb.col]);
        }else{
             parents[pb.row][pb.col] = pa;
             update(pb.row,pb.col,map[pa.row][pa.col]);
        }
    }
}
class Coordinate{
    int row;
    int col;
    
    public Coordinate(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    public boolean equalsTo(Coordinate o){
        if(this.row == o.row && this.col == o.col){
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public String toString(){
        return this.row+" , "+this.col;
    }
}