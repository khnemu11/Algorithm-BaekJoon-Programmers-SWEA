/*
    n = 200
    플로이드 워셜을 이용해서 모든 점과 점사이의 최단거리 계산
    O(n) = n*n*n = 200*200*200 < 1억
*/

import java.util.*;

class Solution {
    int MAX_VALUE = 200 * 100_000;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] graph = new int[n+1][n+1];
        
        //그래프 초기화
        for(int i=0;i<=n;i++){
            Arrays.fill(graph[i],MAX_VALUE);
            graph[i][i] = 0;
        }
        
        for(int i=0;i<fares.length;i++){
            int start = fares[i][0];
            int end = fares[i][1];
            int cost = fares[i][2];
            
            graph[start][end] = cost;
            graph[end][start] = cost;
        }
        //플로이드 워셜로 최단거리 탐색
        for(int mid=1;mid<=n;mid++){
          for(int start=1;start<=n;start++){
            for(int end=1;end<=n;end++){
                    if(graph[start][end] > graph[start][mid] + graph[mid][end]){
                        graph[start][end] = graph[start][mid] + graph[mid][end];
                    }
                }    
            }  
        }
        
        int min = MAX_VALUE;
        
        //시작부터 중간지 까지 같이 갈 경로를 선택
        for(int mid=1;mid<=n;mid++){  
            min = Math.min(min,graph[s][mid] + graph[mid][a]+graph[mid][b]);
        }  
        
        
        return min;
    }
}