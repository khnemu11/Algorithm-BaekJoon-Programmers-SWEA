
/*
    1 2 2 3

*/
class Solution {
    public int[] solution(int[] sequence, int k) {
        int r = sequence.length-1;
        int l=r;
        int answer[]=new int[2];
        int sum = sequence[r];
        while(r>=0){
            while(l>=0){
                if(sum>=k){
                    break;
                }
                l--;
                sum+=sequence[l];
            }
            
            if(sum == k){
                break;
            }
            
            sum-=sequence[r];
            r--;
        }
        
        while(l>0 && r>0){
            if(sum + sequence[l-1] - sequence[r] != sum){
                break;
            }
            
            l--;
            r--;
        }
        
        
        answer[0]=l;
        answer[1]=r;
        
        return answer;
    }
}