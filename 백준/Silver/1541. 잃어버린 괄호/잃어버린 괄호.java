import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String question = br.readLine();

		int sum = 0;
		int curr = 0;
		int op = 1;

		for (int i = 0; i < question.length(); i++) {
			if (question.charAt(i) == '-') {
				sum += curr * op;
				curr = 0;
				op = -1;
			} else if (question.charAt(i) == '+') {
				sum += curr * op;
				curr = 0;
			} else {
				curr = curr * 10 + Character.getNumericValue(question.charAt(i));
			}
		}
		sum += curr * op;

		bw.write(String.valueOf(sum));
		bw.flush();
		bw.close();
		br.close();
	}
}
