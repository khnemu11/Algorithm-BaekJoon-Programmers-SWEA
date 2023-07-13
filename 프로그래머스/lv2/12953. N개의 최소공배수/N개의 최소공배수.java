class Solution {
    public int solution(int[] arr) {
        int answer = arr[0];
        for(int i=1;i<arr.length;i++){
            answer =arr[i]*answer/getLCM(Math.max(arr[i],answer),Math.min(arr[i],answer));
        }
        
        return answer;
    }
    public int getLCM(int a, int b){
        if(a%b ==0){
            return b;
        }
        return getLCM(b,a%b);
    }
}