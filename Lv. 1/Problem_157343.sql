-- 프로그래머스 157343번. 특정 옵션이 포함된 자동차 리스트 구하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/157343

SELECT CAR_ID, CAR_TYPE, DAILY_FEE, OPTIONS
FROM CAR_RENTAL_COMPANY_CAR
WHERE OPTIONS LIKE '%네비게이션%'
ORDER BY CAR_ID DESC;