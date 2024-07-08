import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Problem> largestPQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Problem> smallestPQ = new PriorityQueue<>();

        int[] levels = new int[100_001];

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            levels[num] = level;

            Problem problem = new Problem(num,level);

            largestPQ.add(problem);
            smallestPQ.add(problem);
        }

        Set<String> solveSet = new HashSet<>();

        int M = Integer.parseInt(br.readLine());

        for(int i=0;i<M;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if(command.equals("add")){
                int num = Integer.parseInt(st.nextToken());
                int level = Integer.parseInt(st.nextToken());

                levels[num] = level;
                Problem problem = new Problem(num,level);

                largestPQ.add(problem);
                smallestPQ.add(problem);
            }else if(command.equals("recommend")){
                int val = Integer.parseInt(st.nextToken());

                if(val == 1){
                    String key = largestPQ.peek().num +"-"+largestPQ.peek().level;
                    while(solveSet.contains(key)){
                        largestPQ.poll();
                        key = largestPQ.peek().num +"-"+largestPQ.peek().level;
                    }
                    System.out.println(largestPQ.peek().num);
                }else{
                    String key = smallestPQ.peek().num +"-"+smallestPQ.peek().level;
                    while(solveSet.contains(key)){
                        smallestPQ.poll();
                        key = smallestPQ.peek().num +"-"+smallestPQ.peek().level;
                    }
                    System.out.println(smallestPQ.peek().num);
                }
            }else{
                int num = Integer.parseInt(st.nextToken());

                solveSet.add(num+"-"+levels[num]);
            }
        }
    }
}

class Problem implements Comparable<Problem>{
    int num;
    int level;

    public Problem(int num, int level){
        this.num = num;
        this.level = level;
    }

    @Override
    public int compareTo(Problem o) {
        if(this.level == o.level){
            return this.num - o.num;
        }
        return this.level - o.level;
    }
}