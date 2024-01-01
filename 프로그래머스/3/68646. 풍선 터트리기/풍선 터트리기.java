/*
    자기 자신이 이전값과 상관 없이 다음 값들이 자신보다 무조건 크다면 마지막까지 남을 수 있음
    -> 정방향, 역방향으로 증가하는 배열 탐색
*/

import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = 0;
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
        
        while(!increaseBalloons.isEmpty()){
            possibleBalloons.add(increaseBalloons.pop());
        }
        
        return possibleBalloons.size();
    }
}

