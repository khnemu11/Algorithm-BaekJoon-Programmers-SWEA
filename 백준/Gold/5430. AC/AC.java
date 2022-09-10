import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.valueOf(br.readLine());

		for (int t = 0; t < T; t++) {
			String command = br.readLine();
			boolean reverse = false;
			boolean error = false;
			Deque<String> deque = new ArrayDeque<>();
			int length = Integer.valueOf(br.readLine());
			
			String arr = br.readLine();
			if(length>0) {
				arr = arr.replaceAll("[\\[\\]]", "");
				String nums [] = arr.split(",");
				
				for (int i = 0; i < length; i++) {
					deque.addLast(nums[i]);
				}
			}
//			System.out.println("deque : "+deque.toString());
//			System.out.println("command : "+command);
			for (int i = 0; i < command.length(); i++) {
				if (command.charAt(i) == 'R') {
					reverse = !reverse;
				} else {
					if (deque.isEmpty()) {
						error = true;
						break;
					} else {
						if (reverse) {
							deque.pollLast();
						} else {
							deque.pollFirst();
						}
					}
				}
	//		System.out.println(deque.toString());
			}
			
			if(error) {
				bw.write("error");
//			System.out.println("error");
			}
			else {
				StringBuilder sb = new StringBuilder("[");
				if(reverse) {
					while(!deque.isEmpty()) {
						sb.append(deque.pollLast());
						sb.append(",");
					}
				}
				else {
					while(!deque.isEmpty()) {
						sb.append(deque.pollFirst());
						sb.append(",");
					}
				}
				if(sb.charAt(sb.length()-1)==',') {
					sb.deleteCharAt(sb.length()-1);
				}
				
				sb.append("]");
//				System.out.println(deque.toString().replace(" ", ""));
				bw.write(sb.toString());
			}
			bw.newLine();
			bw.flush();
		}

		br.close();
		bw.close();
	}

}