-- 프로그래머스 276013번. Python 개발자 찾기
-- https://school.programmers.co.kr/learn/courses/30/lessons/276013

SELECT ID, EMAIL, FIRST_NAME, LAST_NAME
FROM DEVELOPER_INFOS
WHERE SKILL_1 = 'Python' OR SKILL_2 = 'Python' OR SKILL_3 = 'Python'
ORDER BY ID;
