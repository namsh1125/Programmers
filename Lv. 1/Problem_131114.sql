-- 프로그래머스 131114번. 경기도에 위치한 식품창고 목록 출력하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/131114

SELECT WAREHOUSE_ID, WAREHOUSE_NAME, ADDRESS, IFNULL(FREEZER_YN, 'N') AS FREEZER_YN
FROM FOOD_WAREHOUSE
WHERE ADDRESS LIKE '경기도%'
ORDER BY WAREHOUSE_ID;
