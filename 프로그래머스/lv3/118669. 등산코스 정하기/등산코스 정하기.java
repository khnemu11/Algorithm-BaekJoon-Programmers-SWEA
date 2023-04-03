/*
풀이 알고리즘
  점과 점사이 모두 연결하는 최소비용 : MST
  시작점과 봉우리만 연결하면 되므로 경로를 최소만 선택해서 산봉우리가 연결되면 해당 봉우리와 이때 최대 경로 비용을 출력하도록
  노드 중심 MST -> 프림
  X -> 이유는 명확히 알 수 없으나 그래프 설정을 잘못 했거나 모든 경로를 확인하기 때문에 시간초과가 나지 않았나 싶음
  -> 알고리즘을 간단하게 해서 디버깅을 편하게 하자
  -> 다익스트라 사용  

   그래프를 생성이 산봉우리는 시작점이 될 수 없고 게이트는 끝점이 될 수 없음을 반영하여 그려야한다.
   
   시작 -> 끝 최소 비용 = min (시작 -> 끝 최소비용 , max(시작 비용, 중간 비용))
   모든 게이트부터 최단거리를 구해서 끝점이 산봉우리인 것 중 최소값 및 해당 게이트를 리턴
  
*/

import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int answer[]=new int[2];
        
        boolean isGate[] = new boolean[n+1];
        for(int gate : gates){
            isGate[gate]=true;
        }
        
        boolean isSummit[] = new boolean[n+1];
        for(int summit : summits){
            isSummit[summit]=true;
        }
        
        ArrayList<Node> graph[] = new ArrayList[n+1];
        for(int i=0;i<=n;i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0;i<paths.length;i++){
            if(isGate[paths[i][0]] || isSummit[paths[i][1]]){ //산봉우리가 끝점이거나 게이트가 시작점인 경우 
                graph[paths[i][0]].add(new Node(paths[i][1],paths[i][2]));
            }else if(isGate[paths[i][1]] || isSummit[paths[i][0]]){ //산봉우리가 시작점이거나 게이트가 끝점인 경우
                graph[paths[i][1]].add(new Node(paths[i][0],paths[i][2]));
            }else{  //지점 간의 경로인 경우
                 graph[paths[i][1]].add(new Node(paths[i][0],paths[i][2]));
                 graph[paths[i][0]].add(new Node(paths[i][1],paths[i][2]));
            }
        }

        int minCost = Integer.MAX_VALUE;    //최소 비용
        int minSummit = Integer.MAX_VALUE; //최소 비용일 때 산봉우리
        
        for(int gate : gates){
            int distance[] = new int[n+1];
            Arrays.fill(distance, 20_000_000);
            distance[gate]=0;
            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(gate,0));
                
            while(!pq.isEmpty()){ //다익스트라 시작
                Node curr = pq.poll();
             
                if(distance[curr.val] < curr.cost){ //현재 알고있는 시작->끝점의 최소 거리가 현재 노드의 시작->끝보다 작으면 연산 X
                    continue;
                }
                else if(curr.cost > minCost){ // 현재 노드의 비용이 전체 최소 비용보다 크면 앞으로 작아질일 이 없으므로 연산 X 
                    continue;
                }
                
                for(Node to : graph[curr.val]){
                    int maxCost = Math.max(to.cost, curr.cost); // 시작 -> 중간 비용과 중간-> 끝의 비용 중 큰값이 intesity이므로 이를 반영
                    if(distance[to.val] > maxCost){
                        distance[to.val] = maxCost;
                        pq.add(new Node(to.val,distance[to.val]));
                    }
                }
            }
            
            for(int i=1;i<=n;i++){  //산봉우리이면서 최소값을 찾는 부분
                if(isSummit[i] && minCost > distance[i]){
                     minCost = distance[i];
                     minSummit = i;
                }else if(isSummit[i] && minCost == distance[i]){
                     minSummit = Math.min(i,minSummit);
                }
            }
        }
        
        answer[0]=minSummit;
        answer[1]=minCost;
        
        return answer;
    }
}

class Node implements Comparable<Node>{
    int val;
    int cost;
    
    public Node(int val, int cost){
        this.val= val;
        this.cost =cost;
    }
    
    public int compareTo(Node o){
        if(this.cost == o.cost){
            return this.val - o.val;
        }
        return this.cost- o.cost;
    }
    public String toString(){
        return "no."+val+" cost : "+cost;
    }
    
}
