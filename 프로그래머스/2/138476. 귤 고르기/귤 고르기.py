class Tangerine:
    def __init__(self, size, cnt):
        self.size = size
        self.cnt = cnt
    
    def __str__(self):
        return f'{self.size} exists {self.cnt}'

def solution(k, tangerine):
    tangerine_cnt = {}
    
    for size in tangerine:
        tangerine_cnt[size] = tangerine_cnt.get(size , 0)+1
    
    tangerine_list = []
    
    for key in tangerine_cnt.keys():
        tangerine_list.append(Tangerine(key, tangerine_cnt[key]))
    
    tangerine_list.sort(key=lambda x: x.cnt)
    sum = 0
    del_cnt = 0
    
    for i in range(0,len(tangerine_list)):
        sum = sum + tangerine_list[i].cnt
        
        if len(tangerine) - sum < k:
            break
        
        del_cnt = del_cnt+1
    
    return len(tangerine_list) - del_cnt