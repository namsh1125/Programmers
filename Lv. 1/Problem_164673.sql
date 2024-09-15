-- 프로그래머스 164673번. 조건에 부합하는 중고거래 댓글 조회하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/164673

SELECT A.TITLE,
       A.BOARD_ID,
       B.REPLY_ID,
       B.WRITER_ID,
       B.CONTENTS,
       DATE_FORMAT(B.CREATED_DATE, '%Y-%m-%d') AS CREATED_DATE
FROM USED_GOODS_BOARD AS A
         JOIN USED_GOODS_REPLY AS B
              ON A.BOARD_ID = B.BOARD_ID
WHERE YEAR (A.CREATED_DATE) = '2022'
  AND MONTH (A.CREATED_DATE) = '10'
ORDER BY B.CREATED_DATE ASC, A.TITLE ASC;
