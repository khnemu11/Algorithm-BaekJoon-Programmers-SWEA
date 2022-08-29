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
		
		int deadGuitarLines = Integer.valueOf(st.nextToken());
		int N = Integer.valueOf(st.nextToken());
		int min = Integer.MAX_VALUE;
		int bundleMin = Integer.MAX_VALUE;
		int eachMin = Integer.MAX_VALUE;
		
		for(int i =0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			bundleMin = Math.min(bundleMin, Integer.valueOf(st.nextToken()));
			eachMin = Math.min(eachMin, Integer.valueOf(st.nextToken()));
			
		}
		if(bundleMin<eachMin*6) {
			min = Math.min(bundleMin*(deadGuitarLines/6) + eachMin*(deadGuitarLines%6),bundleMin*(deadGuitarLines/6+(deadGuitarLines%6>0?1:0)));
		}
		else {
			min = eachMin*deadGuitarLines;
		}
		
		bw.write(String.valueOf(min));
		bw.newLine();
		
		bw.flush();
		bw.close();
		br.close();
	}
}
