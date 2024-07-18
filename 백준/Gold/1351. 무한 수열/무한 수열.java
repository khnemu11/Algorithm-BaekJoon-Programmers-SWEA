import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static Map<Long,Long> aMap = new HashMap<>();
    private static long N;
    private static int P,Q;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        aMap.put(0L,1L);

        System.out.println(getASequence(N));
    }

    public static long getASequence(long i){
        if(aMap.get(i) != null){
            return aMap.get(i);
        }

        Long aip = aMap.get(i/P);

        if(aip == null){
            aip = getASequence(i/P);
        }

        Long aiq = aMap.get(i/Q);

        if(aiq == null){
            aiq = getASequence(i/Q);
        }

        aMap.put(i,aip+aiq);

        return aMap.get(i);
    }
}