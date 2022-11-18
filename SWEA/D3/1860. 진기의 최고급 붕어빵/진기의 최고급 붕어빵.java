import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int num = sc.nextInt();
			int sec = sc.nextInt();
			int make = sc.nextInt();

			int arrive[] = new int[11112];

			for (int i = 0; i < num; i++) {
				arrive[sc.nextInt()]++;
			}

			int item = 0;
			int wait = 0;
			int rest = num;
			boolean isPossible = true;
			for (int i = 0; i < arrive.length; i++) {
				if (rest == 0) {
					break;
				}

				if (i != 0 && i % sec == 0) {
					item += make;
				}
				if (arrive[i] > 0) {
					item -= arrive[i];
					if (item < 0) {
						isPossible = false;
						break;
					}
				}
			}

			StringBuilder sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ");
			if (isPossible) {
				sb.append("Possible");
			} else {
				sb.append("Impossible");
			}

			System.out.println(sb.toString());
		}
	}

}
