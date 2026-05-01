package commands;

import managers.CommandManager;
import managers.Console;

import java.util.Queue;

public class HistoryCommand extends AbstractCommand {

    CommandManager commandManager;

    public HistoryCommand(CommandManager commandManager) {
        super("history", "вывести последние 11 команд", 0);
        this.commandManager = commandManager;
    }

    @Override
    public void executeInternal(String[] args) {
        Queue<AbstractCommand> history = commandManager.getHistory();
        if (history.isEmpty()){
            Console.println("История пуста");
        }
        else {
            for (AbstractCommand command : history){
                Console.println(command.getName());
            }
        }

    }
}