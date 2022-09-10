import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Deque<Integer> deque = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		int T = Integer.valueOf(br.readLine());

		for(int i=0;i<T;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String command = st.nextToken();

			if(command.equals("push_back")) {
				deque.offerLast(Integer.valueOf(st.nextToken()));
			}
			else if(command.equals("push_front")) {
				deque.offerFirst(Integer.valueOf(st.nextToken()));
			}
			else if(command.equals("pop_front")) {
				if(deque.isEmpty()) {
					sb.append("-1");
				}
				else {
					sb.append(deque.pollFirst());
				}
				sb.append("\n");
			}
			else if(command.equals("pop_back")) {
				if(deque.isEmpty()) {
					sb.append("-1");
				}
				else {
					sb.append(deque.pollLast());
				}
				sb.append("\n");
			}
			else if(command.equals("size")) {
				sb.append(deque.size());
				sb.append("\n");
			}
			else if(command.equals("empty")) {
				if(deque.isEmpty()) {
					sb.append(1);
				}
				else {
					sb.append(0);
				}
				sb.append("\n");
			}
			else if(command.equals("front")) {
				if(deque.isEmpty()) {
					sb.append(-1);
				}
				else {
					sb.append(deque.peekFirst());
				}
				sb.append("\n");
			}
			else {
				if(deque.isEmpty()) {
					sb.append(-1);
				}
				else {
					sb.append(deque.peekLast());
				}
				sb.append("\n");
			}
		}
		bw.write(sb.deleteCharAt(sb.length()-1).toString());
		bw.newLine();
		bw.flush();
		br.close();
		bw.close();
	}

}