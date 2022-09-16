import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		PriorityQueue<Integer> minheap = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();
		int n = Integer.valueOf(br.readLine());
		
		for(int i=0;i<n;i++) {
			int num = Integer.valueOf(br.readLine());
			
			
			if(num==0) {
				if(minheap.isEmpty()) {
					sb.append(0);
				}
				else {
					sb.append(minheap.poll());
				}
				sb.append("\n");
			}
			else {
				minheap.add(num);
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}