package managers;

import commands.AbstractCommand;
import exceptions.WrongCommandPattern;

import java.util.*;

public class CommandManager {

    private boolean RunningStatus = false;
    private final int MAX_HISTORY_SIZE = 11;

    private final CollectionManager manager;
    private final Map<String, AbstractCommand> commands = new HashMap<>();
    private final Console console;
    private Queue<AbstractCommand> history = new LinkedList<>();

    public CommandManager (CollectionManager manager, Console console){
        this.manager = manager;
        this.console = console;
    }
    
    public void addCommands (AbstractCommand... commands){
        for (AbstractCommand command : commands){
            this.commands.put(command.getName(), command);
        }
    }

    public Map<String, AbstractCommand> getCommands (){
        return commands;
    }

    public void run (){
        RunningStatus = true;
        Console.println("Программа запущена в интерактивном режиме, введите help для просмотра команд");

        while (RunningStatus) {
            String[] userCommand = console.readCommand();
            launchCommand(userCommand);
        }
    }

    public void stop (){
        RunningStatus = false;
        Console.println("Работа программы завершена");
    }

    private void launchCommand (String[] line) {
        List<String> list = new ArrayList<>(Arrays.asList(line));
        try {
            AbstractCommand command = this.commands.get(list.remove(0));
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

    private void addCommandToHistory (AbstractCommand command){
        if (history.size() == MAX_HISTORY_SIZE) {
            history.poll();
        }
        history.add(command);
    }

    public Queue<AbstractCommand> getHistory (){
        return history;
    }

}
