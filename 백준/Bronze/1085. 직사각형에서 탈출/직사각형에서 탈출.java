import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int x = Integer.valueOf(st.nextToken());
		int y = Integer.valueOf(st.nextToken());
		int w = Integer.valueOf(st.nextToken());
		int h = Integer.valueOf(st.nextToken());
		
		int [] arr= {x-0,y-0,w-x,h-y};
		
		bw.write(String.valueOf(Arrays.stream(arr).min().getAsInt()));
		bw.newLine();
		
		bw.flush();
		br.close();
		bw.close();
	}
}