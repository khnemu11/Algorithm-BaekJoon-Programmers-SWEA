import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
	풀이 알고리즘
	알파벳 순서로 출력해야 하므로 입력값을 알파벳 오름차순 정렬
	선택한 알파벳의 개수 - 선택한 모음의 개수 < 2 -> 선택한 자음의 개수가 2개 보다 작음
	dfs로 완전 탐색 실행
*/

public class Main {
	static int arr[][];
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static String vowels = "aeiou";
	static String alpha[];
	static int vowelCnt;
	static int L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.valueOf(st.nextToken()); // 문자의 길이
		int C = Integer.valueOf(st.nextToken()); // 알파벳 개수

		st = new StringTokenizer(br.readLine());
		alpha = new String[C];

		for (int i = 0; i < C; i++) {
			alpha[i] = st.nextToken();
		}
		Arrays.sort(alpha);

		pick(0, new StringBuilder(), 0);
		bw.flush();
	}

	public static void pick(int idx, StringBuilder sb, int cnt) throws IOException {
		if (cnt == L) {
			if (L - vowelCnt < 2 || vowelCnt < 1) { // 자음의 개수가 2개 미만이고 모음의 개수가 1개 미만이면 패스
				return;
			} else {
				bw.write(sb.toString() + "\n");
				return;
			}
		} else {
			for (int i = idx; i < alpha.length; i++) {
				sb.append(alpha[i]);

				if (vowels.contains(alpha[i])) { // 모음이 포함되면 모음 개수 증가
					vowelCnt++;
					pick(i + 1, sb, cnt + 1);
					vowelCnt--;
				} else {
					pick(i + 1, sb, cnt + 1);
				}
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}
}

class Coordinate {
	int row;
	int col;

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}
}