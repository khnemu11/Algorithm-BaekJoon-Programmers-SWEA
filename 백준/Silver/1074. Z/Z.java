import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.valueOf(st.nextToken());
		int goalRow = Integer.valueOf(st.nextToken());
		int goalCol = Integer.valueOf(st.nextToken());

		count = 0;
		zigzag((int) Math.pow(2, n), goalRow, goalCol);

		bw.write(String.valueOf(count));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}

	public static void zigzag(int length, int goalRow, int goalCol) {
		if (length == 0) {
			return;
		} else {
			int locate = 0;
			if (goalRow < length / 2 && goalCol < length / 2) {
				locate = 1;
			}
			else if (goalRow < length / 2 && goalCol >= length / 2) {
				locate = 2;
				goalCol -=length / 2;
			}
			else if (goalRow >= length / 2 && goalCol < length / 2) {
				locate = 3;
				goalRow -=length / 2;
			}
			else if (goalRow >= length / 2 && goalCol >= length / 2) {
				locate = 4;
				goalRow -=length / 2;
				goalCol -=length / 2;
			}
			
			count+=(length/2)*(length/2)*(locate-1);
			
			zigzag(length/2,goalRow,goalCol);
		}
	}

}