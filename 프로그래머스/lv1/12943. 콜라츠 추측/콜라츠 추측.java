class Solution {
    public int solution(int num) {
        return dfs(0,num);
    }
    
    public int dfs(int curr, long val){
        if(curr==500){
            return -1;
        }
        else if(val == 1){
            return curr;
        }
        else if(val%2==0){
            return dfs(curr+1,val/2);
        }
        else{
            return dfs(curr+1,val*3+1);
        }
    }
}