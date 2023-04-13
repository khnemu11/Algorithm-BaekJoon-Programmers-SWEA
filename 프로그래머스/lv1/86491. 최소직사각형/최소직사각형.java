import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int widthMin = 0;
        int heightMin = 0;
        
        for(int i=0;i<sizes.length;i++){
           widthMin = Math.max(widthMin,Math.max(sizes[i][0],sizes[i][1]));
           heightMin = Math.max(heightMin,Math.min(sizes[i][0],sizes[i][1]));
        }
        
        return widthMin*heightMin;
    }
}