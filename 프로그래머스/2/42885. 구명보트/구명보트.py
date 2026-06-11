def solution(people, limit):
    answer = 0
    big_idx = len(people) - 1
    small_idx = 0
    
    people.sort()

    while small_idx <= big_idx:
        if people[small_idx] + people[big_idx] <= limit:
            small_idx = small_idx + 1
            
        answer=answer+1
        big_idx=big_idx-1
    
    return answer