import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int W = Integer.valueOf(st.nextToken());
		int H = Integer.valueOf(st.nextToken());
		int X = Integer.valueOf(st.nextToken());
		int Y = Integer.valueOf(st.nextToken());
		int P = Integer.valueOf(st.nextToken());
		double R = (double) H / 2;
		int count = 0;
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int personX = Integer.valueOf(st.nextToken());
			int personY = Integer.valueOf(st.nextToken());

			// 직사각형 확인
			if (personX >= X && personX <= (X + W) && personY >= Y && personY <= (Y + H)) {
				count++;
			} // 원 확인
			else if (Math.pow(personX - X, 2) + Math.pow(personY - (Y + R), 2) <= Math.pow(R, 2)) {
				count++;
			} // 원 확인
			else if (Math.pow(personX - (X + W), 2) + Math.pow(personY - (Y + R), 2) <= Math.pow(R, 2)) {
				count++;
			}

		}
		bw.write(String.valueOf(count));
		bw.newLine();
		bw.flush();
		
		br.close();
		bw.close();
	}
}