import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int size = Integer.valueOf(br.readLine());
		int input = 0;
		Queue<Integer> packetQueue = new LinkedList<>();
		while ((input = Integer.valueOf(br.readLine())) != -1) {
			if (input == 0) {
				packetQueue.poll();
			} else if (packetQueue.size() < size) {
				packetQueue.add(input);
			}
		}

		StringBuilder sb = new StringBuilder();
		if (packetQueue.isEmpty()) {
			sb.append("empty");
		} else {
			while (!packetQueue.isEmpty()) {
				sb.append(packetQueue.poll() + " ");
			}
			sb = sb.deleteCharAt(sb.length() - 1);
		}
		bw.write(sb.toString());
		bw.newLine();
		bw.flush();
	}
}
