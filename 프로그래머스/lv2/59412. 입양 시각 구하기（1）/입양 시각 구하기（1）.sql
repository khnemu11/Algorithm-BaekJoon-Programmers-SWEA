-- 코드를 입력하세요
SELECT HOUR(DATETIME) HOUR,count(*) from ANIMAL_OUTS 
where HOUR(DATETIME) > 8 and HOUR(DATETIME) < 20 group by HOUR(DATETIME) order by HOUR(DATETIME) asc