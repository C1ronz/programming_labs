package commands;

import exceptions.WrongCommandPattern;

public abstract class AbstractCommand {

    protected final String name;
    protected final String description;
    protected final int numberOfArgs;

    public AbstractCommand(String name, String description, int numberOfArgs) {
        this.name = name;
        this.description = description;
        this.numberOfArgs = numberOfArgs;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public void execute(String[] args) throws WrongCommandPattern{
        if (args == null || args.length != numberOfArgs)
        {
            throw new WrongCommandPattern(this.getName() + " принимает " + numberOfArgs + " аргументов, получено " + args.length);
        }

        executeInternal(args);

    };

    protected abstract void executeInternal(String[] args);
}
