from collections import deque

class Process:
    def __init__(self, location, priority):
        self.location = location
        self.priority = priority

def solution(priorities, location):
    queue = deque()
    
    for i in range(0, len(priorities)):
        queue.append(Process(i,priorities[i]))
    
    execs = []
    
    priorities.sort(reverse=True)
    
    for priority in priorities:
        find = False
    
        while find == False:
            curr = queue.popleft()
            
            if curr.priority == priority:
                execs.append(curr)
                find = True
            else:
                queue.append(curr)
    
    answer = 0
    
    for i in range(0,len(execs)):
        if execs[i].location == location:
            answer = i+1 
    
    return answer