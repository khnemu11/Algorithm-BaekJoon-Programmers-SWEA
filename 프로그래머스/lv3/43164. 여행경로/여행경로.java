import java.util.*;

class Solution {
    HashMap<String,Integer> cityToIdxMap = new HashMap<>();
    HashMap<Integer,String> idxToCityMap = new HashMap<>();
    ArrayList<Integer> graph[];
    String routeList[];
    int visited[][];
    
    public String[] solution(String[][] tickets) {
        Set<String> cities = new TreeSet<>();
        
        for(int i=0;i<tickets.length;i++){
            cities.add(tickets[i][0]);
            cities.add(tickets[i][1]);
        }
        // System.out.println(cities);
        int idx=0;
        
        for(String city : cities){
            cityToIdxMap.put(city,idx);
            idxToCityMap.put(idx,city);
            idx++;
        }
        // System.out.println(cityToIdxMap);
        
        routeList = new String[tickets.length+1];
        graph = new ArrayList[cities.size()];
        visited= new int[cities.size()][cities.size()];
        
        for(int i=0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0;i<tickets.length;i++){
            visited[cityToIdxMap.get(tickets[i][0])][cityToIdxMap.get(tickets[i][1])]++;
            graph[cityToIdxMap.get(tickets[i][0])].add(cityToIdxMap.get(tickets[i][1]));
        }
        for(int i=0;i<graph.length;i++){
            Collections.sort(graph[i]);
        }
        
        // System.out.println(Arrays.deepToString(graph));

        routeList[0]="ICN";
        selectCity(cityToIdxMap.get("ICN"),1,tickets.length);
       
        // System.out.println(Arrays.toString(routeList));
        
        String answer[] = new String[2];
        return routeList;
    }
    
    public boolean selectCity(int curr,int idx,int size){
        if(idx > size){
            return true;
        }
        for(int next : graph[curr]){
            if(visited[curr][next]==0){
                continue;
            }
            // System.out.println(idxToCityMap.get(curr)+"->"+idxToCityMap.get(next));
            
            visited[curr][next]--;
            routeList[idx] = idxToCityMap.get(next);
            
            if(selectCity(next,idx+1,size)) {
                return true;
            }
            
            visited[curr][next]++;
            routeList[idx] ="";
        }
        return false;
    }
}