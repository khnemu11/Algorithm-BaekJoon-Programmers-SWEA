import java.util.*;

class Solution {
    public int[] solution(String msg) {
        List<Integer>result = new ArrayList<>();
        
        HashMap<String,Integer>map = new HashMap<>();
        
        char alpha = 'A';
        int val=1;
        for(;val<=26;val++){
            map.put(String.valueOf(alpha),val);
            alpha++;
        }
        
        // System.out.println(map);
        int idx=0;
        while(idx<msg.length()){
            String input = String.valueOf(msg.charAt(idx));
            while(idx<msg.length()){
                if(idx+1 >=msg.length()){
                    break;
                }
                input = input +  String.valueOf(msg.charAt(idx+1));
                
                if(map.get(input)==null){
                    break;
                }
                idx++;
            }
            idx++;
            if(map.get(input)==null){
                map.put(input,val++);   
            }
            // System.out.println(input);
        
            if(idx <msg.length()){
                // result.add(map.get(input.length() > 1 ? input.substring(0,input.length()-1) : input));
                // System.out.println(input.substring(0,input.length()-1));
                result.add(map.get(input.substring(0,input.length()-1)));
            }else{
                result.add(map.get(input));
            }
        }
        int answer[] = Arrays.stream(result.toArray(new Integer[result.size()])).mapToInt(x->Integer.valueOf(x)).toArray();
        return answer;
    }
}