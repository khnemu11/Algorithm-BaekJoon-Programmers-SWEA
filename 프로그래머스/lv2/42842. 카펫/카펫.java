import java.util.*;

/*
    갈색 영역의 너비 = (전체 넓이의 가로 - 2(끝테두리))*2(2개) + (전체 넓이의 세로 - 2(끝테두리))*2(2개) + 4개(양 끝 테두리)
    if 갈색 영역의 너비 == 주어진 brown값 ==> 카펫의 너비
    
    카펫의 너비는 최대 sqrt(전체 카펫의 넓이) 까지 된다

    시간 복잡도 = O(sqrt(brown + yellow))
*/

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
