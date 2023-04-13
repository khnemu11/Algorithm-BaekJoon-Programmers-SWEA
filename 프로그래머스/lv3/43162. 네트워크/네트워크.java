/*
    네트워크의 개수 : 유니온 파인드
    
*/

import java.util.*;

class Solution {
    int parents[];
    public int solution(int n, int[][] computers) {
        parents = new int[n];
        
        for(int i=0;i<n;i++){
            parents[i]=i;    
        }
        
        for(int i=0;i<computers.length;i++){
            for(int j=i+1;j<computers.length;j++){
                if(computers[i][j]==1){
                    union(i,j);
                }
            }           
        }
        
        HashSet<Integer> networks = new HashSet<>();
        
        for(int i=0;i<parents.length;i++){
            networks.add(getParent(i));
        }
        System.out.println(networks);
        return networks.size();
    }
    
    public int getParent(int child){
        if(parents[child] == child){
            return parents[child];
        }
        parents[child] = getParent(parents[child]);
        
        return parents[child];
    }
    
    public void union(int a, int b){
        int pa = getParent(a);
        int pb = getParent(b);
        
        if(pa<pb){
            parents[pb] = pa;
        }else{
            parents[pa] = pb;
        }
    }   
}