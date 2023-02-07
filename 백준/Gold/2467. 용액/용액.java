import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
 * 풀이 과정
 * 
 * 시계방향(오른쪽->아래->왼쪽->위)으로 탐색
 * 이때 이미 탐색했거나 배열 이외의 좌표인경우 다음 방향으로 가도록 설정한다.
 * */

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());
		int solutions[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

		int l = 0;
		int r = N - 1;
		int small = solutions[l];
		int large = solutions[r];
		int min = Math.abs(small + large);

		while (l < r) {
			if (solutions[l] + solutions[r] == 0) {
				small = solutions[l];
				large = solutions[r];
				break;
			} else if (min > Math.abs(solutions[l] + solutions[r])) {
				min = Math.abs(solutions[l] + solutions[r]);
				small = solutions[l];
				large = solutions[r];
			}

			if (solutions[l] + solutions[r] > 0) {
				r--;
			} else {
				l++;
			}
		}
		bw.write(small + " " + large);
		bw.flush();
	}
}
