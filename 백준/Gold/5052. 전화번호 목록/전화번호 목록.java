import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            Node root = new Node();
            boolean inValid = false;

            PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.length() - o2.length();
                }
            });

            for (int n = 0; n < N && !inValid; n++) {
                String phoneNumber = br.readLine();
                pq.add(phoneNumber);
            }

            while(!pq.isEmpty()){
                String phoneNumber = pq.poll();
                Node curr = root;

//                System.out.println(phoneNumber);
                for (int i = 0; i < phoneNumber.length(); i++) {
//                    System.out.println(phoneNumber.charAt(i));
//                    System.out.println(curr);

                    Node next = curr.next.get(phoneNumber.charAt(i));

                    if (next == null) {
                        next = new Node(phoneNumber.charAt(i));

                        curr.next.put(next.val, next);
                    }
                    if(next.end){
                        inValid=true;
                        break;
                    }

                    if (i == phoneNumber.length() - 1) {
                        next.end = true;
                    }

                    curr = next;
                }
            }


            if(inValid){
                System.out.println("NO");
            }else{
                System.out.println("YES");
            }
        }
    }
}

class Node{
    char val;
    Map<Character,Node> next = new HashMap<>();
    boolean end;

    public Node(){
    }
    public Node(char val) {
        this.val = val;
    }

    public String toString(){
        return val+" map : "+next.toString()+" end : "+end;
    }
}