import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String []args) throws IOException{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[]input = br.readLine().trim().split(" ");
		
		int a = Integer.valueOf((new StringBuilder(input[0])).reverse().toString());
		int b = Integer.valueOf((new StringBuilder(input[1])).reverse().toString());
		
		if(a>b)	System.out.println(a);
		else	System.out.println(b);
	}
}
