import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] arr = br.readLine().split("[ \\s,:]");

		LocalDateTime start = LocalDateTime.of(Integer.valueOf(arr[3]), 1, 1, 0, 0);
		LocalDateTime curr = LocalDateTime.of(Integer.valueOf(arr[3]), Month.valueOf(arr[0].toUpperCase()),
				Integer.valueOf(arr[1]), Integer.valueOf(arr[4]), Integer.valueOf(arr[5]));
		LocalDateTime end = LocalDateTime.of(Integer.valueOf(arr[3]) + 1, 1, 1, 0, 0);

		double currMinute = ChronoUnit.MINUTES.between(start, curr);
		double endMinute = ChronoUnit.MINUTES.between(start, end);

		bw.write(String.format("%.9f", currMinute / endMinute * 100));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
