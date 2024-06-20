import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
/*

 1              1
 1 2 1          4
 1 2 3 2 1      9
 1 2 3 4 3 2 1  16


*/
public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();
        List<Integer> sorted = new ArrayList<>();

        for(int i=0;i<N;i++){
            int num = Integer.parseInt(br.readLine());
            list.add(num);
            sorted.add(num);
        }

        Collections.sort(sorted);

        int offset = 1000;
        int[]next = new int[2001];
        int[]prev = new int[2001];

        Arrays.fill(next,Integer.MAX_VALUE);
        Arrays.fill(prev,Integer.MAX_VALUE);

        for(int i=0;i<sorted.size();i++){
            if(i+1 < sorted.size()){
                next[sorted.get(i)+offset] = sorted.get(i+1);
            }
            if(i-1 >= 0){
                prev[sorted.get(i)+offset] = sorted.get(i-1);
            }
        }

        List<Deque<Integer>> dequeList = new ArrayList<>();

        for(int i=0;i<list.size();i++){
            boolean valid = false;

            for(Deque<Integer> deque : dequeList){
                if(deque.peekFirst() == next[list.get(i)+offset]){
//                    System.out.println(list.get(i)+" -> "+next[list.get(i)+offset]);
                    deque.addFirst(list.get(i));
                    valid=true;
                    break;
                }else if(deque.peekLast() == prev[list.get(i)+offset]){
//                    System.out.println(prev[list.get(i)+offset]+" -> "+list.get(i));
                    deque.addLast(list.get(i));
                    valid=true;
                    break;
                }
            }

            if(!valid){
                Deque deque = new ArrayDeque();
                deque.add(list.get(i));
                dequeList.add(deque);
            }
//
//            System.out.println(dequeList);
        }

        System.out.println(dequeList.size());
    }
}
