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
		int T = Integer.valueOf(br.readLine());
		StringTokenizer st;
		
		for(int i=0;i<T;i++) {
			st = new StringTokenizer(br.readLine());
			
			int startX = Integer.valueOf(st.nextToken());
			int startY = Integer.valueOf(st.nextToken());
			int endX = Integer.valueOf(st.nextToken());
			int endY = Integer.valueOf(st.nextToken());
			
			int n = Integer.valueOf(br.readLine());
			
			int outCount =0;
			int enterCount=0;
			
			for(int j=0;j<n;j++) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.valueOf(st.nextToken());
				int y = Integer.valueOf(st.nextToken());
				int r = Integer.valueOf(st.nextToken());
				
				if(Math.pow(x-startX, 2) + Math.pow(y-startY, 2) < Math.pow(r, 2) &&
						Math.pow(x-endX, 2) + Math.pow(y-endY, 2) < Math.pow(r, 2)) {
					continue;
				}
				else if(Math.pow(x-startX, 2) + Math.pow(y-startY, 2) < Math.pow(r, 2)) {
					outCount++;
				}
				
				else if(Math.pow(x-endX, 2) + Math.pow(y-endY, 2) < Math.pow(r, 2)) {
					enterCount++;
				}
			}
			int result = outCount+enterCount;
			bw.write(String.valueOf(result));
			bw.newLine();
		}
		bw.flush();
		br.close();
		bw.close();
	}
}