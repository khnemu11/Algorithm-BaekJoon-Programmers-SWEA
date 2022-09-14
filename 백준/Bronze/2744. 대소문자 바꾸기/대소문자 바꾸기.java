import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String word = br.readLine();
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<word.length();i++) {
			if(word.charAt(i)>='a' &&word.charAt(i)<='z') {
				sb.append(Character.toUpperCase(word.charAt(i)));
			}
			else {
				sb.append(Character.toLowerCase(word.charAt(i)));
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
