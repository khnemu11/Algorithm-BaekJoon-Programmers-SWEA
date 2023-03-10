import java.util.*;

/*
    풀이 알고리즘
    
    입력된 큐를 큐의 알고리즘에 맞게 숫자를 빼내어 서로 다른 2개의 큐 안의 수의 합이 같을 때 몇번 꺼내야 하는지 리턴
    
    서로 다른 2개의 큐 안의 수의 합이 같다 == (모든 큐의 수의 합 )/2
    
    만약 한쪽의 큐가 목표 값((모든 큐의 수의 합 )/2)보다 작으면 다른쪽큐의 값을 꺼내서 현재 큐에 삽입
    만약 한쪽의 큐가 목표 값((모든 큐의 수의 합 )/2)보다 크면 현재 큐의 값을 꺼내서 다른쪽 큐로 삽입
    같으면 횟수 출력
    
    이때 같은 숫자가 2번(한쪽 큐, 다른쪽 큐)까지 나타날 수 있고 3번이상 나오면 루프를 돌고 있다는 뜻
    -> 정답은 없다.
    
    걸린시간 : 25분
*/

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        Queue<Num> q1 = new LinkedList<>();
        Queue<Num> q2 = new LinkedList<>();
        long sum[] = new long[3];
        Arrays.fill(sum,0L);
        
        for(int i=0;i<queue1.length;i++){
            q1.add(new Num(i,queue1[i]));
            sum[1]+=queue1[i];
        }
        for(int i=0;i<queue2.length;i++){
            q2.add(new Num(i+queue1.length,queue2[i]));
            sum[2]+=queue2[i];
        }
        
        int visited [] =new int[600_000];
        
        long target = (sum[1]+sum[2])/2;
        int cnt=0;
        boolean isFind =false;

        while(true){
            if(sum[1]==sum[2]){
                isFind=true;
                break;
            }else if(sum[1]<target){
                Num num = q2.poll();
                if(visited[num.seq]>2){
                    break;
                }
                visited[num.seq]++;
                sum[1]+=num.val;
                sum[2]-=num.val;
                q1.add(num);
            }else{
                Num num = q1.poll();
                if(visited[num.seq]>2){
                    break;
                }
                visited[num.seq]++;
                sum[1]-=num.val;
                sum[2]+=num.val;
                q2.add(num);
            }
            cnt++;
        }
        if(isFind){
            answer = cnt;
        }else{
            answer=-1;
        }
        return answer;
    }
}
class Num{
    int seq;
    int val;
    
    public Num(int seq, int val){
        this.seq =seq;
        this.val =val;
    }
    
    public String toString(){
        return " seq : "+seq +" val : "+val;
    }
}
