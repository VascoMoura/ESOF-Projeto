package projectesof.esof.dtos.conversores;

public interface Conversor<O,I> {
    O converter(I i);
}

