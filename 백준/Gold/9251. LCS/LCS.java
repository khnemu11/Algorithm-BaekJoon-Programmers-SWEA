import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String first = br.readLine();
		String second = br.readLine();
		
		int dp [][] = new int [second.length()+1][first.length()+1];
		
		for(int i=0;i<second.length();i++) {
			for(int j=0;j<first.length();j++) {
				if(second.charAt(i) == first.charAt(j)) {
					dp[i+1][j+1] = dp[i][j]+1;
				}
				else {
					dp[i+1][j+1] = Math.max(dp[i+1][j],dp[i][j+1]);
				}
			}
		}
		
		bw.write(String.valueOf(dp[second.length()][first.length()]));
		bw.newLine();
		
		bw.flush();
		bw.close();
		br.close();
	}
}
