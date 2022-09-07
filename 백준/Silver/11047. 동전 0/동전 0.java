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
		int N = Integer.parseInt(st.nextToken());
		int cost = Integer.parseInt(st.nextToken());
		
		int count =0;
		int coins[] = new int[N];
		for(int i=0;i<N;i++) {
			coins[i] = Integer.valueOf(br.readLine());
		}

		for(int i=N-1;i>-1;i--) {
			if(cost/coins[i]==0)	continue;
			if(cost == 0)	break;
			count+=cost/coins[i];
			cost = cost%coins[i];
		}
		
		bw.write(String.valueOf(count));
		bw.flush();
		bw.close();
		br.close();
	}

}