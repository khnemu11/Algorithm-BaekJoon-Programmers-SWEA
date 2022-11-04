import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		char word[] = br.readLine().toCharArray();
		boolean isPallindrom = true;

		for (int i = 0; i <= word.length / 2 - 1; i++) {
			if (word[i] != word[word.length - i - 1]) {
				isPallindrom = false;
				break;
			}
		}

		if (isPallindrom) {
			bw.write("1");
		} else {
			bw.write("0");
		}

		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}

}
