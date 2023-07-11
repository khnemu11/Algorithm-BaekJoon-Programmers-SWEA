/*
    2^20밖에 안되니까 완탐으로
*/
import java.util.*;

class Solution{
    public int solution(int n, int a, int b){
        int answer = 0;

        Queue<Integer> q = new LinkedList<>();
        
        for(int i=1;i<=n;i++){
            q.add(i);
        }
        
        while(true){
            boolean find = false;
            int size = q.size()/2;
            answer++;
            while(size-->0){
                int first = q.poll();
                int second = q.poll();
                
                if((first == a && second == b) || (first == b && second == a)){
                    find=true;
                    break;
                }else if(first == a || second == a){
                    q.add(a);
                }else if(first == b || second == b){
                    q.add(b);
                }else{
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