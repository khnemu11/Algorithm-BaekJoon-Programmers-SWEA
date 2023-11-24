/*
    걸린 시간 : 1시간 49분 18초
    
    알고리즘 : 배열, 구현
    
    배열리스트로 구현)
    O(n) = (인덱스 탐색 시간)*(삭제,복구 시간)*(명령어 개수) = 1 * 1,000,000 * 200,000
    링크드리스트로 구현)
    O(n) = (인덱스 탐색 시간)*(삭제,복구 시간)*(명령어 개수) = 1,000,000 * 1 * 200,000
    
    삭제, 복구, 인덱스 탐색을 전부 O(1)로 해야 최대 200,000명령어 이내로 끝낼 수 있다.
    이때 탐색을 배열로, 삭제 복구도 배열로 구현하면 전부 O(1)로 가능하다
    
    이를 구현하기 위해 다음 인덱스 배열과 이전 인덱스 배열을 이용해 삭제와 복구를 구현하고
    해당 배열들의 인덱스를 이용해 탐색하게 했다.
    
    삭제) 
    이전 -> 현재 -> 다음 
    이전 -> 다음
    
    다음[이전] = 다음
    이전[다음] = 이전
    
    복구) 
    이전 -> 다음
    이전 -> 현재 -> 다음 
    
    다음[이전] = 현재
    이전[다음] = 현재
    
    이때 다음과 이전이 맨 끝인경우 outofindex에러가 발생하므로 예외처리를 한다.
*/

import java.util.*;

class Solution {
    int [] prev;
    int [] next;
    Stack<Integer> history;
    int END = -1;
    
    public String solution(int n, int k, String[] cmd) {        
        // 각 인덱스 별 이전 인덱스 저장 배열 초기화
        prev = new int[n];
        for(int i=0;i<prev.length;i++){
            prev[i] = i-1;
        }
        prev[0] = END;
        // 각 인덱스 별 다음 인덱스 저장 배열 초기화
        next = new int[n];
        for(int i=0;i<prev.length;i++){
            next[i] = i+1;
        }
        next[n-1] = END;
        
        history = new Stack<>();
        int cursor = k;
        
        for(String c : cmd){
            if(c.charAt(0) == 'C'){
                 //삭제
                //다음 커서 설정, 이때 맨 끝 커서면 이전 커서로 변경
                int nextCursor = next[cursor] == END ? prev[cursor] : next[cursor];
                history.add(cursor);
                delete(cursor);
                cursor = nextCursor;
            }else if(c.charAt(0) == 'Z'){
                //복구
                int target = history.pop();
                recovery(target);
            }else if(c.charAt(0)=='D'){
                //아래 이동
                String[] parsed = c.split(" ");
                int distance = Integer.parseInt(parsed[1]);
                cursor = down(cursor,distance);
            }else if(c.charAt(0)=='U'){
                //위 이동
                String[] parsed = c.split(" ");
                int distance = Integer.parseInt(parsed[1]);
                cursor = up(cursor,distance);
            }
        }
        
        boolean[] deleted = new boolean[n];
        //삭제된 인덱스 확인
        while(!history.isEmpty()){
            int deletedIdx = history.pop();
            deleted[deletedIdx] = true;
        }
        
        StringBuilder sb = new StringBuilder();
        
        //문자열 생성
        for(int i=0;i<n;i++){
            if(deleted[i]){
                sb.append("X");
            }else{
                sb.append("O");
            }
        }
        
        return sb.toString();
    }
    public void delete(int cursor){
        if(next[cursor] != END){
            prev[next[cursor]] = prev[cursor];    
        }
        if(prev[cursor] != END){
            next[prev[cursor]] = next[cursor];
        }
    }
    public void recovery(int cursor){
        if(next[cursor] != END){
            prev[next[cursor]] = cursor;    
        }
        if(prev[cursor] != END){
            next[prev[cursor]] = cursor;
        }
    }
    public int up(int cursor,int distance){
        for(int i=0;i<distance;i++){
            cursor = prev[cursor];
        }
        return cursor;
    }
     public int down(int cursor,int distance){
        for(int i=0;i<distance;i++){
            cursor = next[cursor];
        }
        return cursor;
    }
}
