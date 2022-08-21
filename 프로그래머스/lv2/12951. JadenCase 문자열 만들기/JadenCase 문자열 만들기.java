class Solution {
    public String solution(String s) {
       String[] token = s.toLowerCase().split("\\b");
        StringBuilder br = new StringBuilder();
        for(int i=0;i<token.length;i++) {
        	if(token[i].isBlank()) {
        		br.append(token[i]);
        	}
        	else if(token[i].charAt(0)>47 && token[i].charAt(0)<57) {
        		br.append(token[i]);
        	}
        	else {
        		br.append(String.valueOf(token[i].charAt(0)).toUpperCase());
        		br.append(token[i].substring(1,token[i].length()));
        	}
        }
        
        return br.toString().substring(0,br.length());
    }
}