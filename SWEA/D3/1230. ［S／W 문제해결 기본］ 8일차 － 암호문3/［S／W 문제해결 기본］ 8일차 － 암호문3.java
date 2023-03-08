import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/*
	풀이 알고리즘
	
	삭제, 삽입, 변경이 빠른 것 -> 링크드 리스트
 */
public class Solution {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = 10;

		for (int testcase = 1; testcase <= 10; testcase++) {
			int N = scanner.nextInt();

			List<Integer> list = new LinkedList<>();

			for (int i = 0; i < N; i++) {
				list.add(scanner.nextInt());
			}

			int M = Integer.valueOf(scanner.nextInt());

			for (int i = 0; i < M; i++) {

				String command = scanner.next();

				switch (command) {
				case "I":
					int insertIdx = scanner.nextInt();
					int insertSize = scanner.nextInt();

					for (int j = 0; j < insertSize; j++) {
						list.add(insertIdx + j, scanner.nextInt());
					}

					break;
				case "D":
					int deleteIdx = Integer.valueOf(scanner.nextInt());
					int deleteSize = Integer.valueOf(scanner.nextInt());

					for (int d = 0; d < deleteSize; d++) {
						list.remove(deleteIdx);
					}

					break;
				case "A":
					int appendSize = Integer.valueOf(scanner.nextInt());
					for (int a = 0; a < appendSize; a++) {
						int num = Integer.valueOf(scanner.nextInt());
						list.add(num);
					}
					break;
				}
			}
			StringBuilder sBuilder = new StringBuilder();
			for (int i = 0; i < 10; i++) {
				sBuilder.append(list.get(i) + " ");
			}
			bw.write("#" + testcase + " " + sBuilder.deleteCharAt(sBuilder.length() - 1).toString() + "\n");
		}
		bw.flush();
	}
}