-- 프로그래머스 151138번. 자동차 대여 기록에서 장기/단기 대여 구분하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/151138

SELECT HISTORY_ID,
       CAR_ID,
       DATE_FORMAT(START_DATE, '%Y-%m-%d') AS START_DATE,
       DATE_FORMAT(END_DATE, '%Y-%m-%d')   AS END_DATE,
       CASE
           WHEN DATEDIFF(END_DATE, START_DATE) >= 29 THEN '장기 대여'
           ELSE '단기 대여'
           END                             AS RENTAL_TYPE
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE START_DATE LIKE '2022-09-%'
ORDER BY HISTORY_ID DESC;
