import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int row = Integer.valueOf(st.nextToken());
		int column = Integer.valueOf(st.nextToken());
		int inventory = Integer.valueOf(st.nextToken());

		int land[][] = new int[row][column];
		int times[] = new int[257];
		int minTime = Integer.MAX_VALUE;
		int maxHeight = Integer.MIN_VALUE;
		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < column; j++) {
				land[i][j] = Integer.valueOf(st.nextToken());
			}
		}

		for (int height = 0; height < 257; height++) {
			int temp = inventory;
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					int differ = land[i][j] - height;

					if (differ > 0) {
						inventory+=differ;
						times[height] = times[height] + differ * 2;
					} else if (differ < 0) {
						inventory+=differ;
						times[height] = times[height] + differ*(-1);
					}

				}
			}

			if(inventory>=0 && minTime>=times[height] && maxHeight<height) {
				minTime = times[height];
				maxHeight = height;
			}
			
			inventory = temp;
		}
		
		bw.write(String.valueOf(minTime)+" "+String.valueOf(maxHeight));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
