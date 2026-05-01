package commands;

import managers.CommandManager;

public class ExitCommand extends AbstractCommand {

    CommandManager commandManager;

    public ExitCommand(CommandManager commandManager) {
        super("exit", "завершить программу (без сохранения в файл)", 0);
        this.commandManager = commandManager;
    }

    @Override
    public void executeInternal(String[] args) {
        commandManager.stop();
    }
}

