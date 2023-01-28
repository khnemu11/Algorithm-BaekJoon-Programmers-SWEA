import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int parents[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.valueOf(st.nextToken());
		int M = Integer.valueOf(st.nextToken());

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
				if (getParent(a) == getParent(b)) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}

		}
	}

	public static int getParent(int child) {
		if (child == parents[child]) {
			return child;
		} else {
			parents[child] = getParent(parents[child]);
			return parents[child];
		}
	}

	public static void union(int a, int b) {
		int pa = getParent(a);
		int pb = getParent(b);

		parents[pa] = pb;
	}
}
