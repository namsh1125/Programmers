/*
 * 프로그래머스 43163번. 단어 변환
 * https://school.programmers.co.kr/learn/courses/30/lessons/43163
 */

class Solution {

    /**
     * 주어진 단어 집합을 이용하여 begin을 target으로 변환하는 과정을 최소 단계로 구하는 함수
     *
     * @param begin  시작 단어
     * @param target 목표 단어
     * @param words  단어의 집합 (3 <= 단어의 길이는 <= 10, 모든 단어의 길이는 같다. 3 <= words의 길이 <= 50)
     * @return 최소 몇 단계의 과정을 거쳐 begin을 target으로 변환할 수 있는지
     */
    public int solution(String begin, String target, String[] words) {
        return dfs(begin, target, words, new boolean[words.length], 0);
    }

    private int dfs(String begin, String target, String[] words, boolean[] visited, int depth) {
        if (begin.equals(target)) { // 목표 단어에 도달한 경우
            return depth; // 변환 횟수 반환
        }

        if (depth == words.length) { // 모든 단어를 사용한 경우
            return 0; // 변환할 수 없다고 반환
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (!visited[i] && isConvertible(begin, words[i])) { // 변환 가능한 단어이며, 아직 사용하지 않은 단어인 경우
                visited[i] = true;

                // 다음 단계로 진행
                int result = dfs(words[i], target, words, visited, depth + 1);
                if (result != 0) {
                    answer = Math.min(answer, result);
                }

                visited[i] = false;
            }
        }

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    private boolean isConvertible(String from, String to) {
        int diff = 0; // 다른 문자의 개수
        for (int i = 0; i < from.length(); i++) {
            if (from.charAt(i) != to.charAt(i)) {
                diff++;
            }

            if (diff > 1) {
                return false;
            }
        }

        return diff == 1;
    }
}

class Info implements Comparable<Info> {
    private String word; // 현재 단어
    private int count; // 현재까지 변환 횟수

    public Info(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }

    @Override
    public int compareTo(Info o) {
        return this.count - o.count; // 변환 횟수가 적은 순서대로 정렬
    }
}
