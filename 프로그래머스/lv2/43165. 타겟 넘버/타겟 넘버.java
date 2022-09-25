class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        
        answer = bfs(0,0,target,numbers);
        
        return answer;
    }
    
    public int bfs(int curr,int sum,int target,int[]numbers){
        if(curr == numbers.length){
            if(sum==target){
                return 1;
            }
            else{
                return 0;
            }
        }
        else{
            return bfs(curr+1,sum+numbers[curr],target,numbers)+ bfs(curr+1,sum-numbers[curr],target,numbers);
        }
    }
}