import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
				
		int n = Integer.valueOf(br.readLine());
		
		for(int i=0;i<n;i++) {
			Integer [] numList = Arrays.stream(br.readLine().split(" ")).map(x-> Integer.valueOf(x)).toArray(Integer[]::new);
			
			Arrays.sort(numList,Collections.reverseOrder());
			
			bw.write(String.valueOf(numList[2]));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}