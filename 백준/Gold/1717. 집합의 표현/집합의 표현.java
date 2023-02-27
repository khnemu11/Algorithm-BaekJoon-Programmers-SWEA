import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
	0 : a를 부모로 b를 삽입
	1 : a와 b가 같은 집합인지 판단 (YES NO)
	유니온-파인드를 이용해 집합 병합
*/
public class Main {
	static int parents[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken()); // 집합의 개수
		int M = Integer.valueOf(st.nextToken()); // 연산의 개수

		parents = new int[N + 1];

		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.valueOf(st.nextToken());
			int a = Integer.valueOf(st.nextToken());
			int b = Integer.valueOf(st.nextToken());

			if (command == 0) {
				union(a, b);
			} else {
				if (getParent(a) != getParent(b)) {
					System.out.println("NO");
				} else {
					System.out.println("YES");
				}
			}
		}

	}

	public static int getParent(int child) {
		if (parents[child] == child) {
			return child;
		}

		parents[child] = getParent(parents[child]);

		return parents[child];

	}

	public static void union(int a, int b) {
		int pa = getParent(a);
		int pb = getParent(b);

		parents[pb] = pa;
	}
}