import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static boolean visited[];
	static List<String> vowels = Arrays.asList("a", "e", "i", "o", "u");

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int length = Integer.valueOf(st.nextToken());
		int size = Integer.valueOf(st.nextToken());

		visited = new boolean[size];

		String arr[] = br.readLine().split(" ");

		Arrays.sort(arr);

		dfs(0, 0, length, 0, 0, arr);

		bw.close();
		br.close();
	}

	public static void dfs(int vowel, int consonant, int depth, int curr, int index, String arr[]) {
		if (depth == curr) {
			if (vowel > 0 && consonant > 1) {
				for (int i = 0; i < arr.length; i++) {
					if (visited[i]) {
						System.out.print(arr[i]);
					}
				}
				System.out.println();
			}
		} else {
			for (int i = index; i < arr.length; i++) {
				if (visited[i]) {
					continue;
				}

				visited[i] = true;
				if (vowels.contains(arr[i])) {
					vowel++;
					
					dfs(vowel, consonant, depth, curr + 1, i + 1, arr);

					visited[i] = false;
					vowel--;
				} else {
					consonant++;
					
					dfs(vowel, consonant, depth, curr + 1, i + 1, arr);

					visited[i] = false;
					consonant--;
				}

			}
		}
	}
}