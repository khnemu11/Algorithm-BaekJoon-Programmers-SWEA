import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
		public static void main(String []args) throws Exception{	
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			int T = Integer.valueOf(br.readLine());
			int asciiTotal =(90*91)/2-(64*65)/2;
			
			for(int i=0;i<T;i++) {
				String word = Arrays.stream(br.readLine().split("")).distinct().collect(Collectors.joining());
				int count=0;
				for(int j=0;j<word.length();j++) {
					count+=word.charAt(j);
				}
				
				bw.write(String.valueOf(asciiTotal-count));
				bw.newLine();
			}

			bw.flush();
			bw.close();
			br.close();
		}		
}
