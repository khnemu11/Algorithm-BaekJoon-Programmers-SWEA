import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	public static void main(String arg[]) {
		Scanner sc = new Scanner(System.in);
		for (int test_case = 1; test_case <= 10; test_case++) {
			int length = sc.nextInt();
			String board[][] = new String[8][8];

			for (int i = 0; i < board.length; i++) {
				board[i] = sc.next().split("");
			}

			int upDown[] = { -1, 1, 0, 0 };
			int leftRight[] = { 0, 0, -1, 1 };
			int count = 0;

			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[0].length; j++) {
					for (int k = 0; k < 4; k++) {
						int nextRow = i + upDown[k] * (length - 1);
						int nextCol = j + leftRight[k] * (length - 1);

						if (nextRow < 0 || nextCol < 0 || nextRow >= board.length || nextCol >= board[0].length
								|| !board[i][j].equals(board[nextRow][nextCol])) {
							continue;
						}
						boolean isPallin = true;
//						System.out.println("curr : " + i + " , " + j + " k : " + k);
						for (int curr = 0; curr < length / 2; curr++) {
//							System.out.println((i + curr * upDown[k]) + " , " + (j + leftRight[k] * curr) + " vs "
//									+ (nextRow - curr * upDown[k]) + " , " + (nextCol - leftRight[k] * curr));
							if (!board[i + curr * upDown[k]][j + leftRight[k] * curr]
									.equals(board[nextRow - curr * upDown[k]][nextCol - leftRight[k] * curr])) {
								isPallin = false;
								break;
							}
						}

						if (isPallin) {
							count++;
						}
					}
				}
			}

			StringBuilder result = new StringBuilder("#");
			result.append(test_case).append(" ").append(count / 2);
			System.out.println(result.toString());
		}
	}
}
