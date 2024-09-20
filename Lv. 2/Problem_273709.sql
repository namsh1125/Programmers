-- 프로그래머스 273709번. 조건에 맞는 아이템들의 가격의 총합 구하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/273709

SELECT SUM(PRICE) AS TOTAL_PRICE
FROM ITEM_INFO
WHERE RARITY = 'LEGEND'
