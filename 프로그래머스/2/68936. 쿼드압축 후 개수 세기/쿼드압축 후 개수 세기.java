class Solution {
    int ONE = 1;
    int ZERO = 0;
    int[] totalCount = new int[2];
    
    public int[] solution(int[][] arr) {
        Coordinate leftTop = new Coordinate(0,0);
        
        quardZip(leftTop,arr.length,arr);
        
        return totalCount;
    }
    
    public void quardZip(Coordinate leftTop,int length, int[][] arr){
        int[] count = new int[2];
        
        //범위 내의 0,1 개수 세기
        for(int row = 0; row < length;row++){
            for(int col = 0; col <length;col++){
                count[arr[row+leftTop.row][col+leftTop.col]]++;
            }   
        }
        
        //모든 칸이 1인 경우
        if(count[1]>0 && count[0]==0){
            totalCount[1]++;
        }
        //모든 칸이 0인 경우
        else if(count[0]>0 && count[1]==0){
            totalCount[0]++;
        }
        //0과 1이 섞여 있는경우
        else{
            int nextLength = length/2;
            
            quardZip(new Coordinate(leftTop.row,leftTop.col),nextLength,arr);
            quardZip(new Coordinate(leftTop.row,leftTop.col + nextLength),nextLength,arr);
            quardZip(new Coordinate(leftTop.row + nextLength,leftTop.col),nextLength,arr);
            quardZip(new Coordinate(leftTop.row + nextLength,leftTop.col + nextLength),nextLength,arr);
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
}