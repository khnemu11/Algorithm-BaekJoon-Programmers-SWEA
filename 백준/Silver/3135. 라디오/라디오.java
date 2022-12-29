import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.valueOf(st.nextToken());
		int goal = Integer.valueOf(st.nextToken());
		int min = Math.abs(goal - start);
		int favoriteSize = Integer.valueOf(br.readLine());

		while (favoriteSize-- > 0) {
			int favorite = Integer.valueOf(br.readLine());

			min = Math.min(min, Math.abs(goal - favorite) + 1);
		}
		bw.write(String.valueOf(min) + "\n");
		
		bw.flush();
		
	}
}
