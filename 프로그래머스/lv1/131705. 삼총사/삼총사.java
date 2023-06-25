import java.util.*;

class Solution {
    static int cnt=0;
    static int LENGTH=3;
    public int solution(int[] number) {
        select(number,0,0,0);
        return cnt;
    }
    public void select(int num[], int idx, int sum, int length){
        if(length == LENGTH){
            if(sum == 0){
                cnt++;
            }
            return ;
        }
        for(int i= idx; i<num.length;i++){
            select(num,i+1,sum+num[i],length+1);
        }
    }
}