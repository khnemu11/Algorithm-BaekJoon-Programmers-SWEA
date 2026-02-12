class Coordinate:
    def __init__(self, x, y):
        self.x = x
        self.y = y

if __name__ == '__main__':
    N = int(input())
    list = []

    for i in range(N):
        row = input().split(" ")
        list.append(Coordinate(int(row[0]), int(row[1])))

    slist = sorted(list,key=lambda x:(x.x, x.y))

    for coordinate in slist:
        print(coordinate.x,coordinate.y)