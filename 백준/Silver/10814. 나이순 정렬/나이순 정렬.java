import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Person> personList = new ArrayList<Person>();
		
		int n = Integer.valueOf(br.readLine());
		
		for(int i=0;i<n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int age = Integer.valueOf(st.nextToken());
			String name = st.nextToken();	
			
			Person person = new Person(i,age,name);
			
			personList.add(person);
		}
		
		Collections.sort(personList);
		
		for(Person p : personList) {
			System.out.println(p.toString());
		}

		br.close();
	}
}

class Person implements Comparable<Person>{
	int seq;
	int age;
	String name;
	
	Person(int seq,int age, String name){
		this.seq = seq;
		this.age = age;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return age + " "+name;
	}

	@Override
	public int compareTo(Person o) {
		return this.age-o.age;
	}
}