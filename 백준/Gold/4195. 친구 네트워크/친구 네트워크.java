import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
	static int[] parent;
	static int[] height;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			HashMap<String, Integer> nameMap = new HashMap<>();
			int num = Integer.valueOf(br.readLine());
			parent = IntStream.iterate(0, x -> x + 1).limit(num * 2 + 1).toArray();
			height = IntStream.iterate(1, x -> x).limit(num * 2 + 1).toArray();
			int curr = 1;
			for (int k = 0; k < num; k++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();

				if (nameMap.get(a) == null) {
					nameMap.put(a, curr);
					curr++;
				}
				if (nameMap.get(b) == null) {
					nameMap.put(b, curr);
					curr++;
				}
				bw.write(String.valueOf(union(nameMap.get(a), nameMap.get(b))));
				bw.newLine();
				
			}
		}
		bw.flush();
	}

	public static int find(int curr) {
		if (curr == parent[curr]) {
			return curr;
		} else {
			parent[curr] = find(parent[curr]);
			return parent[curr];
		}
	}

	public static int union(int a, int b) {
		int x = find(a);
		int y = find(b);

		if (x != y) {
			parent[y] = x;
			height[x] += height[y];
		}
		return height[x];
	}
}