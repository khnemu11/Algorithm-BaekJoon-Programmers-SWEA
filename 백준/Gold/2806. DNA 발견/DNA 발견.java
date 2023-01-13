import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int length = Integer.valueOf(br.readLine());
		String dna = br.readLine();
		int dpA[] = new int[length + 1];
		int dpB[] = new int[length + 1];

		for (int i = 0; i < dna.length(); i++) {
			if (dna.charAt(i) == 'A') {
				dpA[i + 1] = Math.min(dpA[i], dpB[i] + 1);
				dpB[i + 1] = Math.min(dpA[i] + 1, dpB[i] + 1);
			} else {
				dpA[i + 1] = Math.min(dpA[i] + 1, dpB[i] + 1);
				dpB[i + 1] = Math.min(dpA[i] + 1, dpB[i]);
			}
		}
		System.out.println(Math.min(dpA[dna.length()], dpA[dna.length()] + 1));
	}
}
