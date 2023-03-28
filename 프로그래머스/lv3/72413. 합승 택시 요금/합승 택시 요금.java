import java.util.*;
/*
    풀이 알고리즘
    중간까지 겹치고 각각 a와 b로 갈때의 최소값
    -> 플로이드 워셜을 이용해서 구하기
*/
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int graph [][] = new int[n+1][n+1];
        int INF = 300_000_000;
        for(int i=0;i<=n;i++){
            Arrays.fill(graph[i],INF);
            graph[i][i]=0;
        }
        
        for(int i=0;i<fares.length;i++){
            graph[fares[i][0]][fares[i][1]] = fares[i][2];
            graph[fares[i][1]][fares[i][0]] = fares[i][2];
        }
        
        for(int mid=1;mid<=n;mid++){
            for(int start=1;start<=n;start++){
                for(int end=1;end<=n;end++){
                    if(graph[start][mid] == INF || graph[mid][end]==INF){
                        continue;
                    }
                    if(graph[start][end] > graph[start][mid] + graph[mid][end]){
                        graph[start][end] = graph[start][mid] + graph[mid][end];
                    }
                }  
            }  
        }
        int min = Integer.MAX_VALUE;
        
        for(int mid =1; mid<=n;mid++){
            int totalCost = graph[s][mid];
            int costA =graph[mid][a];
            int costB = graph[mid][b];
            
            if(totalCost == INF || costA == INF || costB ==INF){
                continue;
            }
            totalCost +=costA+costB;
            min = Math.min(min,totalCost);
        }
        
        return min;
    }
}

class Node implements Comparable<Node>{
    int val;
    int cost;
    
    public Node(int val, int cost){
        this.val = val;
        this.cost = cost;
    }
    
    public int compareTo(Node o){
        return this.cost- o.cost;
    }
}
