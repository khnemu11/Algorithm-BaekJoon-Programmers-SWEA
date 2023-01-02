import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int size = sc.nextInt();

			int arr[][] = new int[size][size];

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					arr[i][j] = sc.nextInt();
				}
			}

			List<String> diag90 = new ArrayList<>();

			for (int col = 0; col < size; col++) {
				StringBuilder sbDiag90 = new StringBuilder();
				for (int row = size - 1; row >= 0; row--) {
					sbDiag90.append(arr[row][col]);
				}
				diag90.add(sbDiag90.toString());
			}

			List<String> diag180 = new ArrayList<>();

			for (int row = size - 1; row >= 0; row--) {
				StringBuilder sbDiag180 = new StringBuilder();
				for (int col = size - 1; col >= 0; col--) {
					sbDiag180.append(arr[row][col]);
				}
				diag180.add(sbDiag180.toString());
			}

			List<String> diag270 = new ArrayList<>();

			for (int col = size - 1; col >= 0; col--) {
				StringBuilder sbDiag270 = new StringBuilder();
				for (int row = 0; row < size; row++) {
					sbDiag270.append(arr[row][col]);
				}
				diag270.add(sbDiag270.toString());
			}

			System.out.println("#" + test_case);
			StringBuilder result = new StringBuilder();

			for (int i = 0; i < diag90.size(); i++) {
				result.append(diag90.get(i) + " " + diag180.get(i) + " " + diag270.get(i) + "\n");
			}

			System.out.print(result.toString());
		}
	}
}