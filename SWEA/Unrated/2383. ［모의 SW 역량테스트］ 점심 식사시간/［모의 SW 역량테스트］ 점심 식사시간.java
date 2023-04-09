import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static ArrayList<Person> personList;
	static ArrayList<Stair> stairList;
	static ArrayList<ArrayList<Person>> candidatesList;
	static int minTime;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.valueOf(br.readLine());
			int graph[][] = new int[N][N];

			personList = new ArrayList<>();
			stairList = new ArrayList<>();
			candidatesList = new ArrayList<>();
			minTime = Integer.MAX_VALUE;

			for (int i = 0; i < 2; i++) {
				candidatesList.add(new ArrayList<>());
			}

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					graph[i][j] = Integer.valueOf(st.nextToken());
					if (graph[i][j] == 1) {
						personList.add(new Person(i, j));
					} else if (graph[i][j] > 1) {
						stairList.add(new Stair(i, j, graph[i][j]));
					}
				}
			}

//			System.out.println(stairList);
			select(0);
			bw.write("#" + tc + " " + (minTime + 1) + "\n");
		}

		bw.flush();
	}

	public static void select(int idx) {
		if (idx >= personList.size()) {
//			System.out.println("선택  끝");
			int time1 = getTime(0);
			int time2 = getTime(1);
//			System.out.println(time1 + " vs " + time2);
			int time = Math.max(time1, time2);
			minTime = Math.min(minTime, time);
//			System.out.println("minTime : " + minTime);
		} else {
			for (int i = 0; i < 2; i++) {
				personList.get(idx).time = Math.abs(personList.get(idx).row - stairList.get(i).row)
						+ Math.abs(personList.get(idx).col - stairList.get(i).col);
				candidatesList.get(i).add(personList.get(idx));
				select(idx + 1);
				candidatesList.get(i).remove(candidatesList.get(i).size() - 1);
				personList.get(idx).time = 0;
			}
		}
	}

	public static int getTime(int idx) {
		int time = 0;

		Queue<Person> pList = new LinkedList<>();

		for (Person p : candidatesList.get(idx)) {
			pList.add(new Person(p.row, p.col, p.time));
		}
//		System.out.println(pList + " to " + stairList.get(idx));
		Queue<Person> inStair = new LinkedList<>();

		while (!pList.isEmpty()) {
			time++;
			int loop = inStair.size();

			while (loop-- > 0) {
				Person p = inStair.poll();
				p.time++;
				if (p.time < stairList.get(idx).val) {
					inStair.add(p);
				}
			}
			loop = pList.size();

			while (loop-- > 0) {
				Person p = pList.poll();
				if (p.time > 0) {
					p.time--;
				}

				if (p.time == 0 && inStair.size() < 3) {
					inStair.add(p);
				} else {
					pList.add(p);
				}

			}
//			System.out.println("time : " + time);
//			System.out.println("대기 사람 : " + pList);
//			System.out.println("계단 안 : " + inStair);
		}

		while (!inStair.isEmpty()) {
			int loop = inStair.size();
			time++;
			while (loop-- > 0) {
				Person p = inStair.poll();
				p.time++;
				if (p.time != stairList.get(idx).val) {
					inStair.add(p);
				}
			}

//			System.out.println("time : " + time);
//			System.out.println("대기 사람 : " + pList);
//			System.out.println("계단 안 : " + inStair);
		}

		return time;
	}
}

class Person extends Coordinate implements Comparable<Person> {
	int time;

	public Person(int row, int col) {
		super(row, col);
	}

	public Person(int row, int col, int time) {
		super(row, col);
		this.time = time;
	}

	@Override
	public String toString() {
		return "Person [time=" + time + ", row=" + row + ", col=" + col + "]";
	}

	@Override
	public int compareTo(Person o) {
		return this.time - o.time;
	}

}

class Coordinate {
	int row;
	int col;

	@Override
	public String toString() {
		return "Coordinate [row=" + row + ", col=" + col + "]";
	}

	public Coordinate() {
	}

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

class Stair extends Coordinate {
	int val;

	@Override
	public String toString() {
		return "Coordinate [row=" + row + ", col=" + col + "]";
	}

	public Stair() {
	}

	public Stair(int row, int col, int val) {
		super(row, col);
		this.val = val;
	}
}