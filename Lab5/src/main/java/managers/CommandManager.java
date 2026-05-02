package managers;

import commands.AbstractCommand;
import exceptions.WrongCommandPattern;
import util.Console;

import java.util.*;

/**
 * Управляет командами.
 * @author C1ronz
 */
public class CommandManager {

    private final int maxHistorySize = 11;

    private final Map<String, AbstractCommand> commands = new HashMap<>();
    private final Queue<AbstractCommand> history = new LinkedList<>();


    /**
     * Добвляет команды.
     */
    public void addCommands (AbstractCommand... commands){
        for (AbstractCommand command : commands){
            this.commands.put(command.getName(), command);
        }
    }

    /**
     * @return Словарь команд.
     */
    public Map<String, AbstractCommand> getCommands (){
        return commands;
    }

    /**
     * Вызывает выполнение команды и передает ей аргументы.
     */
    public void launchCommand (String[] line) {
        List<String> list = new ArrayList<>(Arrays.asList(line));
        try {
            AbstractCommand command = this.commands.get(list.removeFirst());
            command.execute(list.toArray(new String[0]));
            addCommandToHistory(command);
        }
        catch (WrongCommandPattern e){
            Console.printErr(e.getMessage());
        }
        catch (ClassCastException | NullPointerException | IndexOutOfBoundsException e){
            Console.printErr("Ошибка: неверный формат команды");
        }
    }

    /**
     * Добвляет команду в историю.
     */
    private void addCommandToHistory (AbstractCommand command){
        if (history.size() == maxHistorySize) {
            history.poll();
        }
        history.add(command);
    }
    /**
     * @return История команд.
     */
    public Queue<AbstractCommand> getHistory (){
        return history;
    }

}
