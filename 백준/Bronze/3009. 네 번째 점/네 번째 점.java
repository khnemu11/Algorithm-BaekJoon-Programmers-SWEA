import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int[] xArr = new int[1001];
		int[] yArr = new int[1001];
		
		
		
		for(int i=0;i<3;i++) {
			st = new StringTokenizer(br.readLine());
			int inputX=Integer.valueOf(st.nextToken());
			int inputY = Integer.valueOf(st.nextToken());
			
			xArr[inputX]++;
			yArr[inputY]++;
		}		
		
		int x=0,y=0;
		
		for(int i=0;i<1001;i++) {
			if(x>0&&y>0)	break;
			if(xArr[i]==1) {
				x=i;
			}
			if(yArr[i]==1) {
				y=i;
			}
		}
		bw.write(String.valueOf(x));
		bw.write(" ");
		bw.write(String.valueOf(y));
		bw.newLine();
		
		bw.flush();
		br.close();
		bw.close();
	}
}