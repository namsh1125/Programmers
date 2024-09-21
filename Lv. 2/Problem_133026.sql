-- 프로그래머스 133026번. 성분으로 구분한 아이스크림 총 주문량
-- https://school.programmers.co.kr/learn/courses/30/lessons/133026

SELECT B.INGREDIENT_TYPE, SUM(A.TOTAL_ORDER) AS TOTAL_ORDER
FROM FIRST_HALF AS A
         JOIN ICECREAM_INFO AS B
              ON A.FLAVOR = B.FLAVOR
GROUP BY INGREDIENT_TYPE
ORDER BY TOTAL_ORDER ASC;
