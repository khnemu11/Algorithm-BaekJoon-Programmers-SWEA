if __name__ == '__main__':
    row = list(map(int,input().split(" ")))
    N = row[0]
    M = row[1]
    result = list(map(int,input().split(" ")))
    result.extend(list(map(int,input().split(" "))))
    sorted_result = sorted(result)

    print(" ".join(map(str,sorted_result)))
