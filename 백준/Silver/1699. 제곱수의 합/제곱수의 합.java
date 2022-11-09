import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int target = Integer.valueOf(br.readLine());
		ArrayList<Integer> powList = new ArrayList<>();
		int num[] = new int[target + 1];
		num[1] = 1;
		for (int i = 2; i <= target; i++) {
			if (Math.pow((int) Math.sqrt(i), 2) == i) {
				num[i] = 1;
				powList.add(i);
			} else {
				int min = num[i - 1] + 1;
				for (int powNum : powList) {
					min = Math.min(num[powNum] + num[i - powNum], min);
					if (min == 2) {
						break;
					}
				}
				num[i] = min;
			}
		}
		bw.write(String.valueOf(num[target]));
		bw.newLine();
		bw.flush();
		br.close();
		bw.close();
	}
}
