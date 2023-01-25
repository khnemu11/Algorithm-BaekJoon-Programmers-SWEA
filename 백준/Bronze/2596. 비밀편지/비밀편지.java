	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	
	public class Main {
	
		public static void main(String[] args) throws NumberFormatException, IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int n = Integer.valueOf(br.readLine());
			String encrypt = br.readLine();
			int curr = 0;
			int length = 6;
			String decrypts[] = { "000000", "001111", "010011", "011100", "100110", "101001", "110101", "111010" };
			StringBuilder result = new StringBuilder();
			boolean isValid = false;
			
			while (curr < encrypt.length()) {
				isValid = false;
	
				for (int j = 0; j < decrypts.length; j++) {
					int differCount = 0;
					for (int i = 0; i < length; i++) {
						if (encrypt.charAt(curr + i) != decrypts[j].charAt(i)) {
							differCount++;
							if (differCount > 1) {
								break;
							}
						}
					}
	
					if (differCount < 2) {
						result.append((char) (j + 'A'));
						isValid = true;
						break;
					}
				}
	
				if (!isValid) {
					System.out.println(result.length() + 1);
					break;
				}
				curr += length;
			}
	
			if (isValid) {
				System.out.println(result.toString());
			}
		}
	}