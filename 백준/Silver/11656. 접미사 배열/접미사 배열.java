import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String word = br.readLine();
		
		String [] suffix = new String[word.length()];
		
		for(int i=word.length()-1;i>=0;i--) {
			suffix[i] = word.substring(i);
		}
		
		bw.write(Arrays.stream(suffix).sorted().collect(Collectors.joining("\n","","")));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
