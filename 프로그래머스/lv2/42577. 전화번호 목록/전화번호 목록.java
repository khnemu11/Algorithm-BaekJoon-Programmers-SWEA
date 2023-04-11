import java.util.*;

/*
    접두어 표현을 위해 트라이 사용
    트라이로 내려가면서 만약 중간에 끝이 나거나 끝이 났는대도 다음이 있다면 접두사가 있다
    
*/


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
            //현재 매칭하는 문자열을 접두사로 쓰이는 경우
            if(trieNode.isLast){
                prefix=false;
            }
            
            if(trieNode.child.get(word.charAt(i))==null){
                trieNode.child.put(word.charAt(i), next);
            }
            
            next = trieNode.child.get(word.charAt(i));
            
            trieNode = next;
        }
        //다음 문자열도 있어서 현재 문자열이 접두사가 되는 경우
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
