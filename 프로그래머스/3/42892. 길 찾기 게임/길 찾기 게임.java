//43분 30초

import java.util.*;

class Solution {
    public Node tree;
    public List<Integer> preOrderList = new ArrayList<>();  //전위 순위 리스트
    public List<Integer> postOrderList = new ArrayList<>(); //후위 순위 리스트
    
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][nodeinfo.length];
        
        List<Node> nodeList = new ArrayList<>();
        
        //노드를 생성
        for(int i=0;i<nodeinfo.length;i++){
            nodeList.add(new Node(
                nodeinfo[i][0],
                nodeinfo[i][1],
                i+1
            ));
        }
        
        //루트 노드를 찾기위한 정렬
        Collections.sort(nodeList);
        //루트 노드를 트리 맨 위에 저장
        tree = nodeList.get(0);
        makeTree(nodeList,tree);
        
        //전위/후위 순회 저장
        setOrder(tree);
        
        for(int i=0;i<answer[0].length;i++){
            answer[0][i] = (int)preOrderList.get(i);
            answer[1][i] = (int)postOrderList.get(i);
        }
        
        return answer;
    }
    
    public void setOrder(Node curr){
        //전위 순회
        preOrderList.add(curr.seq);
        
        if(curr.left !=null){
            setOrder(curr.left);
        }
        if(curr.right !=null){
            setOrder(curr.right);
        }   
        
        //후위 순회
        postOrderList.add(curr.seq);
    }
    //트리를 만드는 메소드
    public void makeTree(List<Node> nodeList, Node parent){
        if(nodeList.size()<=1){
            return ;
        }
        
        List<Node> leftList = new ArrayList<>();
        List<Node> rightList = new ArrayList<>();
           
        //본인 노드를 제외한 나머지 노드를 왼쪽, 오른쪽 구분
        for(int i=1;i<nodeList.size();i++){
            Node curr = nodeList.get(i);
            
            if(curr.x < parent.x){
                leftList.add(curr);
            }else if(curr.x > parent.x){
                rightList.add(curr);
            }
        }
        //왼쪽 노드가 있으면 왼쪽 트리 생성
        if(leftList.size() > 0){
            Collections.sort(leftList);
            Node left = leftList.get(0);
            parent.left = left;
            makeTree(leftList,left);
        }
        //오른쪽 노드가 있으면 오른쪽 트리 생성
        if(rightList.size() > 0){
            Collections.sort(rightList);
            Node right = rightList.get(0);
            parent.right = right;
            makeTree(rightList,right);
        }
    }
}

class Node implements Comparable<Node>{
    int x;
    int y;
    int seq;
    Node left;
    Node right;
    
    public Node(int x, int y, int seq){
        this.x = x;
        this.y = y;
        this.seq = seq;
    }

    public int compareTo(Node o){
        return o.y - this.y;
    }
}