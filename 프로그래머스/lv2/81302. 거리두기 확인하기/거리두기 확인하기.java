import java.util.*;

class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int [places.length];
        Arrays.fill(answer,1);
        
        for(int i=0;i<places.length;i++){
            Queue<People>peopleQ = getPeopleList(places[i]);
            
            while(!peopleQ.isEmpty()){
                People curr = peopleQ.poll();
                answer[i] = Math.min(followRule(places[i],curr),answer[i]);           
            }
        }
        
        return answer;
    }
    public Queue<People> getPeopleList(String[] places){
        Queue<People> peopleQ = new LinkedList<>();
        
        for(int i=0;i<places.length;i++){
            for(int j=0;j<places[0].length();j++){
                if(places[i].charAt(j) == 'P'){
                    peopleQ.add(new People(i,j,0));
                }
            }   
        }
        return peopleQ;
    }
    public int followRule(String[] board, People people){
        // System.out.println("## start : "+people.row+" , "+people.col);
        int dx[] ={-1,1,0,0};
        int dy[] ={0,0,-1,1};
        
        int visited[][] = new int[board.length][board[0].length()];
        
        for(int i=0;i<visited.length;i++){
            Arrays.fill(visited[i],board.length*board.length+1);
        }
        visited[people.row][people.col]=0;
        
        PriorityQueue<People> pq = new PriorityQueue<>();
        pq.add(people);
        
        while(!pq.isEmpty()){
            People curr = pq.poll();
            
            if(curr.distance > visited[curr.row][curr.col]){
                continue;
            }
            if(curr.distance > 2){
                continue;
            }
            // System.out.println(curr.row+" , "+curr.col +" distance : "+curr.distance);
            for(int k=0;k<dx.length;k++){
                
                People next = new People(curr.row+dx[k],curr.col+dy[k],curr.distance+1);
                
                if(next.row<0 || next.col<0||next.row>=board.length || next.col>=board[0].length()){
                    continue;
                }
                if(board[next.row].charAt(next.col) == 'X'){
                    continue;
                }
                if(visited[next.row][next.col] < next.distance){
                    continue;
                }
                if(board[next.row].charAt(next.col) == 'P' && next.distance<=2){
                    return 0;
                }
                visited[next.row][next.col] = next.distance;
                pq.add(next);
            }
        }
        return 1;
    }
}
class Coordinate{
    int row;
    int col;
    
    public Coordinate(int row, int col){
        this.row=row;
        this.col=col;
    }
}
class People extends Coordinate implements Comparable<People> {
    int distance;
    
    public People(int row, int col, int distance){
        super(row,col);
        this.distance = distance;
    }
    
    public int compareTo(People o){
        return this.distance-o.distance;
    }
}