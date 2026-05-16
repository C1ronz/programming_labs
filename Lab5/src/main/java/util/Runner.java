package util;

import exceptions.EofException;
import exceptions.ScriptRecursionException;
import managers.CommandManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Класс с основными циклами работы программы.
 * @author C1ronz
 */
public class Runner {

    private boolean runningStatus;
    private boolean scriptStatus = false;
    private CommandManager commandManager;
    private final static Deque<String> scriptStack = new ArrayDeque<>();
    private String filePath;

    public Runner (String filePath){
        this.filePath = filePath;
    }

    public String getFilePath() {return filePath;}
    public void setFilePath(String filePath) {this.filePath = filePath;}

    public void setCommandManager (CommandManager commandManager){
        this.commandManager = commandManager;
    }

    /**
     * Интерактивный режим.
     */
    public void runInteractive (){
        runningStatus = true;
        Console.println("Программа запущена в интерактивном режиме, введите help для просмотра команд");

        while (runningStatus) {
            try {
                String[] userCommand = Console.readCommand();
                commandManager.launchCommand(userCommand);
            }
            catch (EofException e){
                stop();
            }
        }
    }

    /**
     * Останавливает работу программы или инерацию скрипта.
     */
    public void stop() {
        if (!scriptStatus) {
            runningStatus = false;
            Console.println("Работа программы завершена");
        } else {
            if (!scriptStack.isEmpty()) {
                scriptStack.removeLast();
            }
            scriptStatus = !scriptStack.isEmpty();
            Console.println("Выход из скрипта");
        }
    }

    /**
     * Режим для запуска скрипта.
     * @param filepath путь до скрипта.
     */
    public void runScript(String filepath) {
        scriptStatus = true;

        String fullPath = "scripts/" + filepath;

        if (scriptStack.contains(fullPath)) {
            throw new ScriptRecursionException("Бесконечная рекурсия в скрипте");
        }

        scriptStack.add(fullPath);

        try {
            File file = new File(fullPath);
            if (!file.exists()) {
                Console.printErr("Файл скрипта не найден: " + fullPath);
                return;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;

            while (scriptStatus && (line = reader.readLine()) != null) {
                String trimmedLine = line.trim();
                if (trimmedLine.isEmpty() || trimmedLine.startsWith("#")) {
                    continue;
                }

                String[] userCommand = trimmedLine.split("\\s+");
                commandManager.launchCommand(userCommand);
            }
        } catch (Exception e) {
            Console.printErr("Ошибка при выполнении скрипта: " + e.getMessage());
        } finally {
            if (!scriptStack.isEmpty()) {
                scriptStack.removeLast();
            }
            scriptStatus = !scriptStack.isEmpty();
        }
    }
}
