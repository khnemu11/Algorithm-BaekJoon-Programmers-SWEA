
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			Bulb a = new Bulb(sc.nextInt(), sc.nextInt());
			Bulb b = new Bulb(sc.nextInt(), sc.nextInt());
			int time = 0;

			if (b.end < a.start || a.end < b.start) {
				time = 0;
			} else {
				time = Math.min(a.end, b.end) - Math.max(a.start, b.start);
			}

			StringBuilder sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ").append(time);
			System.out.println(sb.toString());
		}

	}
}

class Bulb {
	int start;
	int end;

	public Bulb(int start, int end) {
		this.start = start;
		this.end = end;
	}
}