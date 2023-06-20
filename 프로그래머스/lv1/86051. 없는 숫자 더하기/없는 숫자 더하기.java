/*
    1~9를 모두 더한 값에서 numbers의 값을 빼주기
*/

class Solution {
    public int solution(int[] numbers) {
        int sum = (9*10)/2;
        
       for(int n : numbers){
            sum-=n;
        }
        
        return sum;
    }
}