import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int size = Integer.valueOf(br.readLine());
		int row[] = new int[3];
		int min[] = new int[3];
		int max[] = new int[3];
		int temp[] = new int[3];

		while (size-- > 0) {
			row = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			temp = Arrays.copyOf(min, 3);

			min[0] = Math.min(row[0] + temp[0], row[0] + temp[1]);
			min[1] = Math.min(row[1] + temp[2], Math.min(row[1] + temp[0], row[1] + temp[1]));
			min[2] = Math.min(row[2] + temp[1], row[2] + temp[2]);

			temp = Arrays.copyOf(max, 3);

			max[0] = Math.max(row[0] + temp[0], row[0] + temp[1]);
			max[1] = Math.max(row[1] + temp[2], Math.max(row[1] + temp[0], row[1] + temp[1]));
			max[2] = Math.max(row[2] + temp[1], row[2] + temp[2]);
		}

		int lowest = Arrays.stream(min).min().getAsInt();
		int highest = Arrays.stream(max).max().getAsInt();

		StringBuilder sb = new StringBuilder();
		sb.append(highest + " " + lowest);
		bw.write(sb.toString());
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}

}
