import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Integer> tournament = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            tournament.add(Integer.parseInt(st.nextToken()));
        }

        long sum = 0;
        int maxTarget = N;

        while(tournament.size() > 1){
            int idx = tournament.indexOf(maxTarget);

            if(idx == 0){
                sum += Math.abs(tournament.get(idx) - tournament.get(idx+1));
            }else if(idx == tournament.size()-1){
                sum += Math.abs(tournament.get(idx) - tournament.get(idx-1));
            }else{
                sum += Math.min(Math.abs(tournament.get(idx) - tournament.get(idx-1)),Math.abs(tournament.get(idx) - tournament.get(idx+1)));
            }

            tournament.remove(idx);
            maxTarget--;
        }

        System.out.println(sum);
    }
}