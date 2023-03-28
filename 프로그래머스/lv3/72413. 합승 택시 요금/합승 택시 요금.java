import java.util.*;
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        
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
            // System.out.println("mid : "+mid +" cost : "+totalCost);
            min = Math.min(min,totalCost);
        }
        // printArr(graph);
        
        return min;
    }
    
    public void printArr(int arr[][]){
        for(int i=1;i<arr.length;i++){
            for(int j=1;j<arr[0].length;j++){
                System.out.print(arr[i][j]+" ");
            }  
            System.out.println();
        }
         System.out.println();
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