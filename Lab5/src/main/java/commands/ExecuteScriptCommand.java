package commands;

import exceptions.ScriptRecursionException;
import exceptions.WrongCommandPattern;
import util.Console;
import util.Runner;

/**
 * Команда "execute_script". Запускает выполнение скрипта из файла.
 * @author C1ronz
 */
public class ExecuteScriptCommand extends AbstractCommand {

    Runner runner;

    public ExecuteScriptCommand(Runner runner){
        super("execute_script", "запустить выполнение скрипта (формат: команда путь)", 1);
        this.runner = runner;
    }

    /**
     * Выполняет команду.
     */
    @Override
    public void executeInternal(String[] args) {
        try {
            runner.runScript(args[0]);
        }
        catch (ScriptRecursionException e){
            Console.printErr(e.getMessage());
        }
    }

}
