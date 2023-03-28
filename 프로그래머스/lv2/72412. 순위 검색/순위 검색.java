import java.util.*;

/*
    풀이 알고리즘
    
    쿼리를 그대로 info에 1대1 대입을 하면 100,000 * 50,000 > 100,000,000 이므로 시간초과
    쿼리문을 대입하면 바로 해당되는 참가자의 점수의 목록이 나와야 시간내에 가능
    
    info가 될 수 있는 모든 -를 포함한 조합의 개수 = 2^4 * 50,000
    
    해당 값 이상의 개수 ->파라메틱  서치->log(n)
    모두 조건에 맞는 info를 구한 시간 복잡도 = 100,000 * log(50,000) < 100,000,000 이므로 가능
*/

class Solution {
    static HashMap<String,ArrayList<Integer>> map = new HashMap<>();
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
    
        for (int i=0;i<info.length;i++) {
            String splited [] = info[i].split(" ");
            makeAllKey(splited,"",0);  
        }
        
        for(String key : map.keySet()){ //파라메틱 서치를 위한 리스트 정렬
            Collections.sort(map.get(key));
        }
        
         for(int i=0;i<query.length;i++){   
            String queryRemoveAnd = query[i].replace(" and","");    //쿼리를 파싱하여 키로 변환
            String splited [] = queryRemoveAnd.split(" ");
            String condition[] ={splited[0],splited[1],splited[2],splited[3],splited[4]};
            String key =splited[0]+splited[1]+splited[2]+splited[3];
             
            ArrayList<Integer> list = map.get(key);
           
            if(list == null){   //비어있다면 매칭되는 사람이 없음
                continue;
            } 
             
            int l = 0;
            int r = list.size()-1;
            int mid = (l+r)/2;
            int cnt = 0;
            
             while (l<=r) { //파라메틱 서치로 범위 계산
                mid = (l+r)/2;
                if (list.get(mid) < Integer.valueOf(splited[4])) {
                    l = mid + 1;
                } else {
                    cnt += r - mid + 1;
                    r = mid - 1;
                }
            }
             
             answer[i]+=cnt;
        }
        
        return answer;
    }
    
    public void makeAllKey(String splited[], String key,int idx){   //-가 들어간 모든 조합 키를 구하는 메소드
        if(idx == splited.length-1){
            if(!map.containsKey(key)){  //키가 처음 생긴 것이라면 리스트 생성
                map.put(key,new ArrayList<Integer>());
            }
            ArrayList<Integer> list = map.get(key);
            list.add(Integer.valueOf(splited[idx]));
            map.put(key,list);
        }else{
            makeAllKey(splited,key+"-",idx+1);
            makeAllKey(splited,key+splited[idx],idx+1);
        }
    }
}