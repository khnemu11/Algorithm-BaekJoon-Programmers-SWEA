import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        Map<String, Integer> id_idx_map = new HashMap<>();
        boolean visited[][] = new boolean[id_list.length][id_list.length];
        int counts[] = new int[id_list.length];
        
        for(int i=0;i<id_list.length;i++){
            id_idx_map.put(id_list[i],i);
        }
        
        for(String element : report){
            String split[] = element.split(" ");
            
            if(visited[id_idx_map.get(split[0])][id_idx_map.get(split[1])]){
                continue;    
            }
            
            visited[id_idx_map.get(split[0])][id_idx_map.get(split[1])]=true;
            counts[id_idx_map.get(split[1])]++;
        }
        
        for(int to=0;to<counts.length;to++){
            if(counts[to]<k){
                continue;
            }
            
            for(int from=0;from < visited.length;from++){
                if(visited[from][to]){
                    answer[from]++;
                }
            }
        }
        
        return answer;
    }
}