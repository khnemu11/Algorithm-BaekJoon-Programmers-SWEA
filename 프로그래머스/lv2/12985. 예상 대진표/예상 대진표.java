/*
    2^20밖에 안되니까 완탐으로
    
    걸린 시간 : 8분 21초
*/
import java.util.*;

class Solution{
    public int solution(int n, int a, int b){
        int answer = 0;

        Queue<Integer> q = new LinkedList<>();
        
        //n까지의 모든 숫자 넣기
        for(int i=1;i<=n;i++){
            q.add(i);
        }
        
        //라운트 별로 a와 b가 동시에 나올 때 까지 무조건 올리기
        while(true){
            boolean find = false;
            int size = q.size()/2;
            answer++;
            while(size-->0){
                int first = q.poll();
                int second = q.poll();
                //찾음
                if((first == a && second == b) || (first == b && second == a)){
                    find=true;
                    break;
                }
                //a올리기
                else if(first == a || second == a){
                    q.add(a);
                }
                //b올리기
                else if(first == b || second == b){
                    q.add(b);
                }
                //아무거나 올리기
                else{
                    q.add(first);
                }
            }
            
            if(find){
                break;
            }
        }
        
        return answer;
    }
}