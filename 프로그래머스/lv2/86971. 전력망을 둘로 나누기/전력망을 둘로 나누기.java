/*
    트리는 그래프의 일종이므로 더 만들기 쉬운 그래프 형성
    전선(경로)를 하나씩 골라 해당 전선을 제외하고 그래프 생성
    각 경로를 유니온 파인드로 부모 노드에 연결
    루트 부모별 개수의 차이중 최소값을 저장
*/

import java.util.*;

class Solution {
    static int parents[];
    static int minDiff;
    
    public int solution(int n, int[][] wires) {
        minDiff = n;
        
        //끊을 전선을 고르는 부분
        for(int i=0;i<wires.length;i++){
            makeTop(n,i,wires);
        }
        
        return minDiff;
    }
    //전력망 네트워크를 만드는 부분
    public void makeTop(int n, int exceptIdx,int[][] wires){
        //부모 배열 초기화
        parents = new int[n+1];
        for(int i=0;i<=n;i++){
            parents[i] = i;
        }
        
        //제외할 전선을 빼고 모든 전선끼리 병합
        for(int i=0;i<wires.length;i++){
            if(i == exceptIdx){ 
                continue;
            }
            
            union(wires[i][0],wires[i][1]);
        }
        //루트별 전선의 개수를 가지고 있는 해쉬맵
        HashMap<Integer,Integer> topCnt = new HashMap<>();
        
        for(int i=1;i<=n;i++){
            int parent = getParent(i);
            topCnt.put(parent,topCnt.getOrDefault(parent,0)+1);
        }
        
        //네트워크별 전선개수의 차이를 구하는 부분
        int diff =0;
        
        for(int key : topCnt.keySet()){
            if(diff == 0){
                diff +=topCnt.get(key);
            }else{
                diff -=topCnt.get(key);
            }
        }
        minDiff = Math.min(Math.abs(diff),minDiff);
    }
    //부모값을 구하는 부분
    public int getParent(int child){
        if(parents[child] == child){
            return parents[child];
        }
        
        parents[child] = getParent(parents[child]);
        return parents[child];
    }
    //네트워크의 병합 메소드
    public void union(int a, int b){
        int pa = getParent(a);
        int pb = getParent(b);
        
        if(pa < pb){
            parents[pb] = pa;
        }else{
            parents[pa] = pb;
        }
    }
}
