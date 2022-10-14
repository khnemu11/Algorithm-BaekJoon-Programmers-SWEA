import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		List<Integer> list = new ArrayList<>();

		int size = Integer.valueOf(br.readLine());

		int nums[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		boolean isvalid=false;
		list.add(nums[size - 1]);

		for (int i = size - 2; i >= 0; i--) {
			list.add(nums[i]);
			Collections.sort(list);
			if (list.get(list.size() - 1) > nums[i]) {
				isvalid=true;
				int changeIndex = i;
				for (int j = 0; j < list.size(); j++) {
					if (list.get(j) > nums[i]) {
						changeIndex = j;
						break;
					}
				}

				nums[i] = list.get(changeIndex);
				list.remove(changeIndex);
				for (int j = i + 1; j < size; j++) {
					nums[j] = list.get(j - (i + 1));
				}
				break;
			}
		}
		if (isvalid) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < size; i++) {
				sb.append(nums[i]);
				sb.append(" ");
			}
			bw.write(sb.toString());
		} else {
			bw.write("-1");
		}
		bw.newLine();

		bw.flush();
		bw.close();
		br.close();
	}
}