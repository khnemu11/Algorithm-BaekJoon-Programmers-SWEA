import java.util.*;

/*
    내 순위를 안다 = 나를 이긴 사람과 내가 이긴 사람의 수가 n-1개이다.
    = 부모와 자식으로 각각 탐색을 했을 때 모든 곳을 탐색했다.
*/

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        ArrayList<Integer> parentToChildGraph[] = new ArrayList[n+1];
        ArrayList<Integer> childToParentGraph[] = new ArrayList[n+1];
        
        for(int i=0;i<=n;i++){
            parentToChildGraph[i] = new ArrayList<>();
            childToParentGraph[i] = new ArrayList<>();
        }
        
        for(int i=0;i<results.length;i++){
            parentToChildGraph[results[i][0]].add(results[i][1]);
            childToParentGraph[results[i][1]].add(results[i][0]);
        }
        
        for(int start=1;start<=n;start++){
            boolean visited[] = new boolean[n+1];
            search(parentToChildGraph,visited,start);
            search(childToParentGraph,visited,start);

            if(canGetScore(visited)){
                answer++;
            }
        }
        
        return answer;
    }
    public boolean canGetScore(boolean visited[]){
        for(int i=1;i<visited.length;i++){
            if(!visited[i]){
                return false;
            }
        }
        return true;
    }
    public void search(ArrayList<Integer> graph[],boolean visited[],int curr){
        visited[curr]=true;
        
        for(int child : graph[curr]){
            if(visited[child]){
                continue;
            }
            search(graph,visited,child);
        }
    }
}