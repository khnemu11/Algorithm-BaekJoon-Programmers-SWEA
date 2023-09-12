import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int start = 1;
        // Queue<Range> q = new LinkedList<>();
        
        for(int i=0;i<stations.length;i++){
            int nextStart = stations[i] + w +1; 
            int end = stations[i] - w -1;

            if(end  < start){
                start = nextStart;
                continue;
            }
            
            answer += (int)Math.ceil((double)(end - start +1)/(w*2+1));
            
            // q.add(new Range(start, end));
            start = nextStart;
        }
        
        if(start <= n){
              answer += (int)Math.ceil((double)(n - start +1)/(w*2+1));
        }
        // System.out.println(q);
                
//         while(!q.isEmpty()){
//             Range range = q.poll();
//             // System.out.println(range +" "+Math.ceil((double)(range.end - range.start +1)/(w*2+1)));
//             answer += (int)Math.ceil((double)(range.end - range.start +1)/(w*2+1));
//         }
        

        return answer;
    }
}

class Range{
    int start;
    int end;
    
    public Range(int start, int end){
        this.start = start;
        this.end = end;
    }
    
    public String toString(){
        return start +" ~ "+end;
    }
}