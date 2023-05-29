import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int parents[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int height = Integer.valueOf(st.nextToken());
		int width = Integer.valueOf(st.nextToken());
		parents = new int[width * height];

		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}

		for (int row = 0; row < height; row++) {
			String input = br.readLine();

			for (int col = 0; col < input.length(); col++) {
				char direction = input.charAt(col);
				int parent = row * width + col;
				int child = 0;

				if (direction == 'D') {
					child = (row + 1) * width + col;
				} else if (direction == 'L') {
					child = row * width + col - 1;
				} else if (direction == 'R') {
					child = row * width + col + 1;
				} else if (direction == 'U') {
					child = (row - 1) * width + col;
				}
//				System.out.println("parent : " + parent);
//				System.out.println("child : " + child);

				union(child, parent);
//				System.out.println(Arrays.toString(parents));
			}
		}

		Set<Integer> safeZone = new HashSet<>();

		for (int i = 0; i < parents.length; i++) {
			safeZone.add(getParent(parents[i]));
		}

		bw.write(safeZone.size() + "\n");
		bw.flush();
	}

	public static int getParent(int child) {
		if (parents[child] == child) {
			return parents[child];
		}

		parents[child] = getParent(parents[child]);
		return parents[child];
	}

	public static void union(int child, int parent) {
		int pc = getParent(child);
		int pp = getParent(parent);

		parents[pc] = pp;
	}
}
