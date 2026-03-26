if __name__ == '__main__':
    N = int(input())
    extends = {}

    for i in range(N):
        fileName = input()
        splitedfileName = fileName.split('.')
        extend = splitedfileName[1]

        extends[extend] = extends.get(extend, 0) + 1

    keys = sorted(list(extends.keys()))

    for key in keys:
        print(f'{key} {extends[key]}')