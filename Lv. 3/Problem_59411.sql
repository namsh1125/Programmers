-- 프로그래머스 59411번. 오랜 기간 보호한 동물(2)
-- https://school.programmers.co.kr/learn/courses/30/lessons/59411

SELECT I.ANIMAL_ID, I.NAME
FROM ANIMAL_INS AS I
JOIN ANIMAL_OUTS AS O
ON I.ANIMAL_ID = O.ANIMAL_ID
ORDER BY O.DATETIME - I.DATETIME DESC
LIMIT 2;
