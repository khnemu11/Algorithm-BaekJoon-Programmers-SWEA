import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*	풀이 알고리즘
 * 	서류가 1등이면 인터뷰는 보지도 않아도 된다 -> 서류를 기준으로 오름차순 정렬
 * 	서류가 2등이면 1등의 인터뷰 점수보다 높으면 된다.
 *  서류가 3등이면 1,2등의 인터뷰 점수보다 높으면 된다.
 *  ...
 *  서류가 n등이면 1~n-1등의 인터뷰 점수보다 모두 높으면 된다. ->1~n-1등의 인터뷰 점수의 최대값보다 높으면 된다.
 * */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.valueOf(br.readLine());

		for (int test_case = 0; test_case < T; test_case++) {
			int N = Integer.valueOf(br.readLine());
			int interviews[] = new int[N + 1]; // 인덱스 = 서류 점수 등수

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				interviews[Integer.valueOf(st.nextToken())] = Integer.valueOf(st.nextToken());
			}

			int max = Integer.MAX_VALUE;
			int cnt = 0;
			for (int i = 1; i < interviews.length; i++) {
				if (max > interviews[i]) {
					cnt++;
					max = Math.min(max, interviews[i]);
				}
			}
			bw.write(cnt + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}
}