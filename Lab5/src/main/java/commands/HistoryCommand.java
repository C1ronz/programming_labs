package commands;

import exceptions.ScriptRecursionException;
import managers.CommandManager;
import util.Console;

import java.util.Queue;

/**
 * Команда "history". Выводит историю команд
 * @author C1ronz
 */
public class HistoryCommand extends AbstractCommand {

    CommandManager commandManager;

    public HistoryCommand(CommandManager commandManager) {
        super("history", "вывести последние 11 команд", 0);
        this.commandManager = commandManager;
    }

    /**
     * Выполняет команду.
     */
    @Override
    public void executeInternal(String[] args) {
        Queue<AbstractCommand> history = commandManager.getHistory();
        if (history.isEmpty()){
            Console.println("История пуста.");
        }
        else {
            for (AbstractCommand command : history){
                Console.println(command.getName());
            }
        }

    }
}