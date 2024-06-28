import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] sushi = new int[N*2];

        for(int i=0;i<N;i++){
            int val = Integer.parseInt(br.readLine());
            sushi[i] = val;
            sushi[i+N] = val;
        }

        Map<Integer,Integer> selectMap = new HashMap<>();

        for(int i=0;i<k;i++){
            selectMap.put(sushi[i],selectMap.getOrDefault(sushi[i],0)+1);
        }

        int max = selectMap.size() + (selectMap.getOrDefault(c,0) > 0 ? 0:1);

        for(int i=1;i<N;i++){
            selectMap.put(sushi[i-1],selectMap.get(sushi[i-1])-1);

            if(selectMap.get(sushi[i-1]) == 0){
                selectMap.remove(sushi[i-1]);
            }

            selectMap.put(sushi[i+k-1],selectMap.getOrDefault(sushi[i+k-1],0)+1);

            max = Math.max(max, selectMap.size() + (selectMap.getOrDefault(c,0) > 0 ? 0:1));
        }

        System.out.println(max);
    }
}