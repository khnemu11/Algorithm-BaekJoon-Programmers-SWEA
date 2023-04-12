import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Truck> truckOnBridge = new LinkedList<>();
        
        for(int i=0;i<bridge_length;i++){
            truckOnBridge.add(new Truck(0,0));
        }
        
        int time=0;
        int idx=0 ;
        int sum=0;
        while(idx < truck_weights.length){
            // System.out.println("#"+time+" "+sum);
            Truck truck = truckOnBridge.poll();
            sum-=truck.weight;
            
            if(sum+truck_weights[idx]<=weight){
                truckOnBridge.add(new Truck(time,truck_weights[idx]));
                sum+=truck_weights[idx];
                idx++;
            }else{
               truckOnBridge.add(new Truck(0,0));
            }
            time++;
        }
     
        
        return time+bridge_length;
    }
}
class Truck{
    int time;
    int weight;
    
    public Truck(int time, int weight){
        this.time = time;
        this.weight = weight;
    }
    
    public String toString(){
        return time +" : " + weight;
    }
}