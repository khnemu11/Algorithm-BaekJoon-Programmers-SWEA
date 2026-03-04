from collections import deque

if __name__ == '__main__':
    row = list(map(int,input().split(' ')))
    N = row[0]
    K = row[1]

    q = deque(list(range(1,N+1)))
    result = []

    while len(q) != 0:
        for i in range(0,K-1):
            q.append(q.popleft())

        result.append(q.popleft())

    print("<"+", ".join(map(str,result))+">")