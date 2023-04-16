/*
    4-2+1, 8-4+1 12 -6 + 1
    좌표의 개수 = 변의 길이 * 2 -1 
    -> 오류 :반지름이 모든 양의 x 좌표에서 반지름의 길이 -1 개의 좌표를 가질 수 있는것은 아니다
    
    해결법: 원의 방정식을 이용해 직접 풀이
    
    좌표 x에서 좌표를 가질 수 있는 개수 = 원의 방정식에서 x좌표에서 가질 수있는 최대 y좌표의 정수값 
                                   = 내림((루트(반지름^2 - x좌표^2)))
                                   
    
    r2에서 가질 수 있는 좌표에서 r1이 가질 수 있는 좌표개수를 빼면 x좌표에서 가질 수 있는 좌표의 개수가 나온다
    이때 r1이 좌표에 접할 수 있으므로 이럴 경우 1을 더해준다.(r1의 길이인 경우 제외)
    
    0좌표와 r1,r2인 경우 무조건 가능한 좌표이므로 따로 확인
    
    총 개수 = 한 사분면(1~r2-1)에서 가능한 좌표*4 + 끝좌표에서 가능한 경우(r2-r1+1) * 4(사분면의 개수 
*/

import java.util.*;

class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        
        double powR2 = Math.pow(r2,2);
        double powR1 = Math.pow(r1,2);
        
        long cnt = 0;
        
        for(int x=1;x<r2;x++){
            long r2Cnt = (long)Math.floor(Math.sqrt(powR2 - Math.pow(x,2)));
            //r1의 내부 좌표인 경우 해당 좌표 제거
            if(x <=r1){
                long r1Cnt = (long)Math.floor(Math.sqrt(powR1 - Math.pow(x,2)));
                cnt += r2Cnt - r1Cnt;
                
                //이때 인접한 좌표인경우 해당 좌표도 가능하므로 1더해준다
                //x좌표가 r1인 경우 나중에 따로 처리하므로 제외
                if(x!=r1 && Math.sqrt(powR1 - Math.pow(x,2)) == Math.floor(Math.sqrt(powR1 - Math.pow(x,2)))){
                    cnt++;
                }
            }else{
                cnt+=r2Cnt;
            }
        }
        //사분면의 개수만큼 곱해주고 끝점인 경우도 더해준다
        cnt = cnt*4 + (r2-r1+1)*4;
        return cnt;
    }
}