/*
    자기 자신이 이전값과 상관 없이 다음 값들이 자신보다 무조건 크다면 마지막까지 남을 수 있음
    -> 정방향, 역방향으로 증가하는 배열 탐색
    
    33분 12초
*/

import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = 0;
        //가능한 번호가 오른쪽 방향/왼쪽 방향이 같을 수 있으므로 중복 제거
        Set<Integer> possibleBalloons = new HashSet<>();        
        
        //오른쪽 방향
        Stack<Integer> increaseBalloons = new Stack<>();
        
        for(int i = 0;i<a.length;i++){
            while(!increaseBalloons.isEmpty() && 
                increaseBalloons.peek() > a[i]){
                increaseBalloons.pop();
            }
            increaseBalloons.push(a[i]);
        }
        
        //가능한 풍선 번호 저장
        while(!increaseBalloons.isEmpty()){
            possibleBalloons.add(increaseBalloons.pop());
        }
        
        //왼쪽 방향
        increaseBalloons = new Stack<>();
        
        for(int i = a.length-1;i>=0;i--){
            while(!increaseBalloons.isEmpty() && 
                increaseBalloons.peek() > a[i]){
                increaseBalloons.pop();
            }
            increaseBalloons.push(a[i]);
        }
        
        //가능한 풍선 번호 저장
        while(!increaseBalloons.isEmpty()){
            possibleBalloons.add(increaseBalloons.pop());
        }
        
        return possibleBalloons.size();
    }
}

