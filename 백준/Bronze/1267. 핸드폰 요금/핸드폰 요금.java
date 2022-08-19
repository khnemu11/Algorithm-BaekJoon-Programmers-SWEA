import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int total = Integer.valueOf(br.readLine());
		int y=0,m=0;
		int []time = Arrays.stream(br.readLine().split(" ")).mapToInt(n -> Integer.parseInt(n)).toArray();
		
		for(int i=0;i<total;i++) {
			y+=time[i]/30*10 + 10;
			m+=time[i]/60*15 + 15;
		}
		if(y<m) System.out.println("Y "+y);
		else if(y>m) System.out.println("M "+m);
		else System.out.println("Y M "+y);
	}
}
