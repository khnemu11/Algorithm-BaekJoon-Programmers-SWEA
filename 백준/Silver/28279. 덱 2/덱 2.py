import sys
from collections import deque

input = sys.stdin.readline

if __name__ == '__main__':
    N = int(input())

    d = deque()

    for i in range(N):
        cmd = list(map(int, input().split()))

        if cmd[0] == 1:
            d.appendleft(cmd[1])
        elif cmd[0] == 2:
            d.append(cmd[1])
        elif cmd[0] == 3:
            if len(d) == 0:
                print(-1)
            else:
                print(d.popleft())
        elif cmd[0] == 4:
            if len(d) == 0:
                print(-1)
            else:
                print(d.pop())
        elif cmd[0] == 5:
            print(len(d))
        elif cmd[0] == 6:
            if len(d) == 0:
                print(1)
            else:
                print(0)
        elif cmd[0] == 7:
            if len(d) == 0:
                print(-1)
            else:
                val = d.popleft()
                print(val)
                d.appendleft(val)
        elif cmd[0] == 8:
            if len(d) == 0:
                print(-1)
            else:
                val = d.pop()
                print(val)
                d.append(val)