import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N =Integer.parseInt(br.readLine());
        parent = new int[N];

        List<Flow> list = new ArrayList<>();

        for(int i=0;i<N;i++){
            parent[i] = i;
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for(int j=i+1;j<input.length;j++){
                list.add(new Flow(i,j,input[j]));
            }
        }

        Collections.sort(list);
        long sum = 0;

        for(Flow curr : list){
            int pa = getParent(curr.start);
            int pb = getParent(curr.end);

            if( pa == pb ){
                continue;
            }

            sum = sum + curr.distance;
            setParent(pa, pb);
        }

        System.out.println(sum);
    }

    public static int getParent(int i){
        if(parent[i] == i){
            return parent[i];
        }
        return parent[i] = getParent(parent[i]);
    }

    public static void setParent(int pa, int pb){
        if(pa < pb){
            parent[pb] = pa;
        }else{
            parent[pa] = pb;
        }
    }
}

class Flow implements Comparable<Flow>{
    int start;
    int end;
    int distance;

    public Flow(int start, int end, int distance){
        this.start= start;
        this.end = end;
        this.distance = distance;
    }

    @Override
    public int compareTo(Flow o) {
        return this.distance - o.distance;
    }
}