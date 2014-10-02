package ru.vsu.csf.enlightened.GameObjects;

/**
 * Класс игрока
 */
public class Player {

    /**
     * Количество набранных очков
     */
    private int score;

    /**
     * Количество пройденных уровней - общий прогресс по игре
     */
    private int passedLevelCount;

    /**
     * Никнейм
     */
    private String nickName;

    public Player(String nick) {
        score = 0;
        passedLevelCount = 0;
        nickName = nick;
    }

    public int getPassedLevelCount() {
        return passedLevelCount;
    }

    public void setPassedLevelCount(int passedLevelCount) {
        this.passedLevelCount = passedLevelCount;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score) {
        this.score += score;
    }
}