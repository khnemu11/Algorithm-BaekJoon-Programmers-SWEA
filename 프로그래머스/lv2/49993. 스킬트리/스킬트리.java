class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for(int i=0;i<skill_trees.length;i++){
            int count=0;
            char arr [] = skill_trees[i].toCharArray();
            boolean available=true;
            
            for(int j=0;j<arr.length;j++){
                if(!skill.contains(String.valueOf(arr[j]))){
                    continue;
                }
                if(arr[j]==skill.charAt(count)){
                    count++;
                }
                else{
                    available=false;
                    break;
                }
            }
            
            if(available){
                answer++;
            }
        }
        
        
        return answer;
    }
}