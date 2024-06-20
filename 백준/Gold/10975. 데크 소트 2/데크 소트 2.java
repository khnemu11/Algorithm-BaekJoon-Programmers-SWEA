import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

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

        int offset = 1000;  //인덱스로 접근하기 위해 최소값(-1000)을 시작인덱스(0)으로 만들기 위한 오프셋
        int[]next = new int[2001];
        int[]prev = new int[2001];
        
        //초기화
        Arrays.fill(next,Integer.MAX_VALUE);
        Arrays.fill(prev,Integer.MAX_VALUE);
        
        //수의 오른쪽값과 왼쪽값을 갱신
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
            
            //deque를 순회하며 해당 숫자를 바로 옆에 놓을 수 있는지 판다
            for(Deque<Integer> deque : dequeList){
                //오른쪽에 놓을 수 있는지 판단
                if(deque.peekFirst() == next[list.get(i)+offset]){
                    deque.addFirst(list.get(i));
                    valid=true;
                    break;
                }
                //왼쪽에 놓을 수 있는지 판단
                else if(deque.peekLast() == prev[list.get(i)+offset]){
                    deque.addLast(list.get(i));
                    valid=true;
                    break;
                }
            }
                
            //아무데도 놓지 못했다면 새로운 deque생성
            if(!valid){
                Deque deque = new ArrayDeque();
                deque.add(list.get(i));
                dequeList.add(deque);
            }
        }
        
        //데큐 개수 출력
        System.out.println(dequeList.size());
    }
}
