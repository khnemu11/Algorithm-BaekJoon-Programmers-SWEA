import java.util.*;

class Solution {  
    public boolean solution(String[] phone_book) {
        Set<String> phoneSet = new HashSet<>();
        Arrays.sort(phone_book,(o1,o2) -> o2.length() - o1.length());
        for(String phone : phone_book){
            if(phoneSet.contains(phone)){
                return false;
            }
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<phone.length();i++){
                sb.append(phone.charAt(i));
                phoneSet.add(sb.toString());
            }
        }
        
        return true;
    }

}