import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static boolean possible[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int[] mask = new int[2];
		int[] shirts = new int[2];
		int[] pants = new int[2];
		int min = Integer.MAX_VALUE;
		int result = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		mask[0] = Integer.valueOf(st.nextToken());
		mask[1] = Integer.valueOf(st.nextToken());
		
		min = Math.min(min, Math.min(mask[0], mask[1]));
		
		st = new StringTokenizer(br.readLine());
		shirts[1] = Integer.valueOf(st.nextToken());
		shirts[0] = Integer.valueOf(st.nextToken());

		min = Math.min(min, Math.min(shirts[0], shirts[1]));
		
		st = new StringTokenizer(br.readLine());
		pants[0] = Integer.valueOf(st.nextToken());
		pants[1] = Integer.valueOf(st.nextToken());

		min = Math.min(min, Math.min(pants[0], pants[1]));
		
		for(int i=0;i<2;i++) {
			mask[i]-=min;
			shirts[i]-=min;
			pants[i]-=min;
		}
		
		result+=min*2;

		if(mask[1] > 0 && shirts[1] > 0 && pants[1] > 0) {
			result++;
		}
		else if(mask[0] > 0 && shirts[0] > 0 && pants[0] > 0) {
			result++;
		}


		bw.write(String.valueOf(result));
		bw.newLine();
		bw.flush();

		br.close();
		bw.close();
	}

}
