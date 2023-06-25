class Solution {
    public int solution(int n) {
        //3진수로 변경
        //이때 앞뒤 반전을 위해 stringbuilder사용
        StringBuilder sb = new StringBuilder(Integer.toString(n,3)); 
        
        //앞뒤 반전 후 10진수로 변경
        return Integer.parseInt(sb.reverse().toString(),3);
    }
}