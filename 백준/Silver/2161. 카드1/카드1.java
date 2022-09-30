import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Queue<Integer>queue = new LinkedList<>();
		
		int size = Integer.valueOf(br.readLine());
		
		for(int i=1;i<=size;i++) {
			queue.add(i);
		}
		StringBuilder sb = new StringBuilder();
		while(queue.size()!=1) {	
			sb.append(queue.poll());
			sb.append(" ");
			queue.add(queue.poll());
		}
		sb.append(queue.poll());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
