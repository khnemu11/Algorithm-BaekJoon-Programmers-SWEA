import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int parents[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.valueOf(br.readLine());
		int M = Integer.valueOf(br.readLine());

		parents = new int[N];

		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}

		boolean cities[][] = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int isValid = Integer.valueOf(st.nextToken());

				if (isValid == 1) {
					cities[i][j] = true;
					union(i, j);
				}
			}
		}

		int targets[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

		boolean isValid = true;

		int pa = getParent(targets[0] - 1);

		for (int i = 1; i < targets.length; i++) {
			int pb = getParent(targets[i] - 1);
			if (pa != pb) {
				isValid = false;
				break;
			}
			pa = pb;
		}

		if (isValid) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
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
