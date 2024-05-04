import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        PriorityQueue<Integer> negativePQ = new PriorityQueue<>();
        PriorityQueue<Integer> positivePQ = new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0;i<N;i++){
            int location = Integer.parseInt(st.nextToken());

            if(location < 0 ){
                negativePQ.add(location);
            }else{
                positivePQ.add(location);
            }
        }

        int negativeMax = negativePQ.isEmpty() ? 0 : Math.abs(negativePQ.peek());
        int positiveMax = positivePQ.isEmpty() ? 0 : positivePQ.peek();
        
        int sum = 0;
        
        //다시 돌아오지 않아도 되는 가장 멀리 있는 책 위치 구하기
        if(positiveMax > negativeMax){
            sum += positivePQ.peek();

            int count = M;

            while(count-- >0 && !positivePQ.isEmpty()){
                positivePQ.poll();
            }
        }else{
            sum += Math.abs(negativePQ.peek());

            int count = M;

            while(count-- >0 && !negativePQ.isEmpty()){
                negativePQ.poll();
            }
        }

        //음수 처리
        while(!negativePQ.isEmpty()){
            sum += Math.abs(negativePQ.peek())*2;

            int count = M;

            while(count-- >0 && !negativePQ.isEmpty()){
                negativePQ.poll();
            }
        }

        //양수 처리
        while(!positivePQ.isEmpty()){
            sum += positivePQ.peek()*2;

            int count = M;

            while(count-- >0 && !positivePQ.isEmpty()){
                positivePQ.poll();
            }
        }

        System.out.println(sum);
    }
}