import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int count =0;
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.valueOf(br.readLine());

		for (int i = 0; i < T; i++) {
			String word = br.readLine();
			int result = recursion(word.toCharArray(),0,word.length()-1);
			StringBuilder sb = new StringBuilder();
			sb.append(String.valueOf(result));
			sb.append(" ");
			sb.append(String.valueOf(count));
			
			bw.write(sb.toString());
			bw.newLine();
			
			count=0;
		}
		bw.flush();
		br.close();
		bw.close();
	}
	public static int recursion(char[]s, int l, int r){
		count++;
	    if(l >= r) return 1;
	    else if(s[l] != s[r]) return 0;
	    else return recursion(s, l+1, r-1);
	}

}