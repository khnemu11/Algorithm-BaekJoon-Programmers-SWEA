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
			Map<String,String> basket = new HashMap<>();
			
			basket.put(")", "(");
			basket.put("]", "[");
			
			
			while(true) {
				String inputBasket = br.readLine();
				if(inputBasket.equals(".")) {
					break;
				}
				
				String answer = "yes";
				for(int j=0;j<inputBasket.length();j++) {
					String inputChar = String.valueOf(inputBasket.charAt(j));
					if(inputChar.matches("[a-zA-Z .]")) {
						continue;
					}
					else if(basket.containsKey(inputChar)) {
						if(!stack.isEmpty() && basket.get(inputChar).equals(stack.peek())) {
							stack.pop();
						}
						else {
							answer="no";
							break;
						}
					}
					else {
						stack.push(inputChar);
					}
				}
				if(!stack.isEmpty()) {
					answer="no";
					stack.clear();
				}
				bw.write(answer);
				bw.newLine();
				stack.clear();
			}
			
			bw.flush();
			bw.close();
			br.close();
		}		
}

