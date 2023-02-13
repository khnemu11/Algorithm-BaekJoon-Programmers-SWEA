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
		LinkedList<Character> list = new LinkedList<>();
		char str[] = br.readLine().toCharArray();

		for (char alpha : str) {
			list.add(alpha);
		}

		int M = Integer.valueOf(br.readLine());

		ListIterator<Character> it = list.listIterator();

		while (it.hasNext()) {
			it.next(); // 커서를 맨 오른쪽으로이동
		}

		while (M-- > 0) { // 왼쪽,오른쪽으로 이동시 왼쪽,오른쪽으로 이동이 가능한지 확인 후 이동 필요
			char[] command = br.readLine().toCharArray();
			if (command[0] == 'P') {
				it.add(command[2]);
			} else if (command[0] == 'L') {
				if (it.hasPrevious()) {
					it.previous();
				}
			} else if (command[0] == 'B') {
				if (it.hasPrevious()) {
					it.previous();
					it.remove();
				}
			} else if (command[0] == 'D') {
				if (it.hasNext()) {
					it.next();
				}
			}
		}
		StringBuilder sb = new StringBuilder();

		for (char alpha : list) {
			sb.append(alpha);
		}

		System.out.println(sb.toString());
	}
}