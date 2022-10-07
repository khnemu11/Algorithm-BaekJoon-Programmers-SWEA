import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		char words[] = br.readLine().toCharArray();
		String result = "";
		boolean isTag = false;
		StringBuilder sb = new StringBuilder(result);
		StringBuilder subWord = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			if (words[i] == '<') {
				sb.append(subWord.reverse());
				subWord.setLength(0);
				isTag = true;
			} else if (words[i] == '>') {
				sb.append(words[i]);
				isTag = false;
				continue;
			}
			if (words[i] == ' ' && isTag == false) {
				sb.append(subWord.reverse());
				subWord.setLength(0);
				sb.append(words[i]);
			}
			else if(isTag == false){
				subWord.append(words[i]);
			}
			else {
				sb.append(words[i]);
			}
			
		}
		sb.append(subWord.reverse());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
