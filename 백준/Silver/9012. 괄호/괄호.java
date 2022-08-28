import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {
		public static void main(String []args) throws Exception{	
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			Stack<String> stack = new Stack<>();
			Map<String,String> basket = new HashMap<>() {{
				put(")", "(");
				put("}", "{");
				put("]", "[");
			}};
			
			int N = Integer.valueOf(br.readLine());
			
			for(int i=0;i<N;i++) {
				String inputBasket = br.readLine();
				String answer = "YES";
				for(int j=0;j<inputBasket.length();j++) {
					String inputChar = String.valueOf(inputBasket.charAt(j));
					if(basket.containsKey(inputChar)) {
						if(!stack.isEmpty() && basket.get(inputChar).equals(stack.peek())) {
							stack.pop();
						}
						else {
							answer="NO";
							break;
						}
					}
					else {
						stack.push(inputChar);
					}
				}
				if(!stack.isEmpty()) {
					answer="NO";
					stack.clear();
				}
				bw.write(answer);
				bw.newLine();
			}
			
			bw.flush();
			bw.close();
			br.close();
		}		
}

