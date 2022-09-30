import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<Integer> queue = new LinkedList<>();
		int size = Integer.valueOf(st.nextToken());
		int seq = Integer.valueOf(st.nextToken());

		for (int i = 1; i <= size; i++) {
			queue.add(i);
		}
		String result [] = new String[size];
		for(int k=0;k<size;k++) {
			for (int i = 0; i < seq - 1; i++) {
				int num = queue.poll();
				queue.add(num);
			}
			result[k] = String.valueOf(queue.poll());
		}
		
		String answer = Arrays.stream(result).collect(Collectors.joining(", ","<", ">"));
		
		bw.write(answer);
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
