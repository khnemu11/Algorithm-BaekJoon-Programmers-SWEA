import java.util.*;
/*
    목적지 간의 모든 경로를 순회하는 경우의 수를 구하는 문제
    이때 노드의 대표값이 String이므로 원활한 그래프 형성을 위해 int로 바꿀 필요가 있음
    -> hashmap을 이용해 string을 int로 변경
    
    중복되는 경로를 생각해서 방문 배열을 int형으로 설정
    
    알파벳 순서로 방문해야 하므로 각 자식 노드를 정렬
    
    dfs로 ICN부터 순회하여 모든 경로를 탐색했으면 해당 경로를 출력
*/
class Solution {
    HashMap<String,Integer> cityToIdxMap = new HashMap<>();
    HashMap<Integer,String> idxToCityMap = new HashMap<>();
    ArrayList<Integer> graph[];
    String routeList[];
    int visited[][];
    
    public String[] solution(String[][] tickets) {
        //도시의 개수를 위한 set
        Set<String> cities = new TreeSet<>();
        
        for(int i=0;i<tickets.length;i++){
            cities.add(tickets[i][0]);
            cities.add(tickets[i][1]);
        }
        
        //도시별 인덱스
        int idx=0;
        
        for(String city : cities){
            cityToIdxMap.put(city,idx);
            idxToCityMap.put(idx,city);
            idx++;
        }
        
        //경로의 개수+1개 만큼 경로 리스트 생성->방문하는 도시는 시작 + 도착도시이기 때문
        routeList = new String[tickets.length+1];
        //도시의 개수만큼 그래프 혀성
        graph = new ArrayList[cities.size()];
        visited= new int[cities.size()][cities.size()];
        
        for(int i=0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }
        
        //시작->도시간의 그래프 및 경로 개수 저장
        for(int i=0;i<tickets.length;i++){
            visited[cityToIdxMap.get(tickets[i][0])][cityToIdxMap.get(tickets[i][1])]++;
            graph[cityToIdxMap.get(tickets[i][0])].add(cityToIdxMap.get(tickets[i][1]));
        }
        
        //자식 경로 정렬
        for(int i=0;i<graph.length;i++){
            Collections.sort(graph[i]);
        }

        //경로의 시작은 항상 ICN
        
        routeList[0]="ICN";
  
        //ICN부터 경로 탐색
        selectCity(cityToIdxMap.get("ICN"),1,tickets.length);
       
        return routeList;
    }
    
    public boolean selectCity(int curr,int idx,int size){
        //모든 경로를 탐색한 경우
        if(idx > size){
            return true;
        }
        //현재 도시와 연결되어 있는 도시를 확인
        for(int next : graph[curr]){
            //이미 다 사용한 항공권이면 다음 도시로
            if(visited[curr][next]==0){
                continue;
            }
            
            //항공권 1개 소비
            visited[curr][next]--;
            //다음 도시를 루트에 추가
            routeList[idx] = idxToCityMap.get(next);
            
            //만약 모든 도시를 탐색했다면 더이상 탐색 불필요
            if(selectCity(next,idx+1,size)) {
                return true;
            }
            
            //롤백
            visited[curr][next]++;
            routeList[idx] ="";
        }
        //현재 도시로 경로를 생성하지 못하는 경우
        return false;
    }
}
