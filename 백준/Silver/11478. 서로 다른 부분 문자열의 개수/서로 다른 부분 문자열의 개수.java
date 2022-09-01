import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String word =br.readLine();
		Set<String> subwords = new HashSet<>(); 
		
		for(int i=0;i<word.length();i++) {
			for(int j=i;j<=word.length();j++) {
				String sub = word.substring(i,j);
				subwords.add(sub);
			}
		}
		
		bw.write(String.valueOf(subwords.size()-1));
		bw.flush();
		bw.close();
		br.close();
		
	}
}
