import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Map;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

//        System.out.println(m+" "+n);

        Map<Integer,Integer> pizzaAPeiceCountMap = getPizzaPieceCountMap(m);
        Map<Integer,Integer> pizzaBPeiceCountMap = getPizzaPieceCountMap(n);

//        System.out.println(pizzaAPeiceCountMap);
//        System.out.println(pizzaBPeiceCountMap);

        int count = pizzaBPeiceCountMap.getOrDefault(N,0) + pizzaAPeiceCountMap.getOrDefault(N,0);;

        for(int size : pizzaAPeiceCountMap.keySet()){
            if(size >= N){
                continue;
            }

            count += pizzaAPeiceCountMap.get(size) * pizzaBPeiceCountMap.getOrDefault(N - size,0);
        }

        System.out.println(count);
    }

    public static Map<Integer,Integer> getPizzaPieceCountMap(int totalSize) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] pizza = new int[totalSize*2];
        int sum = 0;
        for(int i=0;i<totalSize;i++){
            int size = Integer.parseInt(br.readLine());
//            System.out.println(size);

            sum+=size;
            pizza[i] = size;
            pizza[i+totalSize] = size;
        }

        Map<Integer,Integer> pizzaPeiceCountMap = new HashMap<>();
        pizzaPeiceCountMap.put(sum,1);

        for(int i=0;i<totalSize;i++){
            sum = 0;
            for(int j=i;j<i+totalSize-1;j++){
//                System.out.println(i+"~"+j);
                sum += pizza[j];
                pizzaPeiceCountMap.put(sum,pizzaPeiceCountMap.getOrDefault(sum,0)+1);
            }
        }

        return pizzaPeiceCountMap;
    }
}