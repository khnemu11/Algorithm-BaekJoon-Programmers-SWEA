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

		int input[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

		int height = input[0];
		int width = input[1];
		int size = input[2];

		int blackNum[][] = new int[height + 1][width + 1];
		int whiteNum[][] = new int[height + 1][width + 1];
		for (int i = 1; i < blackNum.length; i++) {
			String inputStr[] = br.readLine().split("");
			for (int j = 1; j < blackNum[0].length; j++) {
				if (i % 2 == 1) {
					if (j % 2 == 0) {
						if (inputStr[j - 1].equals("B")) {
							blackNum[i][j] = blackNum[i][j - 1] + blackNum[i - 1][j] + 1 - blackNum[i - 1][j - 1];
							whiteNum[i][j] = whiteNum[i][j - 1] + whiteNum[i - 1][j] - whiteNum[i - 1][j - 1];
						} else {
							blackNum[i][j] = blackNum[i][j - 1] + blackNum[i - 1][j] - blackNum[i - 1][j - 1];
							whiteNum[i][j] = whiteNum[i][j - 1] + whiteNum[i - 1][j] + 1 - whiteNum[i - 1][j - 1];
						}
					} else {
						if (inputStr[j - 1].equals("B")) {
							blackNum[i][j] = blackNum[i][j - 1] + blackNum[i - 1][j] - blackNum[i - 1][j - 1];
							whiteNum[i][j] = whiteNum[i][j - 1] + whiteNum[i - 1][j] + 1 - whiteNum[i - 1][j - 1];
						} else {
							blackNum[i][j] = blackNum[i][j - 1] + blackNum[i - 1][j] + 1 - blackNum[i - 1][j - 1];
							whiteNum[i][j] = whiteNum[i][j - 1] + whiteNum[i - 1][j] - whiteNum[i - 1][j - 1];
						}
					}
				} else {
					if (j % 2 == 0) {
						if (inputStr[j - 1].equals("B")) {
							blackNum[i][j] = blackNum[i][j - 1] + blackNum[i - 1][j] - blackNum[i - 1][j - 1];
							whiteNum[i][j] = whiteNum[i][j - 1] + whiteNum[i - 1][j] - whiteNum[i - 1][j - 1] + 1;
						} else {
							blackNum[i][j] = blackNum[i][j - 1] + blackNum[i - 1][j] + 1 - blackNum[i - 1][j - 1];
							whiteNum[i][j] = whiteNum[i][j - 1] + whiteNum[i - 1][j] - whiteNum[i - 1][j - 1];
						}
					} else {
						if (inputStr[j - 1].equals("B")) {
							blackNum[i][j] = blackNum[i][j - 1] + blackNum[i - 1][j] + 1 - blackNum[i - 1][j - 1];
							whiteNum[i][j] = whiteNum[i][j - 1] + whiteNum[i - 1][j] - whiteNum[i - 1][j - 1];
						} else {
							blackNum[i][j] = blackNum[i][j - 1] + blackNum[i - 1][j] - blackNum[i - 1][j - 1];
							whiteNum[i][j] = whiteNum[i][j - 1] + whiteNum[i - 1][j] + 1 - whiteNum[i - 1][j - 1];
						}
					}
				}

			}
		}
		int min = Integer.MAX_VALUE;
		for (int i = 1; i + size - 1 < blackNum.length; i++) {
			for (int j = 1; j + size - 1 < blackNum[0].length; j++) {
				int blackSum = blackNum[i + size - 1][j + size - 1] - blackNum[i - 1][j + size - 1]
						- blackNum[i + size - 1][j - 1] + blackNum[i - 1][j - 1];
				int whiteSum = whiteNum[i + size - 1][j + size - 1] - whiteNum[i - 1][j + size - 1]
						- whiteNum[i + size - 1][j - 1] + whiteNum[i - 1][j - 1];
				min = Math.min(min, Math.min(blackSum, whiteSum));
			}
		}
		bw.write(String.valueOf(min));
		bw.newLine();
		bw.flush();
		br.close();
		bw.close();
	}
}
