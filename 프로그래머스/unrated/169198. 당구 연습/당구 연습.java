import java.util.*;

/*
    삼각함수로 하면 실수형을 사용해야 하기 때문에 오차가 생길 수 있음
    입사각과 반사각이 같으므로 대상 공을 4축으로 대칭했을 때 일직선의 길이와 원쿠션 길이와 같다.
    -> 각 점을 대칭으로 돌려 일직선의 길이의 제곱을 구하자
    
    이때 공 끼리의 경로가 일직선인 경우 쿠션을 하기 전에 공에 닿으므로 제외해야한다.
*/

class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        
        int x= startX;
        int y = startY;
        // System.out.println(m+"X"+n);
        for(int i=0;i<balls.length;i++){                                                   
            int distance[] = new int[4];
            Arrays.fill(distance,Integer.MAX_VALUE);
            // System.out.println("#"+i);
            //y축 수평이 아닌 경우
            if(!(y==balls[i][1] && x>balls[i][0])){
                //y축 왼쪽 대칭
                distance[0] = (int)Math.pow(balls[i][0]*-1 -x,2) + (int)Math.pow(balls[i][1] -y,2);
                // System.out.println((balls[i][0]*-1)+" , "+balls[i][1]);
            } if(!(y==balls[i][1] && x<balls[i][0])){
                //y축 오른쪽 대칭
                distance[2] = (int)Math.pow(m+m-balls[i][0]-x,2) + (int)Math.pow(balls[i][1] -y,2);
                // System.out.println((m+m-balls[i][0])+" , "+balls[i][1]);
            }
            //x축 수평이 아닌경우
            if(!(x==balls[i][0] && y>balls[i][1])){
                //x축 왼쪽 대칭
                distance[1] = (int)Math.pow(balls[i][0] -x,2) + (int)Math.pow(balls[i][1]*-1 -y,2);
                // System.out.println(balls[i][0]+" , "+balls[i][1]*-1);
                //x축 오른쪽 대칭
            }if(!(x==balls[i][0] && y<balls[i][1])){
                distance[3] = (int)Math.pow(balls[i][0] -x,2) + (int)Math.pow(n+n-balls[i][1]-y,2);
                // System.out.println(balls[i][0]+" , "+(n+n-balls[i][1]));
            }
            //일직선 거리를 제외한 최소값 저장
            answer[i] = Integer.MAX_VALUE;
            // System.out.println(Arrays.toString(distance));
            for(int j=0;j<distance.length;j++){
                answer[i]= Math.min(answer[i],distance[j]);
            }
        }
    
        return answer;
    }
}
