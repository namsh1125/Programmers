-- 프로그래머스 284528번. 연간 평가점수에 해당하는 평가 등급 및 성과금 조회하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/284528

SELECT
    A.EMP_NO,
    B.EMP_NAME,
    CASE
        WHEN A.SCORE >= 96 THEN 'S'
        WHEN A.SCORE >= 90 THEN 'A'
        WHEN A.SCORE >= 80 THEN 'B'
        ELSE 'C'
    END AS GRADE,
    CASE
        WHEN A.SCORE >= 96 THEN B.SAL * 0.2
        WHEN A.SCORE >= 90 THEN B.SAL * 0.15
        WHEN A.SCORE >= 80 THEN B.SAL * 0.1
        ELSE 0
    END AS BONUS
FROM (
    SELECT EMP_NO, AVG(SCORE) AS SCORE
    FROM HR_GRADE
    GROUP BY EMP_NO
) AS A
JOIN HR_EMPLOYEES AS B
ON A.EMP_NO = B.EMP_NO
GROUP BY EMP_NO
ORDER BY EMP_NO;
