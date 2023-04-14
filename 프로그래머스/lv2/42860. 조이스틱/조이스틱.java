/*
    조작 횟수의 최소값 = 각 알파벳 이동의 최소 횟수 + 커서의 위치 이동의 최소 횟수
    각 알파벳 이동의 최소 횟수 : min(A->Z로 뒤로 한번 가고 Z에서 내려오기 , A에서 올라가기,)
    커서의 위치 이동의 최소 횟수 : A를 제외한 모든 영역을 도착했을 때
*/

import java.util.*;

class Solution {
    int minMoveCnt;
    public int solution(String name) {
        int answer = 0;
        //최소 움직임은 모든 자리수를 한번씩 방문하는 것임으로 문자열의 길이 -1(시작위치 제거)
        minMoveCnt = name.length()-1;
        
        int visited = 1<<(name.length()-1);  //0번 인덱스에서 시작하므로 방문 처리
        int alphaCnt = 0;
        
        for(int i=0;i<name.length();i++){
            if(name.charAt(i)=='A'){    //A면 바꿀 필요가 없으므로 방문처리
                visited = visited | (1<< (name.length()-1-i));
            }
            //올라가는 경우와 내려가는 경우의 최소값을 더하기
            alphaCnt += Math.min(name.charAt(i)-'A','Z'-name.charAt(i)+1); 
        }
        //좌우 이동 최소값 구하기
        move(visited,name,0,0);

        return alphaCnt + minMoveCnt;
    }
    //왼쪽 오른쪽 한칸씩 모든 영역을 방문할때 까지 움직이는 메소드
    public void move(int visited, String name, int currIdx,int moveCnt){
        //최소값 보다 현재 움직임이 큰경우
        if(moveCnt >= minMoveCnt){
            return;
        //모든 영역을 방문한 경우
        }else if(visited == (1<<name.length())-1){
            minMoveCnt = Math.min(moveCnt, minMoveCnt);
            return;
        }else{
            //왼쪽 1칸 움직임
            int leftIdx = currIdx-1<0 ? name.length()-1 : currIdx-1;
            move(visited | (1<< (name.length()-1-leftIdx)),name,leftIdx,moveCnt+1);
            
            //오른쪽 한칸 움직임
            int rightIdx = currIdx+1 >= name.length() ? 0 : currIdx+1;
            move(visited | (1<< (name.length()-1-rightIdx)),name,rightIdx,moveCnt+1);
        }
    }
}