/*
 * 프로그래머스 118666번. 성격 유형 검사하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/118666
 */

class Solution {
    
    private int[] score = new int[8]; // 성격 유형 점수
    private static final char[] types = {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'}; // 성격 유형

    public String solution(String[] survey, int[] choices) {
        getScore(survey, choices, score);
        return getPersonalType(score);
    }

    /**
     * 성격 유형 점수를 계산하는 메소드
     */
    private void getScore(String[] survey, int[] choices, int[] score) {
        for (int i = 0; i < survey.length; i++) {
            char[] split = survey[i].toCharArray();

            if (1 <= choices[i] && choices[i] <= 3) {
                score[getIndex(split[0])] += Math.abs(choices[i] - 4);
            } else {
                score[getIndex(split[1])] += Math.abs(choices[i] - 4);
            }
        }
    }

    /**
     * 성격 유형을 찾는 메소드
     */
    private String getPersonalType(int[] score) {
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            if (score[2 * i] == score[2 * i + 1]) {
                // 사전 순으로 빠른 성격 유형을 더한다.
                answer.append(types[2 * i] < types[2 * i + 1] ? types[2 * i] : types[2 * i + 1]);

            } else if (score[2 * i] > score[2 * i + 1]) {
                answer.append(types[2 * i]);
            } else {
                answer.append(types[2 * i + 1]);
            }
        }

        return answer.toString();
    }

    private int getIndex(char c) {
        return switch (c) {
            case 'R' -> 0;
            case 'T' -> 1;
            case 'C' -> 2;
            case 'F' -> 3;
            case 'J' -> 4;
            case 'M' -> 5;
            case 'A' -> 6;
            case 'N' -> 7;
            default -> -1;
        };
    }
}
