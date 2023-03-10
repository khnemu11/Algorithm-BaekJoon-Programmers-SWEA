import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int ALP_REQ = 0;
        int COP_REQ = 1;
        int ALP_RWD = 2;
        int COP_RWD = 3;
        int COST = 4;
    
        int maxAlp=alp;
        int maxCop=cop;
        
        for(int i=0;i<problems.length;i++){
            maxAlp = Math.max(problems[i][ALP_REQ],maxAlp);
            maxCop = Math.max(problems[i][COP_REQ],maxCop);
        }
        
        int time[][] = new int[maxAlp+1][maxCop+1];
        
        for(int i=0;i<time.length;i++){
            Arrays.fill(time[i],1_000);
        }
        
        time[alp][cop]=0;
        PriorityQueue<Status>pq = new PriorityQueue<>();
        Status start = new Status(alp,cop,time[alp][cop]);
        pq.add(start);
        
        while(!pq.isEmpty()){
            Status curr =pq.poll();
            
            if(curr.time > time[curr.alp][curr.cop]){
                continue;
            }

            for(int [] problem : problems){
                if(curr.alp < problem[ALP_REQ] || curr.cop < problem[COP_REQ]){ //현재 알고력으로 못푸는 경우
                    continue;
                }
                
                int nextAlp = curr.alp + problem[ALP_RWD] > maxAlp ? maxAlp : curr.alp + problem[ALP_RWD];
                int nextCop = curr.cop + problem[COP_RWD] > maxCop ? maxCop : curr.cop + problem[COP_RWD];
                
                if(time[nextAlp][nextCop] > time[curr.alp][curr.cop]+problem[COST]){
                    time[nextAlp][nextCop]  = time[curr.alp][curr.cop]+problem[COST];
                    pq.add(new Status(nextAlp,nextCop,time[nextAlp][nextCop]));
                }
            }
            
            int nextAlp = curr.alp + 1 > maxAlp ? maxAlp : curr.alp + 1;
            int nextCop = curr.cop;
    
            if(time[nextAlp][nextCop] > time[curr.alp][curr.cop]+1){
                time[nextAlp][nextCop]  = time[curr.alp][curr.cop]+1;
                pq.add(new Status(nextAlp,nextCop,time[nextAlp][nextCop]));
            }
            
            nextAlp = curr.alp ;
            nextCop = curr.cop + 1 > maxCop ? maxCop : curr.cop + 1;
            
            if(time[nextAlp][nextCop] > time[curr.alp][curr.cop]+1){
                time[nextAlp][nextCop]  = time[curr.alp][curr.cop]+1;
                pq.add(new Status(nextAlp,nextCop,time[nextAlp][nextCop]));
            }
        }
        return time[maxAlp][maxCop];
    }
    
    public static void printArr(int arr[][]){
        for(int i=0;i<arr.length;i++){
         for(int j=0;j<arr[0].length;j++){
          System.out.print(arr[i][j]);  
        }   
            System.out.println();
        }
    }
}
class Status implements Comparable<Status>{
    int alp;
    int cop;
    int time;
    
    public Status(int alp, int cop,int time){
        this.alp=alp;
        this.cop =cop;
        this.time=time;
    }
    
    public int compareTo(Status o){
        return this.time - o.time;
    }
    
    public String toString(){
        return "알고력 : "+alp +" 코딩력 : "+cop;
    }
}