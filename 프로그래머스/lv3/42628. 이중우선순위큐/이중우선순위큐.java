import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> maxPQ= new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minPQ= new PriorityQueue<>();
        
        for(String op : operations){
            String splited[] = op.split(" ");
            
            if(splited[0].equals("I")){
                maxPQ.add(Integer.valueOf(splited[1]));
                minPQ.add(Integer.valueOf(splited[1]));
            }else{
                if(splited[1].equals("1") && !maxPQ.isEmpty()){
                    maxPQ.poll();
                    while(!maxPQ.isEmpty() && !minPQ.isEmpty() && maxPQ.peek() < minPQ.peek()){
                        maxPQ.poll();
                        if(maxPQ.isEmpty()){
                            minPQ= new PriorityQueue<>();
                        }
                    }
                }else if (splited[1].equals("-1") && !minPQ.isEmpty()){
                    minPQ.poll();
                    while(!maxPQ.isEmpty() && !minPQ.isEmpty() && maxPQ.peek() < minPQ.peek()){
                        minPQ.poll();
                        if(minPQ.isEmpty()){
                            maxPQ= new PriorityQueue<>(Collections.reverseOrder());
                        }
                    }
                }
            }
            // System.out.println(maxPQ);
            // System.out.println(minPQ);
        }
        
        int[] answer = new int[2];
        if(!maxPQ.isEmpty() && !minPQ.isEmpty()){
            answer[0] = maxPQ.poll();
            answer[1] = minPQ.poll();
        }
        return answer;
    }
}