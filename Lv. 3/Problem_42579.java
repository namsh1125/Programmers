/*
 * 프로그래머스 42579번. 베스트앨범
 * https://school.programmers.co.kr/learn/courses/30/lessons/42579
 */

import java.util.ArrayList;
import java.util.HashMap;

class Solution {

    /**
     * @param genres 노래의 장르를 나타내는 문자열 배열
     * @param plays  노래별 재생 횟수를 나타내는 정수 배열
     * @return 베스트 앨범에 들어갈 노래의 고유 번호 (순서대로)
     */
    public int[] solution(String[] genres, int[] plays) {
        ArrayList<Integer> answer = new ArrayList<>();

        // 전처리 진행
        ArrayList<Music> musicList = new ArrayList<>();
        for (int i = 0; i < genres.length; i++) {
            musicList.add(new Music(genres[i], plays[i], i));
        }

        // 장르별 재생 횟수 계산
        HashMap<String, Integer> genrePlay = new HashMap<>();
        for (Music music : musicList) {
            if (genrePlay.containsKey(music.getGenre())) {
                genrePlay.put(music.getGenre(), genrePlay.get(music.getGenre()) + music.getPlay());
            } else {
                genrePlay.put(music.getGenre(), music.getPlay());
            }
        }

        // 장르별 재생 횟수 정렬하고 가장 많이 재생된 2개의 노래를 수록
        genrePlay.entrySet().stream()
                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .forEach(entry ->
                        musicList.stream()
                                .filter(music -> music.getGenre().equals(entry.getKey()))
                                .sorted((o1, o2) -> o2.getPlay() - o1.getPlay())
                                .limit(2)
                                .forEach(music ->
                                        answer.add(music.getIndex())
                                )
                );

        return answer.stream()
                .mapToInt(i -> i)
                .toArray();
    }
}

class Music {
    private String genre;
    private int play;
    private int index;

    public Music(String genre, int play, int index) {
        this.genre = genre;
        this.play = play;
        this.index = index;
    }

    public String getGenre() {
        return genre;
    }

    public int getPlay() {
        return play;
    }

    public int getIndex() {
        return index;
    }
}
