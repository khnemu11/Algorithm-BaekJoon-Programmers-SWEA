from collections import deque

def solution(x, y, n):
    cost = [20000000] * ( y + 1 )
    q = deque()
    q.append(x)
    cost[x] = 0
    
    while len(q) > 0:
        curr = q.popleft()

        if curr == y:
            break
        if curr + n <= y and cost[curr] + 1 < cost[curr+n]:
            q.append(curr+n)
            cost[curr + n] = cost[curr] + 1
        if curr * 2 <= y and cost[curr] + 1 < cost[curr*2]:
            q.append(curr * 2)
            cost[curr * 2] = cost[curr] + 1
        if curr * 3 <= y and cost[curr] + 1 < cost[curr*3]:
            q.append(curr * 3)
            cost[curr * 3] = cost[curr] + 1

    answer = cost[y]

    if answer == 20000000:
        answer = -1
    
    return answer