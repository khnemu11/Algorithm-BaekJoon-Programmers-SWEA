import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		boolean isWhite = true;
		int count = 0;

		for (int i = 0; i < 8; i++) {
			String row = br.readLine();
			for (int j = 0; j < 8; j++) {
				if (isWhite && row.charAt(j) == 'F') {
					count++;
				}
				isWhite = !isWhite;
			}
			isWhite = !isWhite;
		}

		bw.write(String.valueOf(count));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}

}
