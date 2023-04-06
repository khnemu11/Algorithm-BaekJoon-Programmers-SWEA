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
	static ArrayList<HashSet<Character>> list = new ArrayList<>();

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
			list.add(new HashSet<>());
		}
        
		for (int col = 0; col < L; col++) {
			int maxCnt = 1;
			HashMap<Character, Integer> map = new HashMap<>();
			list.get(col).add(candidates[0].charAt(col));
			for (int row = 0; row < N; row++) {
				map.put(candidates[row].charAt(col), map.getOrDefault(candidates[row].charAt(col), 0) + 1);
				if (maxCnt < map.get(candidates[row].charAt(col))) {
					list.get(col).clear();
					maxCnt = map.get(candidates[row].charAt(col));
					list.get(col).add(candidates[row].charAt(col));
				} else if (maxCnt == map.get(candidates[row].charAt(col))) {
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

	public static String select(StringBuilder sb, int idx) {
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
		} else {
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
