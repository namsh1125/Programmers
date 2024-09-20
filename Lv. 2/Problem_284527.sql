-- 프로그래머스 284527번. 조건에 맞는 사원 정보 조회하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/284527

SELECT G.SCORE, E.EMP_NO, E.EMP_NAME, E.POSITION, E.EMAIL
FROM HR_EMPLOYEES AS E
JOIN (
    SELECT EMP_NO, YEAR, SUM(SCORE) AS SCORE
    FROM HR_GRADE
    GROUP BY EMP_NO, YEAR
) AS G
ON E.EMP_NO = G.EMP_NO
WHERE G.YEAR = 2022
ORDER BY G.SCORE DESC
LIMIT 1;