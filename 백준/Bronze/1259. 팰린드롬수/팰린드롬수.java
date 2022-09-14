import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while(true) {
			String num = br.readLine();
			if(num.equals("0")) {
				break;
			}
			
			int l = 0;
			int r = num.length()-1;
			boolean palindrom = true;
			while(l<r) {
				if(num.charAt(l) != num.charAt(r)) {
					palindrom = false;
					break;
				}
				l++;
				r--;
			}
			
			if(palindrom) {
				bw.write("yes");
			}
			else {
				bw.write("no");
			}
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
