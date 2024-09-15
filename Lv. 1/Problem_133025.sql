-- 프로그래머스 133025번. 과일로 만든 아이스크림 고르기
-- https://school.programmers.co.kr/learn/courses/30/lessons/133025

SELECT A.FLAVOR
FROM FIRST_HALF AS A
         JOIN ICECREAM_INFO AS B
              ON A.FLAVOR = B.FLAVOR
WHERE A.TOTAL_ORDER > 3000
  AND B.INGREDIENT_TYPE = 'fruit_based'
ORDER BY A.TOTAL_ORDER DESC;
