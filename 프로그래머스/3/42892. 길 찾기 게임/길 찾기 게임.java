import java.util.*;

class Solution {
    public Node tree;
    public List<Integer> preOrderList = new ArrayList<>();
    public List<Integer> postOrderList = new ArrayList<>();
    
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][nodeinfo.length];
        
        List<Node> nodeList = new ArrayList<>();
        
        for(int i=0;i<nodeinfo.length;i++){
            nodeList.add(new Node(
                nodeinfo[i][0],
                nodeinfo[i][1],
                i+1
            ));
        }
        
        Collections.sort(nodeList);
        tree = nodeList.get(0);
        divideTree(nodeList,tree);
        
        // System.out.println(tree);
        
        setOrder(tree);
        
        for(int i=0;i<answer[0].length;i++){
            answer[0][i] = (int)preOrderList.get(i);
            answer[1][i] = (int)postOrderList.get(i);
        }
        // System.out.println(preOrderList);       
        // System.out.println(postOrderList);
        
        return answer;
    }
    
    public void setOrder(Node curr){
        preOrderList.add(curr.seq);
        
        if(curr.left !=null){
            setOrder(curr.left);
        }
        if(curr.right !=null){
            setOrder(curr.right);
        }   
        postOrderList.add(curr.seq);
    }
    public void divideTree(List<Node> nodeList, Node parent){
        if(nodeList.size()<=1){
            return ;
        }
        
        // System.out.println(parent);
        
        List<Node> leftList = new ArrayList<>();
        List<Node> rightList = new ArrayList<>();
           
        for(int i=1;i<nodeList.size();i++){
            Node curr = nodeList.get(i);
            
            if(curr.x < parent.x){
                leftList.add(curr);
            }else if(curr.x > parent.x){
                rightList.add(curr);
            }
        }
       
        if(leftList.size() > 0){
            Collections.sort(leftList);
            Node left = leftList.get(0);
            parent.left = left;
            divideTree(leftList,left);
        }
        if(rightList.size() > 0){
            Collections.sort(rightList);
            Node right = rightList.get(0);
            parent.right = right;
            divideTree(rightList,right);
        }
        
       // System.out.println("left : "+leftList);
       // System.out.println("right : "+rightList);
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
    
    public String toString(){
        return seq+" : "+x+" , "+y;
    }
    
    public int compareTo(Node o){
        return o.y - this.y;
    }
}