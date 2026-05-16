package managers;

import commands.Command;
import exceptions.WrongCommandPattern;
import util.Console;

import java.util.*;

/**
 * Управляет командами.
 * @author C1ronz
 */
public class CommandManager {

    private final int maxHistorySize = 11;

    private final Map<String, Command> commands = new HashMap<>();
    private final Queue<Command> history = new LinkedList<>();


    /**
     * Добвляет команды.
     */
    public void addCommands (Command... commands){
        for (Command command : commands){
            this.commands.put(command.getName(), command);
        }
    }

    /**
     * @return Словарь команд.
     */
    public Map<String, Command> getCommands (){
        return commands;
    }

    /**
     * Вызывает выполнение команды и передает ей аргументы.
     */
    public void launchCommand (String[] line) {
        try {
            List<String> list = new ArrayList<>(Arrays.asList(line));
            Command command = this.commands.get(list.removeFirst());
            command.execute(list.toArray(new String[0]));
            addCommandToHistory(command);
        }
        catch (WrongCommandPattern e){
            Console.printErr(e.getMessage());
        }
        catch (ClassCastException | NullPointerException | IndexOutOfBoundsException e){
            Console.printErr("Неверный формат команды.");
        }
    }

    /**
     * Добвляет команду в историю.
     */
    private void addCommandToHistory (Command command){
        if (history.size() == maxHistorySize) {
            history.poll();
        }
        history.add(command);
    }
    /**
     * @return История команд.
     */
    public Queue<Command> getHistory (){
        return history;
    }

}
