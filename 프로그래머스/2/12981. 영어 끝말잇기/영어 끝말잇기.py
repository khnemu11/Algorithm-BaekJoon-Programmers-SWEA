def solution(n, words):
    result = set()
    result.add(words[0])
    
    for i in range(1, len(words)):
        if words[i] in result or words[i-1][-1] != words[i][0] or len(words[i]) == 1:
            return [(i % n) + 1, (i // n) + 1]
        
        result.add(words[i])
        
    return [0, 0]