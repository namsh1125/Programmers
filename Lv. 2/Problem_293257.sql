-- 프로그래머스 293257번. 물고기 종류 별 잡은 수 구하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/293257

SELECT COUNT(*) AS FISH_COUNT, FISH_NAME
FROM FISH_INFO AS FI
         JOIN FISH_NAME_INFO AS FNI
              ON FI.FISH_TYPE = FNI.FISH_TYPE
GROUP BY FISH_NAME
ORDER BY FISH_COUNT DESC;
