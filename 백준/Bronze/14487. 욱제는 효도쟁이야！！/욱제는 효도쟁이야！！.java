import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
		public static void main(String []args) throws Exception{	
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			br.readLine();
			int [] arrives = Arrays.stream(br.readLine().split(" ")).mapToInt(x->Integer.valueOf(x)).toArray();
			int max = Arrays.stream(arrives).max().getAsInt();
			int total = Arrays.stream(arrives).sum();
			
			bw.write(String.valueOf(total-max));
			
			bw.flush();
			bw.close();
			br.close();
		}		
}
