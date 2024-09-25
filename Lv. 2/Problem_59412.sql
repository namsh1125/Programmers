-- 프로그래머스 59412번. 입양 시각 구하기(1)
-- https://school.programmers.co.kr/learn/courses/30/lessons/59412

SELECT HOUR (DATETIME) AS HOUR, COUNT (*) AS COUNT
FROM ANIMAL_OUTS
WHERE HOUR (DATETIME) >= 9 AND HOUR (DATETIME) < 20
GROUP BY HOUR
ORDER BY HOUR;
