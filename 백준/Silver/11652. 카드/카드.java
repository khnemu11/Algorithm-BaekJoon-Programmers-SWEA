import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		HashMap<Long, Integer> map = new HashMap<>();
		int size = Integer.valueOf(br.readLine());
		int maxValue = 1;
		long maxKey = Long.valueOf(br.readLine());
		map.put(maxKey, 1);

		for (int i = 0; i < size - 1; i++) {
			long key = Long.valueOf(br.readLine());
			map.put(key, map.getOrDefault(key, 0) + 1);

			int value = map.get(key);

			if (maxValue < value || (maxValue == value && maxKey > key)) {
				maxValue = value;
				maxKey = key;
			}
		}

		bw.write(String.valueOf(maxKey));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
