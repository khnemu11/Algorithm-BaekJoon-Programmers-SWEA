/*
    풀이 과정
    현재 노드에서 고를 수 있는 다음 노드의 상태를 계속 저장하여 완전탐색
    방문처리와 선택한 노드의 처리를 동시에 적용
    dfs와 백트래킹 사용
    
    0 : 양
    1 : 늑대
*/
import java.util.*;
class Solution {
    static ArrayList<ArrayList<Integer>> graph;
    static int maxSheep=1;
    
    public int solution(int[] info, int[][] edges) {
        int answer=0;
        graph = new ArrayList<>();
        
        for(int i=0;i<=info.length;i++){
            graph.add(new ArrayList<>());

        }
        
        for(int i=0;i<edges.length;i++){
            graph.get(edges[i][0]).add(edges[i][1]);
        }
        
        select(0,info,new boolean[info.length],new boolean[info.length],0,0);
        
        return maxSheep;
    }
    public void select(int idx, int info[],boolean visited[],boolean candidates[],int sheepCnt, int wolfCnt){
        if(info[idx]==0){
            sheepCnt++;
            maxSheep = Math.max(maxSheep, sheepCnt);
        }else{
            wolfCnt++;
        }
        visited[idx] = true;
        for(int to : graph.get(idx)){
            candidates[to]=true;
        }
        
        for(int i=0;i<candidates.length;i++){
            if(!candidates[i] || visited[i]){
                continue;
            }
            
            if(info[i]==1){
                if(wolfCnt+1>=sheepCnt){
                    continue;
                }
                select(i,info,visited,candidates,sheepCnt,wolfCnt);
            }else{
                select(i,info,visited,candidates,sheepCnt,wolfCnt);
            }
        }
        
        for(int to : graph.get(idx)){
            candidates[to]=false;
        }
        visited[idx] = false;
    }
}
