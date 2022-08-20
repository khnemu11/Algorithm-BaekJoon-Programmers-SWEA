import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String []args) throws IOException{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] dices = Arrays.stream(br.readLine().split(" ")).mapToInt(x->Integer.valueOf(x)).toArray();
		int winRate = 0;
		
		
		if(dices[0] == dices[1] && dices[1]==dices[2]) {
			winRate = 10000+dices[0]*1000;
		}
		
		else if(dices[0] == dices[1]) {
			winRate = 1000+dices[0]*100;
		}
		else if(dices[2] == dices[1]) {
			winRate = 1000+dices[1]*100;
		}
		else if(dices[0] == dices[2]) {
			winRate = 1000+dices[0]*100;
		}
		else{
			winRate = Arrays.stream(dices).max().getAsInt()*100;
		} 
		
		System.out.println(winRate);
	}
}
