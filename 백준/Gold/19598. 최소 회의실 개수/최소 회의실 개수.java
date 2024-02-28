import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Meeting> readyQ = new PriorityQueue<>(new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                if(o1.start == o2.start){
                    return o1.end - o2.end;
                }
                return o1.start - o2.start;
            }
        });

        for(int i=0;i<N;i++){
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            readyQ.add(new Meeting(input[0],input[1]));
        }

        PriorityQueue<Meeting> processQ = new PriorityQueue<>(new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                return o1.end - o2.end;
            }
        });

        int max = 0;

        while(!readyQ.isEmpty()){
            Meeting curr = readyQ.poll();

            while(!processQ.isEmpty() && processQ.peek().end <= curr.start){
                processQ.poll();
            }

            processQ.add(curr);

            max = Math.max(max,processQ.size());
        }

        System.out.println(max);
    }
}
class Meeting{
    int start;
    int end;

    public Meeting(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}