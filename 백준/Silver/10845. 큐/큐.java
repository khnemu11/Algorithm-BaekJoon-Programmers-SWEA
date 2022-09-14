import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Deque<String> queue = new LinkedList<>();	
		
		int T = Integer.valueOf(br.readLine());
		
		for(int i=0;i<T;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			if(command.equals("push")) {
				queue.offer(st.nextToken());
			}
			else if(command.equals("pop")) {
				if(queue.isEmpty()) {
					bw.write(String.valueOf(-1));
				}
				else {
					bw.write(queue.poll());
				}
				bw.newLine();
			}
			else if(command.equals("size")) {
				bw.write(String.valueOf(queue.size()));
				bw.newLine();
			}
			else if(command.equals("empty")) {
				if(queue.isEmpty()) {
					bw.write(String.valueOf(1));
				}
				else {
					bw.write(String.valueOf(0));
				}
				bw.newLine();
			}
			else if(command.equals("front")) {
				if(queue.isEmpty()) {
					bw.write(String.valueOf(-1));
				}
				else {
					bw.write(queue.peekFirst());
				}
				bw.newLine();
			}else if(command.equals("back")) {
				if(queue.isEmpty()) {
					bw.write(String.valueOf(-1));
				}
				else {
					bw.write(String.valueOf(queue.peekLast()));
				}
				bw.newLine();
			}
			
		}
		
		
		bw.flush();
		bw.close();
		br.close();
	}
}
