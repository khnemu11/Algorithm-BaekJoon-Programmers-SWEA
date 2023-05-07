-- 코드를 입력하세요


SELECT board.TITLE, board.BOARD_ID, reply.REPLY_ID, reply.WRITER_ID, reply.CONTENTS, date_format(reply.CREATED_DATE,'%Y-%m-%d') 
from USED_GOODS_BOARD as board join USED_GOODS_REPLY as reply
on board.BOARD_ID = reply.BOARD_ID
where date_format(board.CREATED_DATE,'%Y-%m') = '2022-10'
order by 6,1