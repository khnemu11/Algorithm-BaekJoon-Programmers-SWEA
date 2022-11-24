import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int peopleN = Integer.valueOf(st.nextToken());
		int partyN = Integer.valueOf(st.nextToken());

		HashSet<Integer> truth = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		int truthN = Integer.valueOf(st.nextToken());
		boolean people[][] = new boolean[peopleN + 1][peopleN + 1];

		for (int i = 0; i < truthN; i++) {
			truth.add(Integer.valueOf(st.nextToken()));
		}

		ArrayList<ArrayList<Integer>> party = new ArrayList<>();

		for (int i = 0; i < partyN; i++) {
			party.add(new ArrayList<Integer>());

			int row[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			for (int k = 1; k < row.length; k++) {
				party.get(i).add(row[k]);
			}

			for (int start = 0; start < party.get(i).size(); start++) {
				for (int end = start + 1; end < party.get(i).size(); end++) {
					people[party.get(i).get(start)][party.get(i).get(end)] = true;
					people[party.get(i).get(end)][party.get(i).get(start)] = true;
				}
			}
		}

		Iterator<Integer> it = truth.iterator();

		Queue<Integer> queue = new LinkedList<>();
		while (it.hasNext()) {
			int start = it.next();

			queue.add(start);
		}

		boolean visited[] = new boolean[peopleN + 1];

		while (!queue.isEmpty()) {
			int curr = queue.poll();
			visited[curr] = true;
			for (int i = 1; i < people.length; i++) {
				if (visited[i]) {
					continue;
				}

				if (people[curr][i]) {
					truth.add(i);
					queue.add(i);
				}
			}
		}
//		System.out.println(Arrays.deepToString(people));
//		System.out.println(party.toString());
//		System.out.println(truth.toString());

		int count = 0;
		for (int i = 0; i < partyN; i++) {
			boolean isValid = true;
			for (int k = 0; k < party.get(i).size(); k++) {
				if (truth.contains(party.get(i).get(k))) {
					isValid = false;
					break;
				}
			}

			if (isValid) {
				count++;
			}
		}
		bw.write(String.valueOf(count));
		bw.flush();
	}

}
