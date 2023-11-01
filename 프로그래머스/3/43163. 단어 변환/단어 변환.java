
import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        List<Word> graph[] = new ArrayList[words.length+1];
        int[] time = new int[words.length+1];
        Arrays.fill(time,Integer.MAX_VALUE);
        time[0] = 0;
        
        for(int i=0;i<words.length+1;i++){
            graph[i] = new ArrayList<>();
        }
        
        List<String> wordList = new ArrayList<>();
        
        wordList.add(begin);
        
        for(String word : words){
            wordList.add(word);
        }
        
        int beginSeq = 0;
        int targetSeq = -1; 
        
        for(int i=0;i<wordList.size();i++){
            if(wordList.get(i).equals(target)){
                System.out.println(target);
                targetSeq=i;    
            }
            
            for(int j=0;j<wordList.size();j++){
                int differ = 0;
                String from = wordList.get(i);
                String to = wordList.get(j);
                
                for(int k=0;k<from.length();k++){
                    if(from.charAt(k) != to.charAt(k)){
                        differ++;
                    }
                }
                
                if(differ == 1){
                    graph[i].add(new Word(j,to,1));
                }
            }   
        }
        
        if(targetSeq == -1){
            return 0;
        }
        
        PriorityQueue<Word> pq = new PriorityQueue<>();
        pq.add(new Word(beginSeq,begin,0));
               
        while(!pq.isEmpty()){
            Word curr = pq.poll();
            // System.out.println(curr.value+" time: "+curr.time);
            
            if(time[curr.seq] < curr.time){
                continue;
            }
            
            for(Word next : graph[curr.seq]){
                if(time[next.seq] > curr.time + 1){
                    time[next.seq] = curr.time + 1;
                    pq.add(new Word(next.seq, next.value, time[next.seq]));
                }
            }
        }
        
        return time[targetSeq] == Integer.MAX_VALUE ? 0 : time[targetSeq];
    }

}
class Word implements Comparable<Word>{
    int seq;
    String value;
    int time;
    
    public Word(int seq, String value,int time){
        this.seq = seq;
        this.value = value;
        this.time = time;
    }
    
    public int compareTo(Word o){
        return this.time - o.time;
    }
}