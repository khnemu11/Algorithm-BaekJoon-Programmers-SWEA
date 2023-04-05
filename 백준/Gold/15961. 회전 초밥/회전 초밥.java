import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken()); // 벨트위의 접시 개수
		int d = Integer.valueOf(st.nextToken()); // 초밥의 종류
		int k = Integer.valueOf(st.nextToken()); // 연속해서 먹는 개수
		int c = Integer.valueOf(st.nextToken()); // 쿠폰번호

		HashMap<Integer, Integer> map = new HashMap<>();

		int sushies[] = new int[d + 1];
		int rails[] = new int[N + k];

		for (int i = 0; i < N; i++) {
			rails[i] = Integer.valueOf(br.readLine());
		}
		for (int i = 0; i + N < rails.length; i++) {
			rails[i + N] = rails[i];
		}

		int maxEatCnt = 0;
//		System.out.println(Arrays.toString(rails));
		for (int start = 0; start + k < rails.length; start++) {
//			System.out.println("#" + start);
			if (start == 0) {
				for (int i = 0; i < k; i++) { // 1번째~K번째 초밥 먹기
					map.put(rails[i], map.getOrDefault(rails[i], 0) + 1);
				}
			} else {
				// 이전 초밥 지우기
				map.put(rails[start - 1], map.getOrDefault(rails[start - 1], 0) - 1); // 이전
																						// 초밥
				if (map.get(rails[start - 1]) == 0) {
					map.remove(rails[start - 1]);
				}
				// 다음 초밥 먹기
				map.put(rails[start + k - 1], map.getOrDefault(rails[start + k - 1], 0) + 1); // 이전
				
			}
//			System.out.println(map);
			if (map.get(c) == null) {
				maxEatCnt = Math.max(map.size() + 1, maxEatCnt);
			} else {
				maxEatCnt = Math.max(map.size(), maxEatCnt);
			}
		}

		bw.write(maxEatCnt + "\n");
		bw.flush();
	}
}