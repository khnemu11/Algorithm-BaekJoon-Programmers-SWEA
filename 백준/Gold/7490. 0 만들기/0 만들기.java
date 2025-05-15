import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

public class Main {
    static List<String> answerList = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int i=0;i<T;i++){
            int N = Integer.parseInt(br.readLine());
            answerList = new ArrayList<>();

            findZero(0, new ArrayList<String>(),N);

            Collections.sort(answerList);

            for(String answer : answerList){
                System.out.println(answer);
            }

            if(i != T-1){
                System.out.println();
            }
        }
    }

    public static void findZero(int step, List<String> choiceList, int N){
        if(step == N-1){
            StringBuilder sb = new StringBuilder();
            Queue<String> q = new LinkedList<>();

            for(int i=1;i<N;i++){
                sb.append(i).append(choiceList.get(i-1));
                q.add(String.valueOf(i));

                if(!choiceList.get(i-1).equals(" ")){
                    q.add(choiceList.get(i-1));
                }
            }

            sb.append(N);
            q.add(String.valueOf(N));

            int answer = 0;
            String oper = "+";
            int value = 0;

            while(!q.isEmpty()){
                String curr = q.poll();

                if(curr.equals("+") || curr.equals("-")){
                    if(oper.equals("+")){
                        answer = answer + value;
                    }
                    else{
                        answer = answer - value;
                    }
                    value = 0;
                    oper = curr;
                }else{
                    value = value*10 + Integer.parseInt(curr);
                }
            }

            if (oper.equals("+")) {
                answer = answer + value;
            } else {
                answer = answer - value;
            }

            if(answer == 0){
                answerList.add(sb.toString());
            }
        }else{
            String[] op = {"+","-"," "};

            for(int i=0;i<op.length;i++){
                choiceList.add(op[i]);
                findZero(step+1, choiceList, N);
                choiceList.remove(choiceList.size()-1);
            }
        }
    }
}