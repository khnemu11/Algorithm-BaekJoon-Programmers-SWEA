
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = 100;
		for (int test_case = 1; test_case <= 10; test_case++) {
			int no = sc.nextInt();

			String board[] = new String[size];

			for (int i = 0; i < size; i++) {
				board[i] = sc.next();
			}
			int max = 1;
			// 행
			for (int i = 0; i < size; i++) {
				for (int start = 0; start < size; start++) {
					for (int end = start; end < size; end++) {
						StringBuilder sb = new StringBuilder();
						sb.append(board[i].substring(start, end + 1));
						String original = sb.toString();
						String reverse = sb.reverse().toString();

						if (max < original.length() && original.equals(reverse)) {
							max = original.length();
//							System.out.println(original);
//							System.out.println(max);
						}
					}
				}
			}

			// 열
			for (int i = 0; i < size; i++) {
				StringBuilder columnBuilder = new StringBuilder();
				for (int row = 0; row < board.length; row++) {
					columnBuilder.append(board[row].charAt(i));
				}
				for (int start = 0; start < size; start++) {
					for (int end = start; end < size; end++) {
						StringBuilder column = new StringBuilder();
						column.append(columnBuilder.substring(start, end + 1));
						String original = column.toString();
						String reverse = column.reverse().toString();

						if (max < original.length() && original.equals(reverse)) {
							max = original.length();
//							System.out.println(original);
//							System.out.println(max);
						}
					}
				}
			}

			StringBuilder result = new StringBuilder();
			result.append("#").append(test_case).append(" ").append(max);
			System.out.println(result.toString());
		}
		
	}

}
