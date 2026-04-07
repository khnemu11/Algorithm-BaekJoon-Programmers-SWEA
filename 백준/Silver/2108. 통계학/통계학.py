import sys
import statistics

if __name__ == '__main__':
    input = sys.stdin.readline
    N = int(input())

    nums = []

    for i in range(N):
        nums.append(int(input()))

    result = []
    result.append(round(statistics.mean(nums)))
    result.append(statistics.median(nums))

    modes = sorted(statistics.multimode(nums))
    if len(modes) == 1:
        result.append(modes[0])
    else:
        result.append(modes[1])

    result.append(max(nums) - min(nums))

    print("\n".join(map(str, result)))