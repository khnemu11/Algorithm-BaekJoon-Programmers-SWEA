import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 풀이 알고리즘
 * 
 * 시간 순서대로 시계열 그리기
 * 
 * EX)
 * 3 -> 4  : 20
 * 1 -> 2  : 10
 * 1 -> 3  : 20
 * 1 -> 4  : 30
 * 2 -> 3  : 10
 * 2 -> 4  : 20
 * 
 * 
 * 
 * 1	2	3	4	5
 * 			(20 )
 * ( 10 )
 * ( 	20  )
 * ( 	30      )
 * 		( 10)
 * 		( 	20	)
 * 
 * 
 * 가장 먼저 도착하는 택배 > 가장 먼저 시작하는 택배를 기준으로 정렬
 * 
 * 1) 1->2 10			가능한 최대 택배 크기 = MIN(10, 40) = 10
 * 1	2	3	4	5
 * 10	0	0	0	0
 * 
 * 2) 1->3 20			가능한 최대 택배 크기 = MIN(20, 40-10, 40-20) = 20
 * 1	2	3	4	5
 * 30	20	0	0	0
 * 
 * 3) 1->4 30			가능한 최대 택배 크기 = MIN(30, 40-30, 40-20) = 10
 * 1	2	3	4	5
 * 40	30	10	0	0
 * 
 * 4) 2->3 10			가능한 최대 택배 크기 = MIN(10, 40-30) = 10
 * 1	2	3	4	5
 * 40	40	10	0	0
 * 
 * 5) 2->4 20			가능한 최대 택배 크기 = MIN(20, 40-40, 40-10) = 0
 * 1	2	3	4	5
 * 40	40  10	0	0
 * 
 * 6) 3->4 20			가능한 최대 택배 크기 = MIN(20, 40-10) = 20
 * 1	2	3	4	5
 * 40	40	30	0	0
 * 
 * 10+20+10+10+20 = 40 
 * 
 * */

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken());
		int maxPossible = Integer.valueOf(st.nextToken());
		int weights[] = new int[N + 1];
		int M = Integer.valueOf(br.readLine());

		Box boxes[] = new Box[M];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.valueOf(st.nextToken());
			int end = Integer.valueOf(st.nextToken());
			int weight = Integer.valueOf(st.nextToken());

			boxes[i] = new Box(start, end, weight);
		}

		Arrays.sort(boxes);
		int total = 0;
		for (int i = 0; i < boxes.length; i++) {
			Box box = boxes[i];

			int max = 0;

			for (int from = box.start; from < box.end; from++) {
				max = Math.max(max, weights[from]);
			}

			int possible = Math.min(box.weight, maxPossible - max);

			total += possible;

			for (int from = box.start; from < box.end; from++) {
				weights[from] = weights[from] + possible;
			}
		}

		bw.write((weights[N] + total) + "\n");
		bw.flush();
	}
}

class Box implements Comparable<Box> {
	int start;
	int end;
	int weight;

	public Box(int start, int end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Box o) {
		if (this.end == o.end) {
			return this.start - o.start;
		}
		return this.end - o.end;
	}
}