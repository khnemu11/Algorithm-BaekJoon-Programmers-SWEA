import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String game = br.readLine();
		int bonus[] = new int[game.length() + 2];
		int sum = 0;
		int frame = 1;
		int chance = 1;
		int before = 0;
		for (int i = 0; i < game.length(); i++) {
			char curr = game.charAt(i);
			if (curr == 'S') {
				sum = sum + 10 * (bonus[i] + 1);
				if (frame < 10) {
					bonus[i + 1]++;
					bonus[i + 2]++;
				}
				before = 10;
				frame++;
				chance = 1;
			} else if (curr == '-') {
				chance++;
				if (chance > 2) {
					chance = 1;
					frame++;
				}
				before = 0;
			} else if (curr == 'P') {
				sum = sum + (10 - before) * (bonus[i] + 1);
				if (frame < 10) {
					bonus[i + 1]++;
				}
				before = 10 - before;
				frame++;
				chance = 1;
			} else {
				int score = curr - '0';
				sum = sum + score * (bonus[i] + 1);
				chance++;
				if (chance > 2) {
					chance = 1;
					frame++;
				}
				before = score;
			}
		}
		bw.write(String.valueOf(sum));
		bw.newLine();
		bw.flush();
	}
}
