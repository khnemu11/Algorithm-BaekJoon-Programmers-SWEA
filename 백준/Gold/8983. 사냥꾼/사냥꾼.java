import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int X = 0;
		int Y = 1;

		StringTokenizer st = new StringTokenizer(br.readLine());

		int M = Integer.parseInt(st.nextToken()); // 사대의 수
		int N = Integer.parseInt(st.nextToken()); // 동물의 수
		int L = Integer.parseInt(st.nextToken()); // 사정거리

		int shooters[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).sorted().toArray();

		int count = 0;
		for (int i = 0; i < N; i++) {
			int coordinate[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

			int l = 0;
			int r = M - 1;

			while (l <= r) {
				int mid = (l + r) / 2;
				if (Math.abs(shooters[mid] - coordinate[X]) + coordinate[Y] <= L) {
					count++;
					break;
				} else if (shooters[mid] < coordinate[X]) {
					l = mid + 1;
				} else {
					r = mid - 1;
				}
			}
		}
		bw.write(count + "\n");
		bw.close();
	}
}
