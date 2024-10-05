-- 프로그래머스 59044번. 오랜 기간 보호한 동물(1)
-- https://school.programmers.co.kr/learn/courses/30/lessons/59044

SELECT NAME, DATETIME
FROM ANIMAL_INS
WHERE ANIMAL_ID NOT IN (SELECT ANIMAL_ID FROM ANIMAL_OUTS)
ORDER BY DATETIME ASC
LIMIT 3;
