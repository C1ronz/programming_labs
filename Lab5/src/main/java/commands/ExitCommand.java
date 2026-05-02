package commands;

import util.Runner;

/**
 * Команда "exit. Завершает работу программы.
 * @author C1ronz
 */
public class ExitCommand extends AbstractCommand {

    private final Runner runner;

    public ExitCommand(Runner runner) {
        super("exit", "завершить программу (без сохранения в файл)", 0);
        this.runner = runner;
    }

    /**
     * Выполняет команду.
     */
    @Override
    public void executeInternal(String[] args) {
        runner.stop();
    }
}

