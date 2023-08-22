class Solution {
    int ZERO_COUNT = 0;
    int ONE_COUNT = 1;
    int[] answer = new int[2];
    public int[] solution(int[][] arr) {
        quadZip(new Coordinate(0,0),arr.length,arr);
        return answer;
    }
    
    public void quadZip(Coordinate coord,int width,int[][] arr){
        int zero = 0;
        int one = 0;
        
        for(int i=0;i<width;i++){
            for(int j=0;j<width;j++){
                if(arr[coord.row+i][coord.col+j] == 1){
                    one++;
                }
                else if(arr[coord.row+i][coord.col+j] == 0){
                    zero++;
                }        
            }   
        }
        if(zero == 0){
            answer[ONE_COUNT]++;
        }else if(one == 0){
            answer[ZERO_COUNT]++;
        }else if(one >0 && zero>0){
            quadZip(new Coordinate(coord.row,coord.col),width/2,arr);
            quadZip(new Coordinate(coord.row+width/2,coord.col),width/2,arr);
            quadZip(new Coordinate(coord.row+width/2,coord.col+width/2),width/2,arr);
            quadZip(new Coordinate(coord.row,coord.col+width/2),width/2,arr);
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