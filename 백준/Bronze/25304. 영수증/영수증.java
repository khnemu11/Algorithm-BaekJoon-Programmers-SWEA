import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String []args) throws IOException{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int total = Integer.valueOf(br.readLine());
		int count = Integer.valueOf(br.readLine());
		int check =0;
		
		for(int i=0;i<count;i++) {
			int [] stuff = Arrays.stream(br.readLine().split(" ")).mapToInt(n->Integer.valueOf(n)).toArray();
			check+=stuff[0]*stuff[1];
		}
	
		if(check==total)	System.out.println("Yes");
		else	System.out.println("No");
		
	}
}