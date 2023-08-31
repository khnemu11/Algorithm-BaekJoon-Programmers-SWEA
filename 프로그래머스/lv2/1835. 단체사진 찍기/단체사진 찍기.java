import java.util.*;

class Solution {
    int count = 0;
    boolean[] visited = new boolean[8];
    String[] people = {"A", "C", "F", "J", "M", "N", "R", "T"};
    HashMap<String,Integer> locationMap = new HashMap<>();
    String[][] conditions;
    
    public int solution(int n, String[] data) {
        conditions = new String[n][5]; 
        
        for(int i=0;i<data.length;i++){
            conditions[i] = data[i].split("");
        }

        setPeople(0);
        
        return count;
    }
    
    public void setPeople(int index){
        if(index == visited.length){
            for(int i=0;i<conditions.length;i++){
               int firstLocation = locationMap.get(conditions[i][0]);
               int secondLocation = locationMap.get(conditions[i][2]);
               if(conditions[i][3].equals("=") 
                  && Math.abs(firstLocation - secondLocation) != Integer.valueOf(conditions[i][4]) +1){
                    return;
               }else if(conditions[i][3].equals("<") 
                        && Math.abs(firstLocation - secondLocation) >= Integer.valueOf(conditions[i][4]) +1){
                   return;
               }else if(conditions[i][3].equals(">") 
                        && Math.abs(firstLocation - secondLocation) <= Integer.valueOf(conditions[i][4]) +1){
                   return;
               }
                
            }
            count++;
            return ;
        }
        for(int i=0;i<people.length;i++){
            if(visited[i]){
                continue;
            }
            
            visited[i]=true;
            locationMap.put(people[index],i);
            setPeople(index+1);
            visited[i]=false;
        }
    }
}