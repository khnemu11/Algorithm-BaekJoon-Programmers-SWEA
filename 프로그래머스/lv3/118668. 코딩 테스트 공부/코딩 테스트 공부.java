import java.util.*;

/*
    풀이 알고리즘

    목표 : 모든 문제를 풀 수 있을 때 걸리는 시간
    
    시작 능력 : 입력 알고력, 입력 코딩력
    목표 능력 : 문제 중 가장 큰 알고력보다 크거나 같은 알고력, 문제 중 가장 큰 코딩력보다 크거나 같은 코딩력
    
    (알고력,코딩력)으로 좌표화 시 (문제 중 가장 큰 알고력,문제 중 가장 큰 코딩력)까지의 걸린 시간을 그래프로 표현 가능
    비용 : 시간
    
    점과 점사이의 최단 비용 -> 다익스트라
    
    현재 능력(노드)으로 문제를 못 품 -> 도달할 수 없는 문제(노드)
    현재 능력(노드)으로 문제를 풀 수 있음 -> 도달할 수 있는 문제(노드)
    비용(시간) 1을 가지고 있는 코딩력1 또는 알고력 1을 올리는 문제 -> 항상 도달할 수 있는 노드
    
    시간을 정렬기준으로 우선순위큐를 만들어 다익스트라 알고리즘 적용
    
    걸린시간 : 40분
*/

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int ALP_REQ = 0;    //문제의 인덱스를 변수화
        int COP_REQ = 1;
        int ALP_RWD = 2;
        int COP_RWD = 3;
        int COST = 4;
    
        int maxAlp=alp; //최대 알고력,코딩력의 초기값
        int maxCop=cop;
        
        for(int i=0;i<problems.length;i++){
            maxAlp = Math.max(problems[i][ALP_REQ],maxAlp); //알고력의 최대값,최소값 최신화
            maxCop = Math.max(problems[i][COP_REQ],maxCop);
        }
        
        int time[][] = new int[maxAlp+1][maxCop+1]; //점 (알고력, 코딩력)과 점사이의 최단 비용(시간)
        
        for(int i=0;i<time.length;i++){
            Arrays.fill(time[i],2_000); //최대 가능한 비용이 100(문제 개수)*10(최대 비용) 이므로 이보단 큰 값으로 INF설정
        }
        
        time[alp][cop]=0;   //시작점 초기화
        PriorityQueue<Status>pq = new PriorityQueue<>();
        Status start = new Status(alp,cop,time[alp][cop]);
        pq.add(start);
        
        while(!pq.isEmpty()){
            Status curr =pq.poll(); //현재 능력
            
            if(curr.time > time[curr.alp][curr.cop]){ //만약 현재 능력까지의 비용이 최단 비용보다 크면 탐색할 가치가 없으므로 가지치기
                continue;
            }

            for(int [] problem : problems){ //모든 문제 탐색
                if(curr.alp < problem[ALP_REQ] || curr.cop < problem[COP_REQ]){ //현재 알고력으로 못푸는 경우
                    continue;
                }
                //다음 알고력, 코딩력이 최대값보다 크다면 배열을 벗어나게 되므로 최대값으로 재설정
                int nextAlp = curr.alp + problem[ALP_RWD] > maxAlp ? maxAlp : curr.alp + problem[ALP_RWD];
                int nextCop = curr.cop + problem[COP_RWD] > maxCop ? maxCop : curr.cop + problem[COP_RWD];
                
                //현재 능력에서 다음 문제를 푸는것이 최단비용보다 작을경우 최단비용 최신화
                if(time[nextAlp][nextCop] > time[curr.alp][curr.cop]+problem[COST]){
                    time[nextAlp][nextCop]  = time[curr.alp][curr.cop]+problem[COST];
                    pq.add(new Status(nextAlp,nextCop,time[nextAlp][nextCop]));
                }
            }
            //시간 1의 알고력 증가 문제를 해결할 때 최단비용 판정
            int nextAlp = curr.alp + 1 > maxAlp ? maxAlp : curr.alp + 1;
            int nextCop = curr.cop;
    
            if(time[nextAlp][nextCop] > time[curr.alp][curr.cop]+1){
                time[nextAlp][nextCop]  = time[curr.alp][curr.cop]+1;
                pq.add(new Status(nextAlp,nextCop,time[nextAlp][nextCop]));
            }
            
            //시간 1의 코딩력 증가 문제를 해결할 때 최단비용 판정
            nextAlp = curr.alp ;
            nextCop = curr.cop + 1 > maxCop ? maxCop : curr.cop + 1;
            
            if(time[nextAlp][nextCop] > time[curr.alp][curr.cop]+1){
                time[nextAlp][nextCop]  = time[curr.alp][curr.cop]+1;
                pq.add(new Status(nextAlp,nextCop,time[nextAlp][nextCop]));
            }
        }
        //최소 시간 리턴
        return time[maxAlp][maxCop];
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
}
