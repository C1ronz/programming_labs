package managers;

import commands.AbstractCommand;
import exceptions.WrongCommandPattern;
import util.Console;

import java.util.*;

public class CommandManager {

    private final int MAX_HISTORY_SIZE = 11;

    private final Map<String, AbstractCommand> commands = new HashMap<>();
    private Queue<AbstractCommand> history = new LinkedList<>();

    public void addCommands (AbstractCommand... commands){
        for (AbstractCommand command : commands){
            this.commands.put(command.getName(), command);
        }
    }

    public Map<String, AbstractCommand> getCommands (){
        return commands;
    }


    public void launchCommand (String[] line) {
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
