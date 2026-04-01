if __name__ == '__main__':
    K = int(input())

    for i in range(K):
        print(f'Class {i+1}')

        row = list(map(int, input().split()))
        row.pop(0)
        sorted_row = sorted(row)

        maxScore = max(sorted_row)
        minScore = min(sorted_row)
        maxGap = 0

        for n in range(0, len(sorted_row)-1):
            maxGap = max(maxGap, sorted_row[n+1] - sorted_row[n])

        print(f"Max {maxScore}, Min {minScore}, Largest gap {maxGap}")