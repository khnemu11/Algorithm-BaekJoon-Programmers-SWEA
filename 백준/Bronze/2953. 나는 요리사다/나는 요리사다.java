import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String []args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int winNum =0,max=0;
		
		for(int i=0;i<5;i++) {
			int score = Arrays.stream(br.readLine().split(" ")).mapToInt(x->Integer.valueOf(x)).sum();
			if(max<score) {
				winNum = i+1;
				max = score;
			}
		}
		
		System.out.println(winNum+" "+max);
	}
}
