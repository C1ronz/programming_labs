package commands;

import managers.CommandManager;
import util.Console;
import java.util.Map;

/**
 * Команда "help". Выводит справку по командам.
 * @author C1ronz
 */
public class HelpCommand extends AbstractCommand {
    public final CommandManager commandManager;

    public HelpCommand (CommandManager commandManager){
        super("help", "вывести справку по доступным командам", 0);
        this.commandManager = commandManager;
    }

    /**
     * Выполняет команду.
     */
    @Override
    public void executeInternal(String[] args){
        Console.println("Доступные комманды:");
        Map<String, AbstractCommand> commands = commandManager.getCommands();
        for (Map.Entry<String, AbstractCommand> entry : commands.entrySet()){
            Console.println(entry.getKey() + ": " + entry.getValue().getDescription());
        }
    }

}
