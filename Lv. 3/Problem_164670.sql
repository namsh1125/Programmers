-- 프로그래머스 164670번. 조건에 맞는 사용자 정보 조회하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/164670

SELECT USER_ID,
       NICKNAME,
       CONCAT(CITY, ' ', STREET_ADDRESS1, ' ', STREET_ADDRESS2)                           AS 전체주소,
       CONCAT(SUBSTRING(TLNO, 1, 3), '-', SUBSTRING(TLNO, 4, 4), '-', SUBSTRING(TLNO, 8)) AS 전화번호
FROM USED_GOODS_USER
WHERE USER_ID IN (
    SELECT WRITER_ID
    FROM USED_GOODS_BOARD
    GROUP BY WRITER_ID
    HAVING COUNT(*) >= 3
)
ORDER BY USER_ID DESC;
