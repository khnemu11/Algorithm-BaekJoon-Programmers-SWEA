import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static String gears[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		gears = new String[4];
		int LEFT = -1, RIGHT = 1, EAST = 2, WEST = 6, N = 0, S = 1;

		for (int i = 0; i < 4; i++) {
			gears[i] = br.readLine();
		}

		int T = Integer.valueOf(br.readLine());

		while (T-- > 0) {
//			System.out.println("No." + T);
			StringTokenizer st = new StringTokenizer(br.readLine());
			int seq = Integer.valueOf(st.nextToken()) - 1;
			int direction = Integer.valueOf(st.nextToken());
			char beforeEast = gears[seq].charAt(EAST);
			char beforeWest = gears[seq].charAt(WEST);
//			System.out.println(seq);
			rotate(seq, direction);

			int right = seq + 1;
			int currDirection = direction;

			while (right < 4) {
//				System.out.println("curr east : " + beforeEast + " vs " + "next right west : " + gears[right].charAt(WEST));
				if (beforeEast == gears[right].charAt(WEST)) {
					break;
				}
				beforeEast = gears[right].charAt(EAST);
				currDirection = currDirection * -1;

				rotate(right, currDirection);
				right++;
			}

			int left = seq - 1;
			currDirection = direction;

			while (left >= 0) {
//				System.out.println("curr west : " + beforeWest + " vs " + "next left " + gears[left].charAt(EAST));
				if (beforeWest == gears[left].charAt(EAST)) {
					break;
				}
				beforeWest = gears[left].charAt(WEST);
				currDirection = currDirection * -1;

				rotate(left, currDirection);

				left--;
			}

//			System.out.println(Arrays.deepToString(gears));
		}

		int sum = 0;

		for (int i = 0; i < 4; i++) {
			sum += gears[i].charAt(0) == '1' ? Math.pow(2, i) : 0;
		}

		bw.write(String.valueOf(sum));
		bw.newLine();

		bw.flush();
		bw.close();
		br.close();
	}

	public static void rotate(int seq, int direction) {
		if (direction == -1) {
			gears[seq] = gears[seq].substring(1) + gears[seq].charAt(0);

		} else {
			gears[seq] = gears[seq].charAt(7) + gears[seq].substring(0, 7);
		}
	}
}
