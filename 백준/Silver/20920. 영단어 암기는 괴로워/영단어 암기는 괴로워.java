import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String,Word> wordMap = new HashMap<>();

        for(int i=0;i<N;i++){
            String value = br.readLine();

            Word word = wordMap.getOrDefault(value,new Word(value,0, value.length()));
            word.count++;

            wordMap.put(value,word);
        }

        PriorityQueue<Word> pq = new PriorityQueue<>();

        for(String key : wordMap.keySet()){
            pq.add(wordMap.get(key));
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(!pq.isEmpty()) {
            Word word = pq.poll();

            if (word.length < M) {
                continue;
            }

            bw.append(word.value);
            bw.newLine();
        }

        bw.flush();
    }
}

class Word implements Comparable<Word>{
    String value;
    int count;
    int length;

    public Word(String value, int count, int length){
        this.value = value;
        this.count = count;
        this.length = length;
    }

    @Override
    public int compareTo(Word o) {
        if(this.count == o.count){
            if(this.length == o.length){
                return this.value.compareTo(o.value);
            }
            return o.length - this.length;
        }
        return o.count - this.count;
    }
}