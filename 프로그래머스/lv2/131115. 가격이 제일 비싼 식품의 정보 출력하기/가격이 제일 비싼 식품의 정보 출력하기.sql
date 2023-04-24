-- 코드를 입력하세요
select * from FOOD_PRODUCT where price = (SELECT max(PRICE) from FOOD_PRODUCT);