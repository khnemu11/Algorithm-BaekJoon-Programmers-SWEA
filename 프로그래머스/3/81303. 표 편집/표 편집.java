// prev 0   0   1   2   3
// curr 0   1   2   3   4 
// next 1   2   3   4   4 
// 3 제거
// prev 0   0   1   1   2
// curr 0   1   2   3   4 
// next 1   2   4   -1  4 

//3 복구
// prev 0   0   1   2   3
// curr 0   1   2   3   4 
// next 1   2   4   4   4 

import java.util.*;

class Solution {
    int [] prev;
    int [] next;
    Stack<Integer> history;
    int END = -1;
    int DELETED = -2;
    
    public String solution(int n, int k, String[] cmd) {
        String answer = "";
        
        // 각 인덱스 별 이전 인덱스 저장 배열 초기화
        prev = new int[n];
        int[] curr = new int[n];
        for(int i=0;i<prev.length;i++){
            prev[i] = i-1;
            curr[i] = i;
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
            // System.out.println("현재 값 : "+cursor);
            // System.out.println(Arrays.toString(prev));
            // System.out.println(Arrays.toString(curr));
            // System.out.println(Arrays.toString(next));
            if(c.charAt(0) == 'C'){
                // System.out.println("현재 위치 삭제");
                int nextCursor = next[cursor] == END ? prev[cursor] : next[cursor];
                history.add(cursor);
                delete(cursor);
                cursor = nextCursor;
                //삭제
            }else if(c.charAt(0) == 'Z'){
                int target = history.pop();
                // System.out.println(target+"복구");
                recovery(target);
                //복구
            }else if(c.charAt(0)=='D'){
                //아래 이동
                String[] parsed = c.split(" ");
                int distance = Integer.parseInt(parsed[1]);
                // System.out.println(distance+"만큼 아래 이동");
                cursor = down(cursor,distance);
            }else if(c.charAt(0)=='U'){
                //위 이동
                String[] parsed = c.split(" ");
                int distance = Integer.parseInt(parsed[1]);
                // System.out.println(distance+"만큼 위 이동");
                cursor = up(cursor,distance);
            }
        }
        
        boolean[] deleted = new boolean[n];
         while(!history.isEmpty()){
            int deletedIdx = history.pop();
            deleted[deletedIdx] = true;
        }
        
        StringBuilder sb = new StringBuilder();
        
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
        // prev[cursor] = prev[prev[cursor]];
        // next[cursor] = DELETED;
    }
    public void recovery(int cursor){
        if(next[cursor] != END){
            prev[next[cursor]] = cursor;    
        }
        if(prev[cursor] != END){
            next[prev[cursor]] = cursor;
        }
        // if(prev[cursor] != END){
        //     prev[cursor] = next[prev[cursor]];
        //     next[cursor] = next[prev[cursor]];
        //     next[prev[cursor]] = cursor;    
        // }
        // if(next[cursor] != END){
        //     prev[next[cursor]] = cursor;    
        // }
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