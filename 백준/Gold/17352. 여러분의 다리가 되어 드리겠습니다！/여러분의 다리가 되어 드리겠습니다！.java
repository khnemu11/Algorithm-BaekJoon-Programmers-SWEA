import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] parents;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        parents = new int[N+1];

        //부모 초기화
        for(int i=1;i<=N;i++){
            parents[i] = i;
        }

        //부모 연결
        for(int i=0;i<N-2;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            union(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        Set<Integer> islandSet = new HashSet<>();

        for(int i=1;i<=N;i++){
            islandSet.add(getParent(i));
        }

        StringBuilder sb = new StringBuilder();

        for(int island : islandSet){
            sb.append(island).append(" ");
        }

        sb.deleteCharAt(sb.length()-1);

        System.out.println(sb);
    }

    public static int getParent(int a){
        if(parents[a] == a){
            return parents[a];
        }

        parents[a] = getParent(parents[a]);

        return parents[a];
    }

    public static void union(int a, int b){
        int pa = getParent(a);
        int pb = getParent(b);


        if(pa < pb){
            parents[pb] = pa;
        }else{
            parents[pa] = pb;
        }
    }
}