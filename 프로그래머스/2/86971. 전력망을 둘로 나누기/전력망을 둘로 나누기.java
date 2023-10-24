/*
    n = 100
    모든 종류의 경우를 다 하는 경우
    끊을 전선 선택 * 전선연결 = 100*100 = 100,000 => 가능

*/

import java.util.*;

class Solution {
    int[] parents;
    public int solution(int n, int[][] wires) {
        int min = Integer.MAX_VALUE;
        
        for(int disconnect = 0; disconnect <wires.length; disconnect++){
            parents = new int[n+1];
            for(int i=1;i<parents.length;i++){
                parents[i] = i;
            }
            for(int connect = 0; connect <wires.length; connect++){
                if(disconnect == connect){
                    continue;
                }
                
                union(wires[connect][0],wires[connect][1]);
            }
            
            Map<Integer,Integer> wireMap = new HashMap<>();
            
            for(int i=1;i<parents.length;i++){
               int parent = getParent(parents[i]);
                if(wireMap.get(parent) == null){
                     wireMap.put(parent,0);
                }else{
                     wireMap.put(parent,wireMap.get(parent)+1);
                }
                // wireMap.put(parent,wireMap.getOrDefault(wireMap.get(parent),0)+1);
            }
            
            int[] keyArr = wireMap.keySet().stream().mapToInt(x->Integer.valueOf(x)).toArray();
            
            min = Math.min(min,Math.abs(wireMap.get(keyArr[0]) - wireMap.get(keyArr[1])));
        }
        
        return min;
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
        
        if(pa <= pb){
            parents[pb] = pa;
        }else{
            parents[pa] = pb;
        }
    }
}