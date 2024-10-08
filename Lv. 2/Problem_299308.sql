-- 프로그래머스 299308번. 분기별 분화된 대장균의 개체 수 구하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/299308

SELECT
    CASE
        WHEN MONTH (DIFFERENTIATION_DATE) IN ('1', '2', '3') THEN '1Q'
        WHEN MONTH(DIFFERENTIATION_DATE) IN ('4', '5', '6') THEN '2Q'
        WHEN MONTH(DIFFERENTIATION_DATE) IN ('7', '8', '9') THEN '3Q'
        WHEN MONTH(DIFFERENTIATION_DATE) IN ('10', '11', '12') THEN '4Q'
    END AS QUARTER,
COUNT(*) AS ECOLI_COUNT
FROM ECOLI_DATA
GROUP BY QUARTER
ORDER BY QUARTER;
