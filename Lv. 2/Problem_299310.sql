-- 프로그래머스 299310번. 연도별 대장균 크기의 편차 구하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/299310

SELECT
    YEAR(A.DIFFERENTIATION_DATE) AS YEAR,
    ABS(A.SIZE_OF_COLONY - B.MAX_SIZE) AS YEAR_DEV,
    A.ID
FROM ECOLI_DATA A
    JOIN (
        SELECT
            YEAR(DIFFERENTIATION_DATE) AS YEAR,
            MAX(SIZE_OF_COLONY) AS MAX_SIZE
        FROM ECOLI_DATA
        GROUP BY YEAR(DIFFERENTIATION_DATE)
    ) AS B
ON YEAR (A.DIFFERENTIATION_DATE) = B.YEAR
ORDER BY YEAR ASC, YEAR_DEV ASC;
