import java.util.ArrayList;
import java.util.List;


class Solution {
    public int solution(String s) {
      int answer = s.length();
		
		for(int i=1;i<=s.length()/2;i++) {
			StringBuilder sb = new StringBuilder();
			int part = i;
			int duplicateCount = 1;
			for(int j=0;j+part*2<=s.length();j+=part) {
			//	System.out.println("start");
			//	System.out.println(s.substring(j,j+part) + " " + s.substring(j+part,j+part*2));
				if(s.substring(j,j+part).equals(s.substring(j+part,j+part*2))) {
				//	System.out.println(s.substring(j,j+part) +" is same");
					duplicateCount++;
				}
				else {
				//	System.out.println(s.substring(j,j+part) +" is different");
					if(duplicateCount>1) {
						sb.append(duplicateCount);
						sb.append(s.substring(j, j+part));
						duplicateCount = 1;
					}
					else {
					//	System.out.println("append "+s.substring(j,j+part));
						sb.append(s.substring(j, j+part));
					}
				}
			}
			//System.out.println("loop out");
			if(duplicateCount!=1) {
				sb.append(duplicateCount);
				sb.append(s.substring(s.length()-part-s.length()%part, s.length()));
			}
			else {
				sb.append(s.substring(s.length()-part-s.length()%part, s.length()));
			}
			//System.out.println("result : " + sb);
			//System.out.println("==============");
			if(sb.length()<answer) {
				answer= sb.length();
			}
		}
		
	//	System.out.println("answer : "+answer);
        return answer;
    }
}