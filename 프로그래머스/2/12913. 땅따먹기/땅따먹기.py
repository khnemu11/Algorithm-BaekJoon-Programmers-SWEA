def solution(land):
    n = len(land)
    dp = [[0] * 4 for _ in range(n)]

    dp[0] = land[0][:]

    for i in range(1, n):
        for j in range(4):
            dpMax = 0

            for k in range(4):
                if j == k:
                    continue

                dpMax = max(dp[i-1][k], dpMax)

            dp[i][j] = dpMax + land[i][j]

    return max(dp[n-1])