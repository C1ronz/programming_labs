package util;

import exceptions.ScriptRecursionException;
import managers.CommandManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс с основными циклами работы программы.
 * @author C1ronz
 */
public class Runner {

    private boolean runningStatus;
    private boolean scriptStatus = false;
    private CommandManager commandManager;
    private final static List<String> scriptStack = new ArrayList<>();
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
            String[] userCommand = Console.readCommand();
            commandManager.launchCommand(userCommand);
        }
    }

    /**
     * Останавливает работу программы или инерацию скрипта.
     */
    public void stop (){
        if (!scriptStatus) {
            runningStatus = false;
            Console.println("Работа программы завершена");
        }
        else {
            scriptStack.removeLast();
        }
    }

    /**
     * Режим для запуска скрипта.
     * @param filepath путь до скрипта.
     */
    public void runScript (String filepath){
        scriptStatus = true;

        if (scriptStack.contains(filepath)) {
            throw new ScriptRecursionException("Бесконечная рекурсия в скрипте");
        }
        else {
            scriptStack.add(filepath);
        }

        try {
            File file = new File(filepath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            while ((line = reader.readLine()) != null && scriptStack.getLast().equals(filepath)) {
                String[] userCommand = line.trim().split(" ");
                commandManager.launchCommand(userCommand);
            }
            scriptStack.removeLast();
        } catch (Exception e) {
            Console.printErr(e.getMessage());
        }
    }
}
