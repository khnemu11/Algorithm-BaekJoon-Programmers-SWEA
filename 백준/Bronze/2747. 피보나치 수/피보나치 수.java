import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static boolean visited[];
	static long value[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.valueOf(br.readLine());
		
		visited = new boolean[n + 1];
		value = new long[n + 1];
		
		long result = fibonacci(n);

		bw.write(String.valueOf(result));
		bw.newLine();
		bw.close();
		br.close();
	}

	public static long fibonacci(int n) {
		if(visited[n]) {
			return value[n];
		}
		else {
			visited[n]=true;
			if (n == 1 || n == 2) {
				value[n] = 1;
				
				return value[n];
			} else {
				value[n]=fibonacci(n-1)+fibonacci(n-2);
				
				return value[n];
			}
		}
		
	}
}