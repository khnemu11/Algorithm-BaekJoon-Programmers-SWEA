import java.util.*;

class Solution {
    int minMoveCnt;
    public int solution(String name) {
        int answer = 0;
        minMoveCnt = name.length();
        // System.out.println(Integer.toBinaryString((1<<name.length())-1));
        // System.out.println(Integer.toBinaryString(0 | (1<< (name.length()-1-2))));
        int visited = 1<<(name.length()-1);
        // System.out.println(Integer.toBinaryString(visited));
        
        int alphaCnt = 0;
        for(int i=0;i<name.length();i++){
            if(name.charAt(i)=='A'){
                visited = visited | (1<< (name.length()-1-i));
            }
            alphaCnt += Math.min(name.charAt(i)-'A','Z'-name.charAt(i)+1);
        }
        // System.out.println(visited);
        
        move(visited,name,0,0);
        
        // System.out.println("min move count : "+minMoveCnt);
        return alphaCnt + minMoveCnt;
    }
    public void move(int visited, String name, int currIdx,int moveCnt){
        if(moveCnt >= minMoveCnt){
            return;
        }else if(visited == (1<<name.length())-1){
            minMoveCnt = Math.min(moveCnt, minMoveCnt);
            return;
        }else{
            int leftIdx = currIdx-1<0 ? name.length()-1 : currIdx-1;
            move(visited | (1<< (name.length()-1-leftIdx)),name,leftIdx,moveCnt+1);
            
            int rightIdx = currIdx+1 >= name.length() ? 0 : currIdx+1;
            move(visited | (1<< (name.length()-1-rightIdx)),name,rightIdx,moveCnt+1);
        }
    }
}