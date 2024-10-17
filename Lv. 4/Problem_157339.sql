-- 프로그래머스 157339번. 특정 기간동안 대여 가능한 자동차들의 대여비용 구하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/157339

-- '세단' 또는 'SUV' 인 자동차
WITH CARS AS (
    SELECT CAR_ID
    FROM CAR_RENTAL_COMPANY_CAR
    WHERE CAR_TYPE = '세단' OR CAR_TYPE = 'SUV'
),

--  2022년 11월 1일부터 2022년 11월 30일까지 대여 가능한 자동차 정보
NOVEMBER_RENTAL_AVAILABLE_CARS AS (
    SELECT *
    FROM CAR_RENTAL_COMPANY_CAR
    WHERE CAR_ID NOT IN (
        SELECT DISTINCT CAR_ID
        FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
        WHERE (START_DATE BETWEEN '2022-11-01' AND '2022-11-30') -- 대여 시작일이 11월인 대여 기록
            OR (END_DATE BETWEEN '2022-11-01' AND '2022-11-30') -- 대여 종료일이 11월인 대여 기록
            OR (START_DATE < '2022-11-01' AND END_DATE > '2022-11-30') -- 대여 기간이 11월에 걸친 대여 기록
    )
),

-- 대여 기간이 '30일 이상'인 할인 요금 정보
DISCOUNT_PLAN AS (
    SELECT *
    FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN
    WHERE DURATION_TYPE = '30일 이상'
),

-- 2022년 11월 1일부터 2022년 11월 30일까지 대여 가능한 세단과 SUV 자동차 대여 정보
RENTAL_INFO AS (
    SELECT A.CAR_ID, A.CAR_TYPE, ROUND(A.DAILY_FEE * ((100 - B.DISCOUNT_RATE) / 100) * 30, 0) AS FEE
    FROM NOVEMBER_RENTAL_AVAILABLE_CARS A
    LEFT JOIN DISCOUNT_PLAN B
    ON A.CAR_TYPE = B.CAR_TYPE
    WHERE A.CAR_ID IN (SELECT CAR_ID FROM CARS)
)

SELECT CAR_ID, CAR_TYPE, FEE
FROM RENTAL_INFO
WHERE FEE >= 500000 AND FEE <= 2000000
ORDER BY FEE DESC, CAR_TYPE ASC, CAR_ID DESC