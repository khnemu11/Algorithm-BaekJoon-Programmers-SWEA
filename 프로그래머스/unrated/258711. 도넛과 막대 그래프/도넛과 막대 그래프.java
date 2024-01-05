import java.util.*;

class Solution {
    final int N = 1_000_000;
    final int UNKNOWN = -1;
    final int CREATE_NODE = 0;
    final int DONUT = 1;
    final int STICK = 2;
    final int EIGHT = 3;
    
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        boolean[] exist = new boolean[N+1];
        
        //그래프 초기화
        List<List<Integer>> graph = new ArrayList<>();
        List<List<Integer>> reverseGraph = new ArrayList<>();
        
        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }
        
        List<Integer> eightStartNodeList = new ArrayList<>();
        
        //그래프 생성
        for(int i=0;i<edges.length;i++){
            exist[edges[i][0]]=true;
            exist[edges[i][1]]=true;
            
            graph.get(edges[i][0]).add(edges[i][1]);
            reverseGraph.get(edges[i][1]).add(edges[i][0]);
            
            if(graph.get(edges[i][0]).size()==2){
                eightStartNodeList.add(edges[i][0]);
            }
        }
        
        boolean[] visited = new boolean[N+1];
        
        //8자 그래프 방문 처리 및 생성된 정점 탐색
        for(int start : eightStartNodeList){
            if(reverseGraph.get(start).size() ==0){
                visited[start] = true;
                answer[CREATE_NODE] = start;
                continue;
            }
            
            Queue<Integer> q = new LinkedList<>();
            q.add(start);
            
            while(!q.isEmpty()){
                int curr = q.poll();
                visited[curr] = true;
                
                for(int next : graph.get(curr)){
                    if(visited[next]){
                        continue;
                    }
                    q.add(next);
                }
            }
            
            answer[EIGHT]++;
        }
        //나머지 정점 처리
        //시작점으로 돌아오면 도넛
        //아닌데 이미 방문했으면 1자
        for(int start=1;start<graph.size();start++){
            //8자 그래프이거나 생성된 정점인경우
            if(visited[start]){
                continue;
            }
            //없는 정점인 경우
            else if(!exist[start]){
                continue;
            }
            
            int type = UNKNOWN;
            
            Queue<Integer> q = new LinkedList<>();
            q.add(start);
            
            while(!q.isEmpty()){
                int curr = q.poll();
                
                //도넛그래프인 경우
                if(visited[curr]){
                    type = DONUT;
                    break;
                }
                
                visited[curr] = true;
                
                //막대 그래프인 경우
                if(graph.get(curr).size()==0){
                    type = STICK;
                    break;
                }
                
                for(int next : graph.get(curr)){
                    q.add(next);
                }
            }
            
            if(type == STICK){
                //역방향 방문처리
                q = new LinkedList<>();
                q.add(start);
                
                while(!q.isEmpty()){
                    int curr = q.poll();

                    visited[curr] = true;

                    for(int next : reverseGraph.get(curr)){
                        q.add(next);
                    }
                }
                
                answer[STICK]++;
            }else if(type == DONUT){
                answer[DONUT]++;               
            }
        }

        return answer;
    }
}