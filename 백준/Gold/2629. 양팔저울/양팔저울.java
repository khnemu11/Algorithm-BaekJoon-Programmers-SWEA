import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static boolean possible[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int weightNum = Integer.valueOf(br.readLine());
		int weightArr[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		int max = Arrays.stream(weightArr).sum();
		possible = new boolean[weightNum + 1][max + 1];

		findSum(0, weightArr, 0);
		int targetNum = Integer.valueOf(br.readLine());
		int targetArr[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		for (int i = 0; i < targetArr.length; i++) {
			if (targetArr[i] > max) {
				bw.write("N" + " ");
			} else if (possible[weightNum][targetArr[i]]) {
				bw.write("Y" + " ");
			} else {
				bw.write("N" + " ");
			}
		}
		bw.newLine();
		bw.flush();
	}

	static public void findSum(int curr, int weightArr[], int sum) {
		if (possible[curr][sum]) {
			return;
		}
		possible[curr][sum] = true;

		if (curr == weightArr.length) {
			return;
		}
		findSum(curr + 1, weightArr, sum);
		findSum(curr + 1, weightArr, sum + weightArr[curr]);
		findSum(curr + 1, weightArr, Math.abs(sum - weightArr[curr]));
	}

}
