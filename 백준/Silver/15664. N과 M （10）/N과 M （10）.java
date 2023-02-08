import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
	오름차순 조합을 위해 정렬 후 dfs
	중복이 되면 안되고 순서를 지켜야 하므로 linkedHashSet을 사용하여 중복을 제거하고 입력받은 순서대로 저장
*/

public class Main {
	static HashSet<String> set = new LinkedHashSet<>();
	static int N, M;
	static int nums[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.valueOf(st.nextToken());
		M = Integer.valueOf(st.nextToken());

		nums = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		Arrays.sort(nums);
		combination(0, 0, new int[M]);

		for (String comb : set) {
			bw.write(comb + "\n");
		}

		bw.flush();
	}

	public static void combination(int size, int idx, int[] selected) throws IOException {
		if (size == M) {
			StringBuilder sb = new StringBuilder();
			for (int num : selected) {
				sb.append(num + " ");
			}
			set.add(sb.deleteCharAt(sb.length() - 1).toString());
		} else {
			for (int i = idx; i < nums.length; i++) {
				selected[size] = nums[i];
				combination(size + 1, i + 1, selected);
			}
		}
	}
}
