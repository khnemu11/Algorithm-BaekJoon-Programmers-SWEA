class Solution {
    public int solution(String skill, String[] skill_trees) {
		int result=0;
		
		for(String skill_tree : skill_trees) {
			int current_skill=0;
			
			for(int i=0;i<skill_tree.length();i++) {
				if(skill.indexOf(skill_tree.charAt(i)) != -1) {
					if(skill_tree.charAt(i) == skill.charAt(current_skill)) {
						current_skill++;
					}
					else {
						current_skill=-1;
						break;
					}
				}
			}
			if(current_skill!=-1) {
				result++;
			}
		}
        return result;
    }
}