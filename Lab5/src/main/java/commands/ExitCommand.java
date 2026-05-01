package commands;

import managers.CommandManager;
import util.Runner;

public class ExitCommand extends AbstractCommand {

    Runner runner;

    public ExitCommand(Runner runner) {
        super("exit", "завершить программу (без сохранения в файл)", 0);
        this.runner = runner;
    }

    @Override
    public void executeInternal(String[] args) {
        runner.stop();
    }
}

