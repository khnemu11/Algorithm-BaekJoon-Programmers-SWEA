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
		int size = Integer.valueOf(br.readLine());
		Score[] scores = new Score[size];

		for (int i = 0; i < size; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			scores[i] = new Score(st.nextToken(), Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()),
					Integer.valueOf(st.nextToken()));
		}

		Arrays.sort(scores);

		for (int i = 0; i < size; i++) {
			bw.write(scores[i].name);
			bw.newLine();
		}

		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}

class Score implements Comparable<Score> {
	String name;
	int kor;
	int eng;
	int math;

	public Score(String name, int kor, int eng, int math) {
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}

	@Override
	public int compareTo(Score o) {
		if (this.kor == o.kor) {
			if (this.eng == o.eng) {
				if (this.math == o.math) {
					return this.name.compareTo(o.name);
				}
				return o.math - this.math;
			}
			return this.eng - o.eng;
		}
		return o.kor - this.kor;
	}

}