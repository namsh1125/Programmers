-- 프로그래머스 301649번. 대장균의 크기에 따라 분류하기 2
-- https://school.programmers.co.kr/learn/courses/30/lessons/301649

WITH COLONY_LEVEL AS (
    SELECT ID,
           NTILE(4) OVER (ORDER BY SIZE_OF_COLONY DESC) AS LEVEL
    FROM ECOLI_DATA
)

SELECT
    ID,
    CASE
        WHEN LEVEL = 1 THEN 'CRITICAL'
        WHEN LEVEL = 2 THEN 'HIGH'
        WHEN LEVEL = 3 THEN 'MEDIUM'
        ELSE 'LOW'
    END AS COLONY_NAME
FROM COLONY_LEVEL
ORDER BY ID;
