import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class Main {
	static int map[][];
	static int bluecnt;
	static int whitecnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		HashMap<Character, Integer> chronogramMap = new HashMap<>();

		chronogramMap.put('I', 1);
		chronogramMap.put('V', 5);
		chronogramMap.put('X', 10);
		chronogramMap.put('L', 50);
		chronogramMap.put('C', 100);
		chronogramMap.put('D', 500);
		chronogramMap.put('M', 1000);

		int N = Integer.valueOf(br.readLine());

		for (int t = 0; t < N; t++) {
			String str = br.readLine();
			int year = 0;
			for (int i = 0; i < str.length(); i++) {
				year += chronogramMap.getOrDefault(str.charAt(i), 0);
			}
			bw.write(String.valueOf(year));
			bw.newLine();
		}
		bw.flush();
	}

}
