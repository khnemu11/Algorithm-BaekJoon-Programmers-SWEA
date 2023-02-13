import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

/*
	풀이 알고리즘
	커서 -> iterator
	양 옆으로 움직일 수 있어야함 -> 양방향 리스트 -> listItrator 필요
*/

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.valueOf(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			char commands[] = br.readLine().toCharArray();

			LinkedList<Character> list = new LinkedList<>();
			ListIterator<Character> it = list.listIterator();

			for (char command : commands) {
				if (command == '<') {
					if (it.hasPrevious()) {
						it.previous();
					}
				} else if (command == '>') {
					if (it.hasNext()) {
						it.next();
					}
				} else if (command == '-') { // 현재 커서가 다음에 있고 타겟은 이전이므로 이전으로 돌아가 지워야 한다.
					if (it.hasPrevious()) {
						it.previous();
						it.remove();
					}
				} else {
					it.add(command);
				}

			}

			StringBuilder sb = new StringBuilder();
			for (char alpha : list) {
				sb.append(alpha);
			}
			System.out.println(sb.toString());
		}
	}
}