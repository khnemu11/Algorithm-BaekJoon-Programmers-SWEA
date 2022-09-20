import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int IOInum = Integer.valueOf(br.readLine());
		int length = Integer.valueOf(br.readLine());
		char word[] = br.readLine().toCharArray();

		int count = 0;
		int total = 0;
		for (int i = 1; i < length - 1; i++) {
			if (word[i - 1] == 'I' && word[i] == 'O' && word[i + 1] == 'I') {
				count++;
				i++;
				if(count==IOInum) {
					count--;
					total++;
				}
			}
			else {
				count=0;
			}
		}

		bw.write(String.valueOf(total));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
