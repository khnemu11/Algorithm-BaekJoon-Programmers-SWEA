import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String word = br.readLine();
		String result = "";

		for (int left = 1; left < word.length() - 1; left++) {
			for (int right = left + 1; right < word.length(); right++) {
				String leftWord = new StringBuilder(word.substring(0, left)).reverse().toString();
				String midWord = new StringBuilder(word.substring(left,right)).reverse().toString();
				String rightWord = new StringBuilder(word.substring(right, word.length())).reverse().toString();
				StringBuilder sb = new StringBuilder();

				sb.append(leftWord);
				sb.append(midWord);
				sb.append(rightWord);
				
				String candidate = sb.toString();
				
				if(result.isEmpty()) {
					result = candidate;
				}
				if (candidate.compareTo(result) < 0) {
					result = candidate;
				}
			}
		}

		bw.write(result);
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
