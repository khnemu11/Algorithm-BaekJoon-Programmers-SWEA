import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
	
	하나의 집합을 해쉬 셋에 저장해 해당 키가 있는 값만 개수 출력
 */

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.valueOf(st.nextToken());
			int M = Integer.valueOf(st.nextToken());

			HashSet<String> set = new HashSet<>();

			String keys[] = br.readLine().split(" ");

			for (String key : keys) {
				set.add(key);
			}

			int cnt = 0;
			String values[] = br.readLine().split(" ");
			for (String value : values) {
				if (set.contains(value)) {
					cnt++;
				}
			}

			bw.write("#" + testcase + " " + cnt + "\n");
		}

		bw.flush();
	}
}