import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Person> minpq = new PriorityQueue<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if(o1.birth.isBefore(o2.birth)){
                    return -1;
                }else{
                    return 1;
                }
            }
        });

        PriorityQueue<Person> maxpq = new PriorityQueue<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if(o1.birth.isBefore(o2.birth)){
                    return 1;
                }else{
                    return -1;
                }
            }
        });

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String name = st.nextToken();
            int day = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());

            minpq.add(new Person(name, LocalDate.of(year, month, day)));
            maxpq.add(new Person(name, LocalDate.of(year, month, day)));
        }

        System.out.println(maxpq.poll().name);
        System.out.println(minpq.poll().name);
    }
}

class Person{
    String name;
    LocalDate birth;

    public Person(String name,LocalDate birth){
        this.name = name;
        this.birth = birth;
    }
}