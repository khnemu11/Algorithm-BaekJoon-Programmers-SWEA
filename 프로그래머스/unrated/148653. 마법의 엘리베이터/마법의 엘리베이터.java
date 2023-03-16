import java.util.*;

class Solution {
    int min = Integer.MAX_VALUE;
    
    public int solution(int storey) {
        int answer =0;
        
        selectUpDown(0,storey,0);
        
        return min;
    }
        
    public void selectUpDown(int digit, int storey,int sum){
        if(digit==8 || storey==0){
            min = Math.min(Math.abs(0 - storey)+sum,min);
        }else{
            String num[] = String.valueOf(storey).split("");
            int lastNum = Integer.valueOf(num[num.length-1]);
            // System.out.println("last num : "+lastNum);
            if(min > sum+lastNum){
                selectUpDown(digit+1,storey/10,sum+lastNum);
            }
           
            int differ = 10 - lastNum;
            
            if(lastNum!=0 && sum+differ<min){
                selectUpDown(digit+1,(storey+differ)/10,sum+differ);
            }
        }
    }
}