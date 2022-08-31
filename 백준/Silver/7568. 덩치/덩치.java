import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.valueOf(br.readLine());
		List<Person> personList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			long weight = Integer.valueOf(st.nextToken());
			long height = Integer.valueOf(st.nextToken());

			personList.add(new Person(weight, height));
		}
		
		for(int i=0;i<N;i++) {
			int rank=1;
			for(int j=0;j<N;j++) {
				if(j==i)	continue;
				if(personList.get(i).height < personList.get(j).height 
						&& personList.get(i).weight < personList.get(j).weight) {
					rank++;
				}
			}
			System.out.print(rank+" ");
		}
		

		br.close();
	}
}

class Person {
	long weight;
	long height;

	Person(long weight, long height) {
		this.weight = weight;
		this.height = height;
	}
}