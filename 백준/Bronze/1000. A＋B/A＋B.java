import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).sum() + "\n");
		bw.flush(); // 결과 출력
		bw.close();
	}
}