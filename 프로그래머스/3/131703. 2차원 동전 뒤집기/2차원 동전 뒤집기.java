import java.util.*;

class Solution {
    final int INF = Integer.MAX_VALUE;
    final int BLACK = 0;
    final int WHITE = 1;
    int[][] target;
    int[][] beginning;
    int min = INF;
        
    public int solution(int[][] beginning, int[][] target) {
        this.target = target;
        this.beginning = beginning;
        
        selectRow(new ArrayList<>(),0);
        
        return min == INF ? -1 : min;
    }
    public void selectRow(List<Integer> rowList, int curr){
        if(curr >= beginning.length){
            selectCol(rowList,new ArrayList<>(),0);
            return;
        }
        selectRow(rowList,curr+1);
        
        rowList.add(curr);
        selectRow(rowList,curr+1);
        rowList.remove(rowList.size()-1);
    }
    public void selectCol(List<Integer> rowList, List<Integer> colList,int curr){
        if(curr >= beginning[0].length){
            if(rowList.size() + colList.size() > min){
                return ;
            }
            
            int[][] map = copyArr(beginning);
            
            //열 뒤집기
            for(int row : rowList){
                for(int col=0;col<map[row].length;col++){
                    map[row][col] = (map[row][col] +1) % 2;
                }
            }   
            
            //행 뒤집기
            for(int col : colList){
                for(int row=0;row<map.length;row++){
                    map[row][col] = (map[row][col] +1) % 2;
                }
            } 
            
            if(isEqual(map,target)){
                min = Math.min(min,rowList.size()+colList.size());
            }
            
            return;
        }
        
        selectCol(rowList,colList,curr+1);
        
        colList.add(curr);
        selectCol(rowList,colList,curr+1);
        colList.remove(colList.size()-1);
    }
    
    public int[][] copyArr(int[][] arr){
        int[][] temp = new int[arr.length][arr[0].length];
        
        for(int i=0;i<arr.length;i++){
             for(int j=0;j<arr[0].length;j++){
                 temp[i][j] = arr[i][j];
             }    
        }   
        
        return temp;
    }
    
    public boolean isEqual(int[][] a, int[][] b){
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){
                if(a[i][j] != b[i][j]){
                    return false;
                }
            }   
        }
        
        return true;
    }
}