class Solution {
    public long solution(int price, int money, int count) {
        long need = 0;
        
        for(int i=1;i<=count;i++){
            need +=price*i;
        }
        
        return money - need >= 0 ? 0 : need - money;
    }
}