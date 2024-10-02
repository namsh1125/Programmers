-- 프로그래머스 276035번. FrontEnd 개발자 찾기
-- https://school.programmers.co.kr/learn/courses/30/lessons/276035

SELECT DISTINCT D.ID, D.EMAIL, D.FIRST_NAME, D.LAST_NAME
FROM DEVELOPERS AS D
JOIN (
    SELECT NAME, CODE
    FROM SKILLCODES
    WHERE CATEGORY = 'Front End'
) AS S
WHERE D.SKILL_CODE & S.CODE > 0
ORDER BY ID ASC
