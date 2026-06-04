def solution(phone_book):
    answer = True
    list = set()
    
    phone_book.sort(key= len, reverse=True)

    for phone in phone_book:
        num = ""
        
        if phone in list:
            answer=False
            break
        
        for i in phone:
            num = num + i
            list.add(num)
    
    return answer