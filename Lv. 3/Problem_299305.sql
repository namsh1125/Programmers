-- 프로그래머스 299305번. 대장균들의 자식의 수 구하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/299305

SELECT A.ID, IFNULL(B.CHILD_COUNT, 0) AS CHILD_COUNT
FROM ECOLI_DATA AS A
LEFT JOIN (
    SELECT PARENT_ID, Count(*) AS CHILD_COUNT
    FROM ECOLI_DATA
    GROUP BY PARENT_ID
) AS B
ON A.ID = B.PARENT_ID
ORDER BY A.ID ASC;
