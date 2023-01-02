import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int num[] = Arrays.stream(br.readLine().split("")).mapToInt(x -> Integer.valueOf(x)).toArray();

		int sum = Arrays.stream(num).sum();

		bw.write(String.valueOf(sum));
		bw.newLine();

		bw.flush();
	}
}
