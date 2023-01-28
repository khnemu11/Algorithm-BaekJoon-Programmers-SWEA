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

		parents = new int[N];

		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}
		
		int cnt = 1;

		for (; cnt <= M; cnt++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.valueOf(st.nextToken());
			int b = Integer.valueOf(st.nextToken());

			if (!union(a, b)) {
				System.out.println(cnt);
				break;
			}
		}

		if (cnt > M) {
			System.out.println(0);
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

	public static boolean union(int a, int b) {
		int pa = getParent(a);
		int pb = getParent(b);

		if (pa == pb) {
			return false;
		} else {
			parents[pa] = pb;
			return true;
		}

	}
}
