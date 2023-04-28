/*
    5 10
    14 
    18 30 102
*/

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        
        return Math.max(divideCard(arrayA,arrayB),divideCard(arrayB,arrayA));
    }
    public int divideCard(int arrayA[],int arrayB[]){
        int a = arrayA[0];
        for(int i=1;i<arrayA.length;i++){
            a = getGcd(a,arrayA[i]);
        }
        for(int num : arrayB){
            if(num%a==0){
                return 0;
            }
        }
        return a;
    }
    public int getGcd(int a, int b){
        if(a%b==0){
            return b;
        }else{
            return getGcd(b,a%b);
        }
    } 
}