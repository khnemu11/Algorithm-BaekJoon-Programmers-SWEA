import java.util.*;

class Solution {
    static boolean prefix;
    
    public boolean solution(String[] phone_book) {
        prefix=true;
        
        Trie root = new Trie();
        
        for(String phone : phone_book){
            insert(phone,root);
            
            if(!prefix){
                break;        
            }
        }
        
        return prefix;
    }
    
    public void insert(String word, Trie trieNode){
        for(int i=0;i<word.length();i++){
            Trie next = new Trie();
            
            if(trieNode.isLast){
                prefix=false;
            }
            
            if(trieNode.child.get(word.charAt(i))==null){
                trieNode.child.put(word.charAt(i), next);
            }
            
            next = trieNode.child.get(word.charAt(i));
            
            trieNode = next;
        }
        
        if(trieNode.child.size()>0){
            prefix=false;
        }
        
        trieNode.isLast=true;
    }
}
class Trie{
    Map<Character,Trie> child = new HashMap<>();
    boolean isLast=false;
}