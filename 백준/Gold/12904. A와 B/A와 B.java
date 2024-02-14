import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	public static void main(String[] agrs) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String start = br.readLine();
		StringBuilder target = new StringBuilder(br.readLine());
		
		while(target.length() > start.length()) {
			if(target.charAt(target.length()-1) =='A') {
				target = target.deleteCharAt(target.length()-1);
			}else{
				target = target.deleteCharAt(target.length()-1).reverse();
			}
		}
		
		System.out.println(target.toString().equals(start) ? 1 : 0);
	}
}