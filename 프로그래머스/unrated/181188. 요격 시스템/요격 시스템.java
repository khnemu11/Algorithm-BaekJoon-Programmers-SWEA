/*

    시계열 문제 -> 그리디
    현재 요격 미사일로 쏠 수 있는 최대치 = 먼저 발견된 미사일이 도착하기 전에 발사된 모든 미사일
    
    가장 먼저 도착한 미사일을 기준으로 같이 요격이 가능한 미사일을 전부 찾고 요격이 불가능한 미사일이 등장하면 새로운 기준으로 재설정
    -> 도착이 빠른 순서로 미사일을 정렬
    
    그리디 문제에서 정 모르겠으면 우선순위를 변경해보자
    
    풀이 시간 : 14분 17초
*/

import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        PriorityQueue<Missile> pq = new PriorityQueue<>();
        
        //도착이 빠른 순서로 미사일을 정렬
        for(int i=0;i<targets.length;i++){
            pq.add(new Missile(targets[i][0],targets[i][1]));
        }
        
        int cnt = 1;
        Missile last = pq.poll();
        
        while(!pq.isEmpty()){
            Missile curr = pq.poll();
            //현재 미사일을 요격 못하는 경우 현재 미사일을 요격 미사일로 설정
            if(last.end <= curr.start){
                cnt++;
                last = curr;
            }
        }
        
        return cnt;
    }
}

class Missile implements Comparable<Missile>{
    int start;
    int end;
    
    public Missile(int start, int end){
        this.start =start;
        this.end =end;
    }
    
    public int compareTo(Missile o){
        return this.end - o.end;
    }
}
