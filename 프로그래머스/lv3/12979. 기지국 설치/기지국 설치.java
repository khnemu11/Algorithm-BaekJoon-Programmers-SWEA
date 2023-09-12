import java.util.*;
/*
    N = 20억이므로 logN이거나 stations이 10,000개 이므로 최대 stations * log stations의 알고리즘을 찾아야한다.
    (기지국 1개가 커버 가능한 영역) = w *2 +1
    한 영역에서 세울 수 있는 기지국 최소 개수 = 반올림(((끝점) - (시작점) +1)/ (기지국 1개가 커버 가능한 영역))
    이때 기지국이 겹치는 부분은 스킵한다.
*/
class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int start = 1; //시작점
        
        for(int i=0;i<stations.length;i++){
            int nextStart = stations[i] + w +1; //다음 시작점 
            int end = stations[i] - w -1; 
            
            //이전 끝 점이 현재 끝점 보다 큰 경우 이미 겹치는 곳이므로 탐색하지 않아도 된다.
            if(end  < start){
                start = nextStart;
                continue;
            }
            
            answer += (int)Math.ceil((double)(end - start +1)/(w*2+1));
            
            start = nextStart;
        }
        
        //아직 끝까지 가지 않았다면 나머지 영역에 기지국 설치
        if(start <= n){
              answer += (int)Math.ceil((double)(n - start +1)/(w*2+1));
        }

        return answer;
    }
}