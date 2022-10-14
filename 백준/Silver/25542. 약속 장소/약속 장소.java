import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.valueOf(st.nextToken());
		int L = Integer.valueOf(st.nextToken());
		List<HashMap<Character, Integer>> map = new ArrayList<>();
		String list[] = new String[N];

		for (int i = 0; i < L; i++) {
			map.add(new HashMap<Character, Integer>());
		}

		for (int n = 0; n < N; n++) {
			String input = br.readLine();
			list[n] = input;
			for (int l = 0; l < L; l++) {
				map.get(l).put(input.charAt(l), map.get(l).getOrDefault(input.charAt(l), 0) + 1);
			}
		}

		boolean exist = true;

		for (int i = 0; i < L; i++) {
			Iterator<Character> it = map.get(i).keySet().iterator();
			char candidate = it.next();
			int max = map.get(i).get(candidate);

			while (it.hasNext()) {
				char key = it.next();
				if (map.get(i).get(key) > max) {
					candidate = key;
					max = map.get(i).get(key);
					exist = true;
				} else if (map.get(i).get(key) == max) {
					exist = false;
				}
			}
			if (exist) {
				sb.append(candidate);
			} else {
				sb = new StringBuilder("CALL FRIEND");
				break;
			}

		}
		String result = sb.toString();
		exist = true;
		for (int i = 0; i < N; i++) {
			int count = 0;
			for (int j = 0; j < L; j++) {
				if(result.charAt(j)!=list[i].charAt(j)) {
					count++;
				}
				if(count>1) {
					exist = false;
					break;
				}
			}
			if(!exist) {
				result ="CALL FRIEND";
				break;
			}
		}

		bw.write(result);
		bw.newLine();
		bw.flush();

		br.close();
		bw.close();
	}
}