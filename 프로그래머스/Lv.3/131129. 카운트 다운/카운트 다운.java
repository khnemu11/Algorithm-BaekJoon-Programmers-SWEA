import java.util.*;

class Solution {
    int TIME = 0;
    int COUNT = 1;
    public int[] solution(int target) {
        int[] answer = new int[2];
        int[] time = new int[target+1]; //도착하는 최소 시간(횟수)
        Arrays.fill(time,Integer.MAX_VALUE);
        time[0] = 0;
        
        int[] count = new int[target+1];   //싱글 혹은 불을 던진 최대값
        
        PriorityQueue<Round> pq = new PriorityQueue<>();
        pq.add(new Round(0,0,0));
        
        while(!pq.isEmpty()){
            Round round = pq.poll();
            
            //목표 점수에 도달하면 그만
            if(round.score == target){
                answer[TIME] = round.time;
                answer[COUNT] = round.count;
                break;
            }
            
            //다른 최소 방법이 있으면 스킵
            if(round.time > time[round.score]){
                continue;
            }
            if(round.time == time[round.score]
               && round.count < count[round.score]){
                continue;
            }
            
             //불 던지기
            if(round.score+ 50 <= target
                && time[round.score+50] > round.time + 1
                && count[round.score+50] < round.count + 1){
                    time[round.score+50] = round.time + 1;
                    count[round.score+50] = round.count + 1;
                    
                    pq.add(new Round(count[round.score+50],time[round.score+50],round.score+50));
            }
            
            //싱글/더블/트리플 던지기
            for(int mul=1;mul<=3;mul++){
                for(int i=1;i<=20;i++){
                    Round next = new Round(mul == 1 ? round.count+1 : round.count,round.time+1,round.score+i*mul);

                    //점수가 목표보다 넘으면 현재 배수로 그만 던지기
                    if(next.score >target){
                        break;
                    }
                    
                    // System.out.println("현재 점수 : "+next.score);
                    
                    //현재 경우의 수가 더 빠른 경우
                    if(time[next.score] > next.time){
                        time[next.score] = next.time;
                        count[next.score] = next.count;

                        pq.add(new Round(next.count,next.time,next.score));
                    }
                    
                    //현재 경우의 수와 최소값이 같은데 싱글or불 맞춘 횟수가 더 많은 경우 
                    else if(time[next.score] == next.time
                                && count[next.score] < next.count){
                        count[next.score] = next.count;
                        pq.add(new Round(next.count,next.time,next.score));
                    }
                }
            }
            // System.out.println(Arrays.toString(time));
        }
        
       
        
        return answer;
    }
}
class Round implements Comparable<Round>{
    int count;
    int time;
    int score;
    
    public Round(int count, int time,int score){
        this.count = count;
        this.time = time;
        this.score = score;
    }
    
    public int compareTo(Round o){
        if(this.time == o.time){
            return o.count - this.count;
        }
        return this.time - o.time;
    }
}