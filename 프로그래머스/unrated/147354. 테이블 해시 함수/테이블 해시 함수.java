import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Tuple tuples [] = new Tuple[data.length];
        
        for(int i=0;i<data.length;i++){
            tuples[i] = new Tuple(data[i],col);
        }
   
        Arrays.sort(tuples); 
        
        int bit = 0;
        
        for(int i=row_begin-1;i<row_end;i++){
            int sum=0;
            for(int j=0;j<tuples[i].val.length;j++){
                sum = sum + (tuples[i].val[j]%(i+1));
            } 
            bit = bit ^ sum;
        }
        
        return bit;
    }
}

class Tuple implements Comparable<Tuple>{
    int val[];
    int col;
    
    public Tuple(int val[], int col){
        this.val=val;
        this.col=col-1;
    }
    
    public int compareTo(Tuple o){
       if(this.val[col] == o.val[col]){
                    return o.val[0] - this.val[0];
                }
                
                return this.val[col] - o.val[col]; 
    }
    
    public String toString(){
        return Arrays.toString(val);
    }
}