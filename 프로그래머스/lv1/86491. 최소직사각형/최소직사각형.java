import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int widthMin = 0;
        int heightMin = 0;
        
        for(int i=0;i<sizes.length;i++){
           widthMin = Math.max(sizes[i][0],widthMin);
           heightMin = Math.max(heightMin,sizes[i][1]);
        }
        
        // System.out.println("start: "+widthMin+"*"+heightMin);
        
        for(int i=0;i<sizes.length;i++){
            int height = getHeight(sizes[i][0], sizes,i);
            // System.out.println(sizes[i][0]+"*"+height);
            if(height != Integer.MAX_VALUE && widthMin * heightMin > height * sizes[i][0]){
                widthMin = sizes[i][0];
                heightMin = height;
            }
            
            height = getHeight(sizes[i][1], sizes,i);
            // System.out.println(sizes[i][1]+"*"+height);
            if(height != Integer.MAX_VALUE && widthMin * heightMin > height * sizes[i][1]){
                widthMin = sizes[i][1];
                heightMin = height;
            }
        }
        
        // System.out.println("result " +widthMin+"*"+heightMin);
        
        return widthMin*heightMin;
    }
    public int getHeight(int width, int [][]sizes,int idx){
        int height = 0;
        
        for(int i=0;i<sizes.length;i++){
            if(sizes[i][0] > width && sizes[i][1] > width){
                 return Integer.MAX_VALUE;
            }else if(sizes[i][0]<=width && sizes[i][1]<=width){
                height = Math.max(height,Math.min(sizes[i][1],sizes[i][0]));
            }else if(sizes[i][0]<=width){
                height = Math.max(height,sizes[i][1]);
            }else{
                height = Math.max(height,sizes[i][0]);
            }
        }
        return height;
    }
}