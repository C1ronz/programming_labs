package commands;

import exceptions.ScriptRecursionException;
import util.Console;
import util.Runner;

public class ExecuteScriptCommand extends AbstractCommand {

    Runner runner;

    public ExecuteScriptCommand(Runner runner){
        super("execute_script", "запустить выполнение скрипта (формат: команда путь)", 1);
        this.runner = runner;
    }

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
