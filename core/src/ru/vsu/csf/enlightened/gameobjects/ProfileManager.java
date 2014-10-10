package ru.vsu.csf.enlightened.gameobjects;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, отвечающий за хранение прогресса игроков и управления различными игровыми профилями
 */
public class ProfileManager {

    //region Singleton
    private static ProfileManager instance;

    private ProfileManager() {
        players = new ArrayList<Player>();
    }

    public static ProfileManager getManager() {
        if (instance == null)
            instance = new ProfileManager();
        return instance;
    }
    //endregion


    List<Player> players;


    /**
     * Метод, который загружает информацию об игроках из файла
     * @param f Файл с записями об игроках
     * @return true, если загрузка прошла успешно
     */
    public boolean loadData(File f) {
        try {
            //TODO: read from file, add to list of players

            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * Метод, который записываем информацию об игроках в файл
     * @param f Файл с записями об игроках
     * @return true, если запись прошла успешно
     */
    public boolean saveData(File f) {
        try {
            //TODO: write to file

            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * Метод, выставляющий в классе игры текущего игрока
     * @param index Индекс выбранного игрока в списке
     */
    public void chooseProfile(int index) {
        Game.getGame().setCurrentPlayer(players.get(index));
    }


}
