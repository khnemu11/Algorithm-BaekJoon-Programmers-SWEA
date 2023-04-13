import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        int total = brown + yellow;
        int sqrtTotal = (int)Math.sqrt(total);
        int[] answer = new int[2];
        
        for(int height=3;height<=sqrtTotal;height++){
            if(total%height!=0){
                continue;
            }
            
            int width = total / height;
            
            if((width-2)*2 + (height-2)*2+ 4 == brown){
                answer[0]=width;
                answer[1]=height;
                break;
            }
        }

        
        return answer;
    }
}