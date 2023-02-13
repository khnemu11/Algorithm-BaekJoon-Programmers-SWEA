import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

/*
	풀이 알고리즘
	뒤에서 부터 하나씩 길이를 늘려가며 map으로 중복되는것이 있는지 확인하여 풀이
*/

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.valueOf(br.readLine());

		String id[] = new String[N];

		for (int i = 0; i < id.length; i++) {
			id[i] = br.readLine();
		}

		int len = 1;
		for (int i = id[0].length() - 1; i >= 0; i--) {
			HashMap<String, Boolean> map = new HashMap<>();
			boolean isValid = true;
			for (int j = 0; j < id.length; j++) {
				if (map.get(id[j].substring(i, id[0].length())) != null) {
					isValid = false;
					break;
				} else {
					map.put(id[j].substring(i, id[0].length()), true);
				}
			}
			if (isValid) {
				break;
			}
			len++;
		}
		bw.write(len + "\n");
		bw.flush();
	}

}
