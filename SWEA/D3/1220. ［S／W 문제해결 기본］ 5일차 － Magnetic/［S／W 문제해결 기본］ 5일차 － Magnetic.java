import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int test_case = 1; test_case <= 10; test_case++) {
			int length = sc.nextInt();
			int board[][] = new int[length][length];
			// 1 : N극, 2 : S극
			for (int i = 0; i < length; i++) {
				for (int j = 0; j < length; j++) {
					board[i][j] = sc.nextInt();
				}
			}

			boolean isMove = true;

			while (isMove) {
				isMove = false;
				for (int i = 0; i < length; i++) {
					for (int j = 0; j < length; j++) {
						if (board[i][j] == 2) {
							if (i == 0) {
								board[i][j] = 0;
							} else if (board[i - 1][j] == 0) {
								board[i][j] = 0;
								board[i - 1][j] = 2;
								isMove = true;
							}
						}
					}
				}
				for (int i = length - 1; i >= 0; i--) {
					for (int j = 0; j < length; j++) {
						if (board[i][j] == 1) {
							if (i == length - 1) {
								board[i][j] = 0;
							} else if (board[i + 1][j] == 0) {
								board[i][j] = 0;
								board[i + 1][j] = 1;
								isMove = true;
							}
						}
					}
				}
//				System.out.println("move finish");
//				for (int i = 0; i < board.length; i++) {
//					for (int j = 0; j < board.length; j++) {
//						System.out.print(board[i][j] + " ");
//					}
//					System.out.println();
//				}
			}

			int count = 0;

			for (int j = 0; j < length; j++) {
				for (int i = 0; i < length - 1; i++) {
					if (board[i][j] == 1 && board[i + 1][j] == 2) {
						count++;
					}
				}
			}
			StringBuilder result = new StringBuilder("#");
			result.append(test_case).append(" ").append(count);
			System.out.println(result.toString());
		}
	}
}
