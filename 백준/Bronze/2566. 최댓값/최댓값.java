import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	static int dp[][];
	static int maze[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int max = -1;
		int row = 0;
		int col = 0;
		for (int i = 0; i < 9; i++) {
			int input[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			for (int j = 0; j < 9; j++) {
				if (max < input[j]) {
					max = input[j];
					row = i + 1;
					col = j + 1;
				}
			}
		}

		bw.write(String.valueOf(max));
		bw.newLine();
		bw.write(row + " " + col);
		bw.newLine();
		bw.flush();
	}

}
