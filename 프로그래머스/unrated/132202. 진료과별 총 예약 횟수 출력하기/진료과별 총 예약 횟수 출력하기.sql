-- 코드를 입력하세요
SELECT MCDP_CD as '진료과코드', count(MCDP_CD) as '5월예약건수'
from APPOINTMENT 
where date_format(APNT_YMD,"%m") = '05'
group by MCDP_CD
order by count(MCDP_CD),MCDP_CD