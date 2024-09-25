/*
 * 프로그래머스 49993번. 스킬트리
 * https://school.programmers.co.kr/learn/courses/30/lessons/49993
 */

class Solution {

    /**
     * 선행 스킬 순서와 유저들이 만든 스킬트리를 기반으로 가능한 스킬트리 개수를 반환하는 메소드
     *
     * @param skill      선행 스킬 순서
     * @param skillTrees 유저들이 만든 스킬트리를 담은 배열
     * @return 가능한 스킬트리 개수
     */
    public int solution(String skill, String[] skillTrees) {
        int answer = 0;

        for (String skillTree : skillTrees) {
            // 스킬 순서에서 선행 스킬 순서에 포함되지 않는 스킬을 제거
            String regex = "[^" + skill + "]";
            String filteredSkillTree = skillTree.replaceAll(regex, "");

            // 선행 스킬 순서와 유저가 만든 스킬트리가 일치하는지 확인
            if (skill.startsWith(filteredSkillTree)) {
                answer++;
            }
        }

        return answer;
    }
}
