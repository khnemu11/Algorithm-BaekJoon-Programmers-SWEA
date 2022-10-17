import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken());
		int k = Integer.valueOf(st.nextToken());

		Score[] scores = new Score[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			scores[i] = new Score(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()),
					Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));
		}
		Arrays.sort(scores);

		int grade = 1;
		Score curr = scores[0];

		for (int i = 1; i <= N; i++) {
			if (curr.seq == k) {
				bw.write(String.valueOf(grade));
				bw.newLine();
				break;
			}
			
			curr = scores[i];

			if (curr.compareTo(scores[i - 1]) != 0) {
				grade = i + 1;
			}

		}
		
		bw.flush();
		br.close();
		bw.close();
	}
}

class Score implements Comparable<Score> {
	int seq;
	int gold;
	int silver;
	int bronze;

	public Score(int seq, int gold, int silver, int bronze) {
		this.seq = seq;
		this.gold = gold;
		this.silver = silver;
		this.bronze = bronze;
	}

	@Override
	public int compareTo(Score o) {
		if (this.gold == o.gold) {
			if (this.silver == o.silver) {
				return o.bronze - this.bronze;
			} else {
				return o.silver - this.silver;
			}
		} else {
			return o.gold - this.gold;
		}
	}
}