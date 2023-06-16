

class Solution {
    public int[] solution(String[] wallpaper) {
        int[] answer = {};
        int top = Integer.MAX_VALUE;
        int bottom = Integer.MIN_VALUE;
        int right = Integer.MIN_VALUE;
        int left = Integer.MAX_VALUE;
        
        for(int row = 0; row < wallpaper.length; row++){
            for(int col = 0; col < wallpaper[row].length(); col++){
                if(wallpaper[row].charAt(col)=='.'){
                    continue;
                }
                top = Math.min(top,row);
                bottom = Math.max(bottom,row);
                right = Math.max(right,col);
                left = Math.min(left,col);
            }   
        }
        
        return new int[]{top,left,bottom+1,right+1};
    }
}