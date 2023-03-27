import java.util.*;

class Solution {
    static HashMap<String, Integer> couresMap = new HashMap<>();
    static boolean find[] = new boolean[11];
    static int maxLength = 0;
    public String[] solution(String[] orders, int[] course) {
        for(int i=0;i<course.length;i++){
            find[course[i]]=true;
        }
        maxLength = course[course.length-1];
        
        for(int i=0;i<orders.length;i++){
            char [] alphas = orders[i].toCharArray();
            Arrays.sort(alphas);
            select(alphas,new StringBuilder(),0);
        }
        
        PriorityQueue<Node>pq [] = new PriorityQueue[11];
        
        for(int i=0;i<11;i++){
            pq[i] = new PriorityQueue<>();
        }
        
        for(String key : couresMap.keySet()){
            pq[key.length()].add(new Node(couresMap.get(key),key));
        }
        
        PriorityQueue<String> result = new PriorityQueue<>();
        
        for(int length : course){
            if(pq[length].isEmpty() ){
                continue;
            }
            int max = pq[length].peek().cnt;
            if(max ==1){
                continue;
            }
            while(!pq[length].isEmpty()){
                Node curr = pq[length].poll();
                if(curr.cnt<max){
                    break;
                }
                result.add(curr.val);
            }
        }
        String[] answer = new String[result.size()];
        
        int idx=0;
        
        while(!result.isEmpty()){
            answer[idx++] = result.poll();
        }
        
//         System.out.println(couresMap);
//         System.out.println(result);
        
        return answer;
    }
    
    public void select (char[] orders,StringBuilder sb, int idx){
        if(sb.length()>maxLength){
            return ;
        }else if(find[sb.length()]){
            String key = sb.toString();
            couresMap.put(key, couresMap.getOrDefault(key,0)+1);
        }
        
        for(int i=idx;i<orders.length;i++){
            sb.append(orders[i]);
            select(orders,sb,i+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
class Node implements Comparable<Node>{
    int cnt;
    String val;
    
    public Node(int cnt, String val){
        this.cnt = cnt;
        this.val = val;
    }
    
    public int compareTo(Node o){
        return o.cnt - this.cnt;
    }
}