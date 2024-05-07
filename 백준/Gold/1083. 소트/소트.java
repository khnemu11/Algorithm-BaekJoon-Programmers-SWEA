import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int S = Integer.parseInt(br.readLine());
        int changeIdx = 0;

        while(S>0 && changeIdx < num.length-1){
            PriorityQueue<Num> pq = new PriorityQueue<>((a,b)->b.value - a.value);

            for(int i=changeIdx+1;i<num.length;i++){
                pq.add(new Num(num[i],i));
            }

            while(!pq.isEmpty()){
                Num curr = pq.poll();

                if(num[changeIdx] >= curr.value){
                    continue;
                }
                if(curr.idx - changeIdx > S){
                    continue;
                }


                for(int i=curr.idx;i>changeIdx;i--){
                    num[i] = num[i-1];
                }

                num[changeIdx] = curr.value;
                S-= (curr.idx - changeIdx);

                break;
            }
            changeIdx++;
        }

        System.out.println(Arrays.stream(num).mapToObj(String::valueOf).collect(Collectors.joining(" ","","")));
    }
}

class Num{
    int value;
    int idx;

    public Num(int value, int idx){
        this.value = value;
        this.idx = idx;
    }
}