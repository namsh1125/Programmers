-- 프로그래머스 131535번. 조건에 맞는 회원수 구하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/131535

SELECT COUNT(*) AS USERS
FROM USER_INFO
WHERE DATE_FORMAT(JOINED, '%Y') = '2021'
  AND AGE >= 20
  AND AGE < 30;
