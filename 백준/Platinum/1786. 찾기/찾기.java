import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/* 
 * 	풀이 알고리즘
 * 
 * 
 * 	문자열 중복 확인 알고리즘 : kmp
 *
 * */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String target = br.readLine();
		String pattern = br.readLine();

		int prefixLng[] = makeTable(pattern);
		int sameCnt = 0;
		Queue<Integer> startIdx = new LinkedList<>();
		int matched = 0;

		for (int start = 0; start < target.length(); start++) {

			while (matched > 0 && target.charAt(start) != pattern.charAt(matched)) {
				matched = prefixLng[matched - 1];
			}

			if (target.charAt(start) == pattern.charAt(matched)) {
				if (matched == pattern.length() - 1) {
					startIdx.add(start - pattern.length() + 2);
					matched = prefixLng[matched];
					sameCnt++;
				} else {
					matched++;
				}
			}
		}

		bw.write(sameCnt + "\n");
		while (!startIdx.isEmpty()) {
			bw.write(startIdx.poll() + "\n");
		}
		bw.newLine();
		bw.flush();
	}

	public static int[] makeTable(String pattern) {
		int table[] = new int[pattern.length()];
		int idx = 0;
		for (int i = 1; i < pattern.length(); i++) {
			while (idx > 0 && pattern.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx - 1];
			}
			if (pattern.charAt(i) == pattern.charAt(idx)) {
				idx++;
				table[i] = idx;
			}
		}

		return table;
	}
}