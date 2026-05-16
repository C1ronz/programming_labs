package commands;

import exceptions.WrongCommandPattern;


/**
 * Абстрактная команда с именем, описанием и кол-вом принимаемых аргуметов.
 * Содержит метод для проверки кол-ва переданных аргументов.
 * @author C1ronz
 */
public abstract class Command {

    protected final String name;
    protected final String description;
    protected final int numberOfArgs;

    public Command(String name, String description, int numberOfArgs) {
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


    /**
     * Проверяет кол-во переданных аргументов и вызывает уникальнове выполнение для каждой команды.
     * @throws WrongCommandPattern выбрасывает исключение при неверном кол-ве аргументов
     */
    public void execute(String[] args) throws WrongCommandPattern{
        if (args == null || args.length != numberOfArgs)
        {
            throw new WrongCommandPattern(this.getName() + " принимает " + numberOfArgs + " аргументов, получено " + (args == null ? 0 : args.length));
        }
        executeInternal(args);
    }

    /**
     * Уникальное выполнение команды.
     */
    protected abstract void executeInternal(String[] args);
}
