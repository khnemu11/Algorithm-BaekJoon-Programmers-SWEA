import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
 * 풀이 알고리즘
 * 
 * 1) 각 자리수 별 가장 많이 나온 캐릭터 연산
 * 2) 캐릭터를 이용해 조합
   3) 조합으로 나온 단어와 후보 단어를 비교할 때 2개 이상 틀리면 false, 아니면 true
 * */

public class Main {
	static String candidates[];
	static ArrayList<HashSet<Character>> list = new ArrayList<>();	//자리수 별로 가장 많이 나온 단어를 저장하는 리스트

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.valueOf(st.nextToken());
		int L = Integer.valueOf(st.nextToken());

		candidates = new String[N];
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			candidates[i] = br.readLine();
		}
		for (int i = 0; i < L; i++) {
			list.add(new HashSet<>());	//중복 처리를 위해 set사용
		}
        
		for (int col = 0; col < L; col++) {
			int maxCnt = 1;
			HashMap<Character, Integer> map = new HashMap<>();
			list.get(col).add(candidates[0].charAt(col)); //초기 값 설정을 위해 가장 앞의 있는 문자 선택
			
			for (int row = 0; row < N; row++) {
				map.put(candidates[row].charAt(col), map.getOrDefault(candidates[row].charAt(col), 0) + 1);
				
				//해당 문자의 개수가 이전 문자의 최대 개수보다 많을 경우 해당 문자가 가장 많이 나온 문자이다.
				//이전까지 저장했던 값을 모두 삭제하고 새로운 set에 저장
				if (maxCnt < map.get(candidates[row].charAt(col))) {
					list.get(col).clear();
					maxCnt = map.get(candidates[row].charAt(col));
					list.get(col).add(candidates[row].charAt(col));
				} 
				//이전 문자의 개수 최대값과 현재 문자의 최대값이 같을 경우 해당 문자를 추가한다.
				else if (maxCnt == map.get(candidates[row].charAt(col))) {
					list.get(col).add(candidates[row].charAt(col));
				}
			}
		}
		String result = select(new StringBuilder(), 0);
		
		if (!result.equals("")) {
			bw.write(result + "\n");
		} else {
			bw.write("CALL FRIEND\n");
		}

		bw.flush();
	}
	//단어를 자리수 별로 조합하여 생성 후 규칙에 맞는지 확인하는 메소드, 값이 존재하지 않으면 빈 문자열 리턴
	public static String select(StringBuilder sb, int idx) {
		//모든 단어를 선택하였으면 규칙에 맞는지(자리수와 다른 단어의 개수가 1개 이하인지) 확인
		if (idx >= list.size()) {
			String str = sb.toString();

			for (int i = 0; i < candidates.length; i++) {
				int differCnt = 0;

				for (int j = 0; j < candidates[i].length(); j++) {
					if (str.charAt(j) != candidates[i].charAt(j)) {
						differCnt++;
					}
				}
				if (differCnt > 1) {
					return "";
				}
			}

			return str;
		} 
		//단어 선택
		else {
			for (Character chr : list.get(idx)) {
				sb.append(chr);
				String next = select(sb, idx + 1);
				if (!next.equals("")) {
					return next;
				}

				sb.deleteCharAt(sb.length() - 1);
			}
			return "";
		}
	}

}
