from queue import PriorityQueue

def solution(scoville, K):
    answer = 0
    pq = PriorityQueue()
    
    for s in scoville:
        pq.put(s)
    cnt = 0
    
    while pq.qsize() > 1:
        first = pq.get()
        
        if first >= K:
            return cnt
        
        second = pq.get()
        
        pq.put(first + second * 2)
        cnt = cnt + 1
        
    if pq.get() <= K:
        return -1
        
    return cnt