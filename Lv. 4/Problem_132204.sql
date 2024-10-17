-- 프로그래머스 132204번. 취소되지 않은 진료 예약 조회하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/132204

SELECT T.APNT_NO, T.PT_NAME, T.PT_NO, T.MCDP_CD, D.DR_NAME, T.APNT_YMD
FROM DOCTOR AS D
JOIN (
    SELECT A.APNT_NO, P.PT_NAME, A.PT_NO, A.MCDP_CD, A.MDDR_ID, A.APNT_YMD
    FROM APPOINTMENT AS A
    JOIN PATIENT AS P
    ON A.PT_NO = P.PT_NO
    WHERE DATE_FORMAT(A.APNT_YMD, '%Y-%m-%d') = '2022-04-13' AND A.APNT_CNCL_YN = 'N' AND A.MCDP_CD = 'CS'
) AS T
ON D.DR_ID = T.MDDR_ID
ORDER BY T.APNT_YMD