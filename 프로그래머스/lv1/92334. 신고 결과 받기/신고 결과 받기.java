import java.util.*;

/*
    풀이 과정
    이미 신고를 한 사람이 똑같은 사람을 또 신고한다면 반영되지 말아야 한다 -> 방문처리 필요 -> 그래프 이용
    그래프를 이용해야 하므로 String 타입인 아이디를 인덱스화 시킬 필요가 있음 -> 맵을 이용해 아이디 인덱스화
    신고한 사람(from) -> 신고 당한사람(to)을 방문처리에 맞추어 개수를 센다
    신고 처리 메일을 보내기 위해 신고 당한사람이 정지가 된다면 신고한 사람의 개수가 오를 필요가 있음 
    신고 당한사람(to) -> 신고한 사람(from)을 이용해 개수 파악
*/

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        Map<String, Integer> id_idx_map = new HashMap<>(); //id를 인덱스화 시키기위한 맵
        boolean visited[][] = new boolean[id_list.length][id_list.length]; //아이디별 방문처리
        int counts[] = new int[id_list.length];
        
        for(int i=0;i<id_list.length;i++){
            id_idx_map.put(id_list[i],i); //id를 인덱스화
        }
        
        for(String element : report){
            String split[] = element.split(" "); //신고한 사람, 신고 당한사람으로 파싱
            
            if(visited[id_idx_map.get(split[0])][id_idx_map.get(split[1])]){ //이미 신고한 사람이면 스킵
                continue;    
            }
            
            visited[id_idx_map.get(split[0])][id_idx_map.get(split[1])]=true; //방문처리
            counts[id_idx_map.get(split[1])]++; //신고 당한 개수 더하기
        }
        
        for(int to=0;to<counts.length;to++){ //방문처리 배열을 이용해 역으로 신고당한 사람에서 신고한 사람의 개수를 파악
            if(counts[to]<k){ //신고 횟수 K 보다 신고당한게 작으면 패스
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
