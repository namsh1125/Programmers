/*
 * 프로그래머스 72410번. 신규 아이디 추천
 * https://school.programmers.co.kr/learn/courses/30/lessons/72410
 */

class Solution {

    /**
     * 추천 아이디를 반환하는 메소드
     *
     * @param newId 신규 유저가 입력한 아이디
     * @return "네오"가 설계한 7단계의 처리 과정을 거친 후의 추천 아이디
     */
    public String solution(String newId) {
        String step1 = step1(newId);
        String step2 = step2(step1);
        String step3 = step3(step2);
        String step4 = step4(step3);
        String step5 = step5(step4);
        String step6 = step6(step5);
        return step7(step6);
    }

    /**
     * 7단계) 아이디 길이가 2자 이하라면, 아이디의 마지막 문자를 길이가 3이 될 때까지 반복해서 끝에 붙이는 메소드
     *
     * @param newId 7단계를 적용할 아이디
     * @return 7단계를 적용한 아이디
     */
    private String step7(String newId) {
        StringBuilder sb = new StringBuilder(newId);

        while (sb.length() <= 2) {
            sb.append(sb.charAt(sb.length() - 1));
        }

        return sb.toString();
    }

    /**
     * 6단계) 아이디 길이가 16자 이상이면, 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거하는 메소드
     * 만약 제거 후 마침표(.)가 아이디의 끝에 위치한다면, 끝에 위치한 마침표(.) 문자를 제거
     *
     * @param newId 6단계를 적용할 아이디
     * @return 6단계를 적용한 아이디
     */
    private String step6(String newId) {
        StringBuilder sb = new StringBuilder(newId);

        if (sb.length() >= 16) {
            sb.delete(15, sb.length());

            for (int i = sb.length() - 1; i >= 0; i--) {
                if (sb.charAt(i) == '.') {
                    sb.deleteCharAt(i);
                } else {
                    break;
                }
            }
        }

        return sb.toString();
    }

    /**
     * 5단계) 아이디가 빈 문자열이라면, "a"를 대입하는 메소드
     *
     * @param newId 5단계를 적용할 아이디
     * @return 5단계를 적용한 아이디
     */
    private String step5(String newId) {
        if (newId.isEmpty()) {
            return "a";
        }
        return newId;
    }

    /**
     * 4단계) 마침표(.)가 처음이나 끝에 위치한다면 제거하는 메소드
     *
     * @param newId 4단계를 적용할 아이디
     * @return 4단계를 적용한 아이디
     */
    private String step4(String newId) {
        StringBuilder sb = new StringBuilder(newId);

        if (sb.length() > 0 && sb.charAt(0) == '.') {
            sb.deleteCharAt(0);
        }

        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '.') {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    /**
     * 3단계) 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환하는 메소드
     *
     * @param newId 3단계를 적용할 아이디
     * @return 3단계를 적용한 아이디
     */
    private String step3(String newId) {
        StringBuilder sb = new StringBuilder();
        sb.append(newId.charAt(0));

        for (int i = 1; i < newId.length(); i++) {
            if (newId.charAt(i) == '.' && newId.charAt(i - 1) == '.') {
                continue;
            }
            sb.append(newId.charAt(i));
        }

        return sb.toString();
    }

    /**
     * 2단계) 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거하는 메소드
     *
     * @param newId 2단계를 적용할 아이디
     * @return 2단계를 적용한 아이디
     */
    private String step2(String newId) {
        StringBuilder sb = new StringBuilder();

        for (char c : newId.toCharArray()) {
            if (Character.isLowerCase(c) || Character.isDigit(c) || c == '-' || c == '_' || c == '.') {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 1단계) 모든 대문자를 대응되는 소문자로 치환하는 메소드
     *
     * @param newId 1단계를 적용할 아이디
     * @return 1단계를 적용한 아이디
     */
    private String step1(String newId) {
        return newId.toLowerCase();
    }
}
