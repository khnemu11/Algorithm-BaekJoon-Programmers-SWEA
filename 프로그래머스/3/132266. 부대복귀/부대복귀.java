import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        int INF = 500_001;
        //그래프 초기화
        List<List<Integer>> graph = new ArrayList<>();
        
        for(int i=0;i<=n;i++){
            graph.add(new ArrayList<>());
        }
        
        //그래프 생성
        for(int i=0;i<roads.length;i++){
            graph.get(roads[i][0]).add(roads[i][1]);
            graph.get(roads[i][1]).add(roads[i][0]);
        }
        
        //지역과 지역사이의 최단거리 배열 생성 및 초기화
        int[] distance = new int[n+1];
        Arrays.fill(distance,INF);
        distance[destination] = 0;
        
        //다익스트라 우선순위 큐 생성
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(destination,0));
        
        //지역간 최단거리를 다익스트라로 구하기
        while(!pq.isEmpty()){
            Node curr = pq.poll();
            
            if(curr.cost > distance[curr.seq]){
                continue;
            }
            
            for(int next : graph.get(curr.seq)){
                if(distance[next] > curr.cost + 1){
                    distance[next] = curr.cost + 1;
                    pq.add(new Node(next,distance[next]));
                }
            }
        }
        
        //sources의 최단거리 저장
        for(int i=0;i<answer.length;i++){
            answer[i] = distance[sources[i]] == INF ? -1 : distance[sources[i]];
        }
    
        return answer;
    }
}

class Node implements Comparable<Node>{
    int seq;
    int cost;
    
    public Node(int seq, int cost){
        this.seq = seq;
        this.cost = cost;
    }
    
    public int compareTo(Node o){
        return this.cost - o.cost;
    }
}