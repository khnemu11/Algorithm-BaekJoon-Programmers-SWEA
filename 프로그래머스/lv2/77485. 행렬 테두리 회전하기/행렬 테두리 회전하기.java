class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int arr[][] = new int[rows+1][columns+1];
        int val=1;
        for(int i=1;i<=rows;i++){
            for(int j=1;j<=columns;j++){
                arr[i][j] = val++; 
            }
        }
        
        int upDown[] = {0,1,0,-1,0};
        int leftRight[] = {1,0,-1,0,0};
        
        for(int seq=0;seq<queries.length;seq++){
            Coordinate curr = new Coordinate(queries[seq][0],queries[seq][1]);
            int currVal = arr[curr.row][curr.col];
            int min = currVal;
            int direction = 0;
            
            while(direction < 4){
                Coordinate next = new Coordinate(curr.row +upDown[direction], curr.col +leftRight[direction]);
                while(!isValid(queries[seq],next)){
                    direction++;
                    next = new Coordinate(curr.row +upDown[direction], curr.col + leftRight[direction]);
                }
                int temp =  arr[next.row][next.col];
                arr[next.row][next.col] = currVal;
                currVal = temp;             
                curr=next;
                min = Math.min(currVal,min);
            }
            arr[queries[seq][0]][queries[seq][1]] = currVal;
            answer[seq] = min;
        }
        
        
        
        return answer;
    }
    
    public boolean isValid(int [] limit, Coordinate coord){
        if(coord.row < limit[0] || coord.col < limit[1] || coord.row >limit[2] || coord.col > limit[3]){
            return false;
        }
        return true;
    } 
    
    public void printArr(int arr[][]){
          for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                System.out.print(arr[i][j]+" ");
            }
              System.out.println();
        }
        System.out.println();
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