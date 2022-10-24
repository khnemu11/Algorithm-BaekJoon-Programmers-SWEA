import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int testRoomSize = Integer.valueOf(br.readLine());
		int testRooms[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int master = Integer.valueOf(st.nextToken());
		int sub = Integer.valueOf(st.nextToken());
		long total = 0;

		for (int i = 0; i < testRoomSize; i++) {
			if (testRooms[i] > master) {
				total = total + 1 + (int) Math.ceil(((double) testRooms[i] - master) / sub);
			} else {
				total++;
			}
		}

		bw.write(String.valueOf(total));
		bw.newLine();
		bw.flush();

		br.close();
		bw.close();
	}
}