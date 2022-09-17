import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int num = Integer.valueOf(br.readLine());
		
		for(int i=0;i<num/4;i++) {
			sb.append("long ");
		}
		if(num%4 >0) {
			sb.append("long ");
		}
		
		sb.append("int");
	
		bw.write(sb.toString());
		bw.newLine();
		
		bw.flush();
		
		br.close();
		bw.close();
	}
}
