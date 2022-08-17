import java.util.ArrayList;
import java.util.List;


class Solution {
    public int solution(String s) {
      int answer = s.length();
		//최대한 나눌 수 있는건 절반 크기이므로 1~절반까지 수행
		for(int i=1;i<=s.length()/2;i++) {
			StringBuilder sb = new StringBuilder();
			int part = i;
			int duplicateCount = 1;
            //n 개 단위로 분할할 수 있을때 까지 분할
			for(int j=0;j+part*2<=s.length();j+=part) {
                //이번 문자열과 다음 문자열이 겹치면 겹침 변수 1 더함
				if(s.substring(j,j+part).equals(s.substring(j+part,j+part*2))) {
					duplicateCount++;
				}
                //이번 문자열과 다음 문자열이 안겹치면 이번 문자열을 문자열에 추가
				else {
                    //이전에 겹치던 문자열의 개수만큼 숫자를 추가함
					if(duplicateCount>1) {
						sb.append(duplicateCount);
						sb.append(s.substring(j, j+part));
						duplicateCount = 1;
					}
					else {
						sb.append(s.substring(j, j+part));
					}
				}
			}
            //비교했던 마지막 문자열과 나머지 문자열을 추가하는 조건문
			if(duplicateCount!=1) {
				sb.append(duplicateCount);
				sb.append(s.substring(s.length()-part-s.length()%part, s.length()));
			}
			else {
				sb.append(s.substring(s.length()-part-s.length()%part, s.length()));
			}
			if(sb.length()<answer) {
				answer= sb.length();
			}
		}
        return answer;
    }
}