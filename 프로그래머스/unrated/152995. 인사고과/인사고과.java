import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int[] target = new int[2];
        target[0] = scores[0][0];
        target[1] = scores[0][1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->(o2[0] != o1[0] ? o2[0]-o1[0] : o1[1]-o2[1]));
        // Arrays.sort(scores,(o1,o2)->(o1[0] == o2[0] ? o2[1]-o1[1] : o2[0]-o1[0]));
        // System.out.println(Arrays.deepToString(scores));
        for(int i=0;i<scores.length;i++){
            pq.add(scores[i]);
        }
        
        TreeMap<Integer,Integer>map = new TreeMap<>(Collections.reverseOrder());
        int max[] = pq.poll();
        map.put(max[0]+max[1],1);
        while(!pq.isEmpty()){
            int [] curr = pq.poll();
            // System.out.println(scores[i-1][0]+" , "+scores[i-1][1]+" vs "+scores[i][0]+" ,"+scores[i][1]);
            if(max[0] > curr[0] && max[1] > curr[1]){
                if(curr[0] == target[0] && curr[1] == target[1]){
                    return -1;
                }else{
                    continue;
                }
            }
            
            int sum = curr[0]+curr[1];
            map.put(sum,map.getOrDefault(sum,0)+1);
            
            if(max[1]<curr[1]){
                max[0] = curr[0];
                max[1] = curr[1];
            }
        }
        
        // System.out.println(map);

        int grade = 1;
        
        for(int key : map.keySet()){
            if(key ==  target[0]+ target[1]){
                break;
            }
            grade+=map.get(key);
        }
        
        return grade;
    }
}