class Solution {
    public int solution(int a, int b, int n) {
        int cokes = n;
        int getCnt = 0;
        
        //가게에 줄 빈병이 생길때 까지 a*n만큼 줘서 b*n만큼 받는 부분
        while(cokes >= a){
            int extraCokes = cokes/a*b;
            cokes = cokes%a + extraCokes;
            getCnt +=extraCokes;    
        }
        
        return getCnt;
    }
}