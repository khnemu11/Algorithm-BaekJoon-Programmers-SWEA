/*
    풀이 과정
    양인 노드를 선택, 이때 현재 늑대의 수+ 선택한 늑대의수 >= 현재 양의수 + 선택한 양의 수 가 되지 않도록 한다.
    -> 프림 알고리즘 사용
    더이상 갈 곳이 없다면 탐색을 종료하고 선택한 양의 개수를 출력
    
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