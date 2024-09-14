-- 프로그래머스 131528번. 나이 정보가 없는 회원 수 구하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/131528

SELECT COUNT(*) AS USERS
FROM USER_INFO
WHERE AGE IS NULL;
