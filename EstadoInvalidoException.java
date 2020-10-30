
/**
 * Write a description of class EstadoInvalidoException here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EstadoInvalidoException extends Exception
{
    public EstadoInvalidoException () {
        System.out.println("Tentou introduzir um estado inválido! Estados possíveis: 'em venda', 'reservado' ou 'vendido'");
    }
}
