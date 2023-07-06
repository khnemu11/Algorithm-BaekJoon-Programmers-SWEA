import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        PriorityQueue<MyFile> pq = new PriorityQueue<>();
        for(int i=0;i<files.length;i++){
            MyFile file = new MyFile();
            file.seq = i;
            file.fullName = files[i];
            StringBuilder sb = new StringBuilder();
            int numberStart=0;
            
            //number 시작 인덱스를 숫자가 나올때 까지 찾으면서 head부분을 찾는 부분
            for(;numberStart<files[i].length();numberStart++){
                if(Character.isDigit(files[i].charAt(numberStart))){
                    break;
                }
                sb.append(files[i].charAt(numberStart));
            }
            //헤드는 대소문자 구별X
            file.head = sb.toString().toLowerCase();
            
            //tail시작 인덱스를 찾기위해 number 시작 인덱스부터 찾는다.
            int tailStart = numberStart;
            
            //tail 시작 인덱스를 찾으면서 number값을 구하는 부분
            for(;tailStart<files[i].length();tailStart++){
                if(!Character.isDigit(files[i].charAt(tailStart))){
                    break;
                }
                file.number = file.number*10+files[i].charAt(tailStart)-'0';
            }
            //tail이 존재하면 나머지 부분 저장
            if(tailStart < files[i].length()){
                file.tail = files[i].substring(tailStart,files[i].length());
            }
            //정렬
            pq.add(file);
        }        
        
        //정렬한 파일 이름을 배열에 저장
        String[] answer = new String[pq.size()];
        
        for(int i=0;i<answer.length;i++){
            answer[i] = pq.poll().fullName;
        }
        
        return answer;
    }
}
//head-number-tail로 구성된 파일 이름 객체
class MyFile implements Comparable<MyFile>{
    int seq;    //순서
    String fullName;    //풀네임
    String head; 
    int number;
    String tail;
    
    public int compareTo(MyFile o){
        if(head.equals(o.head)){
            if(number == o.number){
                return seq-o.seq;
            }
            return number - o.number;
        }
        
        return head.compareTo(o.head);
    }
}