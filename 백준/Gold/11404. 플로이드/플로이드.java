import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int V = Integer.valueOf(br.readLine());
		int W = Integer.valueOf(br.readLine());

		int route[][] = new int[V + 1][V + 1];

		for (int i = 1; i <= V; i++) {
			Arrays.fill(route[i], Integer.MAX_VALUE);
			route[i][i] = 0;
		}
		// 루트 생성

		for (int k = 0; k < W; k++) {
			int[] row = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			route[row[0]][row[1]] = Math.min(route[row[0]][row[1]], row[2]);
		}

		// 플로이드 워셜로 실행

		// 가운데 지점 루프
		// 시작지점 루프
		// 끝지점 루프
		// 현재 값과 시작-가운데-끝 노선 중 최소값 설정
		for (int mid = 1; mid <= V; mid++) {
			for (int start = 1; start <= V; start++) {
				for (int end = 1; end <= V; end++) {
					if (route[start][mid] == Integer.MAX_VALUE || route[mid][end] == Integer.MAX_VALUE) {
						continue;
					}
					route[start][end] = Math.min(route[start][end], route[start][mid] + route[mid][end]);
				}
			}
		}

		for (int i = 1; i <= V; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 1; j <= V; j++) {
				if(route[i][j]==Integer.MAX_VALUE) {
					sb.append("0").append(" ");
				}
				else {
					sb.append(route[i][j]).append(" ");
				}
			}
			bw.write(sb.deleteCharAt(sb.length()-1).toString());
			bw.newLine();
		}
		
		
		bw.flush();
	}
}
