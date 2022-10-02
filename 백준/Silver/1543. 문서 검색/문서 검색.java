import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String target = br.readLine();
		String search = br.readLine();
		int count = 0;
		int idx = 0;
		while (idx + search.length() - 1 < target.length()) {
			boolean same = true;
			for (int j = 0; j < search.length(); j++) {
				if (target.charAt(idx + j) != search.charAt(j)) {
					same = false;
					break;
				}
			}
			if (same == true) {
				count++;
				idx = idx + search.length();
			} else {
				idx++;
			}
		}
		bw.write(String.valueOf(count));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
