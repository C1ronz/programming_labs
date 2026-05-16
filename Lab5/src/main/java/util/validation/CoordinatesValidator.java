package util.validation;

import exceptions.ValidationException;
import models.Coordinates;

public class CoordinatesValidator {

    public static Coordinates validate (Coordinates coordinates) {
        if (coordinates == null) throw new ValidationException("coordinates не может быть null");
        return new Coordinates(validateX(coordinates.getX()), validateY(coordinates.getY()));
    }

    /**
     * Валидирует координату X.
     *
     * @param x проверяемая координата
     * @return валидная координата X
     * @throws ValidationException если x &lt;= -600
     */
    public static int validateX(int x) {
        if (x <= -600) throw new ValidationException("x должен быть больше -600");
        return x;
    }

    /**
     * Валидирует координату Y.
     *
     * @param y проверяемая координата
     * @return валидная координата Y
     * @throws ValidationException если y == null или y > 885
     */
    public static Long validateY(Long y) {
        if (y == null) throw new ValidationException("y не может быть null");
        if (y > 885) throw new ValidationException("y не может быть больше 885");
        return y;
    }
}
