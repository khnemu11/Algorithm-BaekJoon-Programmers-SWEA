import java.util.Scanner;
import java.io.FileInputStream;


class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
		StringBuilder result = new StringBuilder();
			boolean rowVisited[] = new boolean[8];
			boolean colVisited[] = new boolean[8];
			boolean isValid = true;
			long count = 0;

			for (int i = 0; i < 8; i++) {
				String row = sc.next();
				if (!isValid) {
					continue;
				}
				for (int j = 0; j < 8; j++) {
					if (row.charAt(j) == 'O') {
						count++;
						if (rowVisited[i] || colVisited[j]) {
							isValid = false;
							break;
						}
						rowVisited[i] = true;
						colVisited[j] = true;
					}
				}
			}

			if (isValid && count == 8) {
				result.append("#" + test_case + " yes");
			} else {
				result.append("#" + test_case + " no");
			}
			System.out.println(result.toString());
		}
	}
}