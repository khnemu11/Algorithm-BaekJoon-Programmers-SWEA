import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static int whiteCount = 0;
	static int blueCount = 0;

	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int size = Integer.valueOf(br.readLine());

		arr = new int[size][size];

		for (int i = 0; i < size; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				arr[i][j] = Integer.valueOf(st.nextToken());
			}
		}

		divide(0, size, 0, size);

		bw.write(String.valueOf(whiteCount));
		bw.newLine();
		bw.write(String.valueOf(blueCount));
		bw.newLine();
		br.close();
		bw.close();
	}

	public static void divide(int widthStart, int widthEnd, int heightStart, int heightEnd) {
		boolean next = false;
		boolean white = false;
		boolean blue = false;

		if (widthStart == widthEnd || heightStart == heightEnd) {
			return;
		}

		for (int i = heightStart; i < heightEnd; i++) {
			for (int j = widthStart; j < widthEnd; j++) {
				if (arr[i][j] == 0 && !blue) {
					white = true;
				} else if (arr[i][j] == 1 && !white) {
					blue = true;
				} else {
					next = true;
					break;
				}
			}
			if (next) {
				break;
			}
		}

		if (next) {
			divide(widthStart, (widthStart + widthEnd) / 2, heightStart, (heightStart + heightEnd) / 2);
			divide((widthStart + widthEnd) / 2 , widthEnd, heightStart, (heightStart + heightEnd) / 2);
			divide(widthStart, (widthStart + widthEnd) / 2, (heightStart + heightEnd) / 2, heightEnd);
			divide((widthStart + widthEnd) / 2 , widthEnd, (heightStart + heightEnd) / 2, heightEnd);
		} else {
			if (blue) {
				blueCount++;
			} else {
				whiteCount++;
			}
			
		}

	}
}