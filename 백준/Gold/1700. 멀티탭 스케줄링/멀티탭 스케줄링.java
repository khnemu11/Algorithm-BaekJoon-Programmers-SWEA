import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.PlatformLoggingMXBean;
import java.util.*;

public class Main {
    final static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        int[]input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0];
        int K = input[1];

        int[] appliances = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Set<Integer> plug = new HashSet<>();

        int count = 0;

        for(int i=0;i<appliances.length;i++){
            if(plug.size() < N){
                plug.add(appliances[i]);
            }else if(plug.contains(appliances[i])){
                continue;
            }else{
                PriorityQueue<Plug> pq = new PriorityQueue<>();

                for(int p : plug){
                    Plug next = new Plug(Integer.MAX_VALUE,p);

                    for(int j=i+1;j<appliances.length;j++){
                        if(appliances[j] == next.type){
                            next.idx = j;
                            break;
                        }
                    }

                    pq.add(next);
                }

                Plug target = pq.poll();

                plug.remove(target.type);
                plug.add(appliances[i]);
                count++;
            }
        }

        System.out.println(count);
    }
}

class Plug implements Comparable<Plug> {
    int idx;
    int type;

    public Plug(int idx, int type){
        this.idx = idx;
        this.type = type;
    }

    @Override
    public int compareTo(Plug o) {
        return o.idx -this.idx;
    }
}