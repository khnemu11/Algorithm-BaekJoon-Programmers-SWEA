import java.util.*;

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
            // System.out.println(sum[1]+" "+sum[2]);
            // System.out.println(q1+" \n"+q2);
            
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