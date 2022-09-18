import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static Character answer[][];
	static Character input[][];
	static Character curr[][];
	static boolean visited[][];
	static int inputCount;
	static Stack<Integer> startX = new Stack<>();
	static Stack<Integer> startY = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String input = "";
		
		while(true) {
			input=br.readLine().toLowerCase();
			if(input.equals("#")) {
				break;
			}
			int count =0;
			
			for(int i=0;i<input.length();i++) {
				if(input.charAt(i) == 'a'||input.charAt(i) == 'e'||input.charAt(i) == 'i' || input.charAt(i) == 'o'||input.charAt(i) == 'u') {
					count++;
				}
			}
			bw.write(String.valueOf(count));
			bw.newLine();
		}
		bw.flush();
		br.close();
		bw.close();
	}
}
