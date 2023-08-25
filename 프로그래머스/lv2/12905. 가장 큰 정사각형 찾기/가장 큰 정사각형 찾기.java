import java.util.*;

class Solution{
    public int solution(int [][]board){
        int answer = 0;
        int sum[][] = new int[board.length+1][board[0].length+1];
        
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                sum[i+1][j+1] = sum[i+1][j]+sum[i][j+1] - sum[i][j] + board[i][j];
            }   
        }          
        
        for(int i=1;i<sum.length;i++){
            for(int j=1;j<sum[0].length;j++){
                int len = (int)Math.sqrt(answer);
                
                while(i+len <= board.length && j+len <= board[0].length){
                    int cnt = sum[i+len][j+len] - sum[i-1][j+len] 
                        - sum[i+len][j-1] + sum[i-1][j-1];     
                    
                    if(cnt == (len+1)*(len+1)){
                        answer = cnt;
                    }
                    len++;
                }
            }  
        }

        return answer;
    }
}