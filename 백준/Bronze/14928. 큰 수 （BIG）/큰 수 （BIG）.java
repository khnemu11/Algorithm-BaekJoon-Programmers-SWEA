import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String num = br.readLine();

		int reminder = 0;

		for (int i = 0; i < num.length(); i++) {
			reminder = (reminder * 10 + Character.getNumericValue(num.charAt(i))) % 20000303;
		}

		bw.write(String.valueOf(reminder));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
