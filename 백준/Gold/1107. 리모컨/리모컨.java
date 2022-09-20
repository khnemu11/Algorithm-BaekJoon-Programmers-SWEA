import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean visited[][];
	static Queue<Integer> xQueue = new LinkedList<Integer>();
	static Queue<Integer> yQueue = new LinkedList<Integer>();
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int curr = 100;
		int goal = Integer.valueOf(br.readLine());
		int blockNum = Integer.valueOf(br.readLine());
		int candidate[] = new int[3];

		boolean blocks[] = new boolean[11];

		if (blockNum != 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < blockNum; i++) {
				blocks[Integer.valueOf(st.nextToken())] = true;
			}
		}
		int min = Math.abs(goal - curr);
		if (!blocks[0]) {
			min = Math.min(Math.abs(goal - 0) + 1, min);
		}
		if(blockNum!=10) {
			for (int i = 1; i < 1000000; i++) {
				boolean possible = true;
				int temp = i;
				while (temp > 0) {
					if (blocks[temp % 10]) {
						possible = false;
						break;
					} else {
						temp = temp / 10;
					}
				}
				if (possible) {
					min = Math.min(Math.abs(goal - i) + String.valueOf(i).length(), min);
				}
			}
		}
		
		bw.write(String.valueOf(min));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}