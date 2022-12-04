import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	static int timeArr[];
	static ArrayList<ArrayList<Integer>> buildings;
	static int timeSum[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());

		for (int test_case = 0; test_case < T; test_case++) {
			int row[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			int num = row[0];
			int rules = row[1];
			timeArr = new int[num + 1];
			timeSum = new int[num + 1];
			Arrays.fill(timeSum, -1);
			row = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			for (int i = 0; i < row.length; i++) {
				timeArr[i + 1] = row[i];
			}

			buildings = new ArrayList<>();
			for (int i = 0; i <= num; i++) {
				buildings.add(new ArrayList<Integer>());
			}

			for (int i = 0; i < rules; i++) {
				row = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
				buildings.get(row[1]).add(row[0]);
			}
//			System.out.println(buildings.toString());
//			System.out.println(Arrays.toString(timeArr));
			int goal = Integer.valueOf(br.readLine());
			int result = buildingTime(goal);

			bw.write(String.valueOf(result));
			bw.newLine();
		}

		bw.flush();
	}

	public static int buildingTime(int curr) {
		if (timeSum[curr] > -1) {
			return timeSum[curr];
		} else if (buildings.get(curr).isEmpty()) {
			return timeArr[curr];
		} else {
			int max = 0;

			for (int prev : buildings.get(curr)) {
				int time = buildingTime(prev);
//				System.out.println("prev : " + prev + "'s time : " + time);
//				System.out.println("curr : " + curr + "'s time : " + timeArr[curr]);
				max = Math.max(timeArr[curr] + time, max);
			}
			timeSum[curr] = max;
			return timeSum[curr];
		}
	}
}
