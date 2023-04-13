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
        
        for(int i=0;i<wires.length;i++){
            makeTop(n,i,wires);
        }
        
        return minDiff;
    }
    
    public void makeTop(int n, int exceptIdx,int[][] wires){
        parents = new int[n+1];
        for(int i=0;i<=n;i++){
            parents[i] = i;
        }
        
        for(int i=0;i<wires.length;i++){
            if(i == exceptIdx){
                continue;
            }
            
            union(wires[i][0],wires[i][1]);
        }
        
        HashMap<Integer,Integer> topCnt = new HashMap<>();
        
        for(int i=1;i<=n;i++){
            int parent = getParent(i);
            topCnt.put(parent,topCnt.getOrDefault(parent,0)+1);
        }
        
        int diff =0;
        
        for(int key : topCnt.keySet()){
            if(diff == 0){
                diff +=topCnt.get(key);
            }else{
                diff -=topCnt.get(key);
            }
        }
        minDiff = Math.min(Math.abs(diff),minDiff);
        // System.out.println(topCnt);
    }
    
    public int getParent(int child){
        if(parents[child] == child){
            return parents[child];
        }
        
        parents[child] = getParent(parents[child]);
        return parents[child];
    }
    
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