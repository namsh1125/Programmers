-- 프로그래머스 132202번. 진료과별 총 예약 횟수 출력하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/132202

SELECT MCDP_CD AS '진료과 코드', COUNT (*) AS '5월예약건수'
FROM APPOINTMENT
WHERE YEAR (APNT_YMD) = 2022 AND MONTH (APNT_YMD) = 5
GROUP BY MCDP_CD
ORDER BY COUNT (*) ASC, MCDP_CD ASC;