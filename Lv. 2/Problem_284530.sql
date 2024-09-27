-- 프로그래머스 284530번. 연도 별 평균 미세먼지 농도 조회하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/284530

SELECT YEAR (YM) AS 'YEAR', ROUND(AVG (PM_VAL1), 2) AS 'PM10', ROUND(AVG (PM_VAL2), 2) AS 'PM2.5'
FROM AIR_POLLUTION
WHERE LOCATION1 = '경기도' AND LOCATION2 = '수원'
GROUP BY YEAR
ORDER BY YEAR ASC;
