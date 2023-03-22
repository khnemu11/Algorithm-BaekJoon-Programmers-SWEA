
/*
    누적합 알고리즘
    만약 0 ~ n번째 까지의 값이 1이라면 i+1번째는 i번째 + 0이고 f(0)=1이므로 f(i+1)=1이라고 표현할 수 있다.
    f(i+1) = f(i)+0;
    이때 n+1번째에도 똑같이 적용하면 n까지가 아니라 n+1까지 1이 된다.
    이를 막기위해 n+1에 -1를 넣어 더이상 1을 더하지 않게 한다.
    이를 전체적으로 적용해 증가량을 시작점과 끝점에 저장하면 누적합으로 한번에 범위의 값을 넣을 수 있다.
    
*/
class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer=0;
        
        int preSum[][] = new int[board.length+1][board[0].length+1];
       
        for(int skill_row[] : skill){
            int attack = skill_row[0] ==1 ? -skill_row[5] : skill_row[5];
            
            preSum[skill_row[1]][skill_row[2]] += attack;
            preSum[skill_row[3]+1][skill_row[4]+1] += attack;
            preSum[skill_row[1]][skill_row[4]+1] += -attack;
            preSum[skill_row[3]+1][skill_row[2]] += -attack;
            
            // printArr(preSum);
        }
        
        for(int i=0;i<preSum.length;i++){
            for(int j=1;j<preSum[0].length;j++){
                preSum[i][j] += preSum[i][j-1];
            }
        }
        for(int i=1;i<preSum.length;i++){
            for(int j=0;j<preSum[0].length;j++){
                preSum[i][j] += preSum[i-1][j];
            }
        }
       
        
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(preSum[i][j] + board[i][j] > 0){
                    answer++;
                }
            }
        }
        
        return answer;
    }
    
    public void printArr(int board[][]){
         for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                System.out.print(board[i][j]+" ");
            }   
              System.out.println();
        }
         System.out.println();
    }
}