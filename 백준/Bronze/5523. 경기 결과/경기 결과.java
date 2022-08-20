import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String []args) throws IOException{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.valueOf(br.readLine());
		int aWin=0,bWin=0;
		
		for(int i=0;i<N;i++) {
			int [] score = Arrays.stream(br.readLine().split(" ")).mapToInt(x-> Integer.valueOf(x)).toArray();
			if(score[0]<score[1]) {
				bWin++;
			}
			else if(score[0]>score[1]) {
				aWin++;
			}
		}
		
		
		System.out.println(aWin+" "+bWin);
		 
		 br.close();
		 
	}
}
