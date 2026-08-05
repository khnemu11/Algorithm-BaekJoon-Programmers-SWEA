import queue

class Coordinate:
    def __init__(self, row, col,count):
        self.row = row
        self.col = col
        self.count = count
        
def solution(maps):
    q = queue.Queue()
    
    q.put(Coordinate(0,0,1))
    
    nextDirection = [[-1,0],[1,0],[0,-1],[0,1]]
    visited = [[False for col in range(len(maps[0]))] for row in range(len(maps))]
    
    answer = -1
    
    while q.qsize() > 0 :
        curr = q.get()
        visited[curr.row][curr.col] = True
        
        if curr.row == len(maps) -1 and curr.col == len(maps[0]) - 1:
            answer = curr.count
            break
        
        for n in nextDirection:
            next = Coordinate(curr.row + n[0], curr.col+n[1],curr.count + 1)
            if next.row < 0 or next.row >= len(maps) or next.col < 0 or next.col >= len(maps[0]):
                continue
            if maps[next.row][next.col] == 0:
                continue
            if visited[next.row][next.col] == True:
                continue
            visited[next.row][next.col] = True
            q.put(next)
    
    return answer