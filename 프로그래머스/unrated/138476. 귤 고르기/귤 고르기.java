import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        HashMap<Integer,Integer> map = new HashMap<>();
        
        for(int tan : tangerine){
            map.put(tan,map.getOrDefault(tan,0)+1);
        }
        List<Integer> keyList = new ArrayList<>(map.keySet());
        Collections.sort(keyList,(o1,o2)->(map.get(o1).compareTo(map.get(o2)))*-1);
       
        int cnt = 0;
        int sum = 0;
        int idx =0;
        
        while(k > sum){
            cnt++;
            sum +=map.get(keyList.get(idx));
            idx++;
        }
        return cnt;
    }
}