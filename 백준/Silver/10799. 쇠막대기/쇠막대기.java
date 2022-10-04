import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		char[] baskets = br.readLine().toCharArray();
		int currSticks = 0;
		int slicedSticks = 0;

		for (int i = 0; i < baskets.length; i++) {
			if (baskets[i] == '(') {
				currSticks++;
			} else {
				if (i > 0 && baskets[i - 1] == '(') {
					currSticks--;
					slicedSticks+=currSticks;
				}
				else {
					currSticks--;
					slicedSticks++;
				}
			}
		}
		bw.write(String.valueOf(slicedSticks));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}