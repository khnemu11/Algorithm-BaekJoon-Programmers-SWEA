import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
		public static void main(String []args) throws Exception{	
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			Stack<Integer> stack = new Stack<>();
			
			int N = Integer.valueOf(br.readLine());
			
			for(int i=0;i<N;i++) {
				int num = Integer.valueOf(br.readLine());
				
				if(num==0) {
					if(!stack.isEmpty()) {
						stack.pop();
					}
				}
				else {
					stack.push(num);
				}
			}
			
			int total=0;
			while(stack.size()>0) {
				total+=stack.pop();
			}
			
			bw.write(String.valueOf(total));
			
			bw.flush();
			bw.close();
			br.close();
		}		
}

