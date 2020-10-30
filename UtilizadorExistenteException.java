/**
 * Write a description of class UtilizadorExistenteExeption here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UtilizadorExistenteException extends Exception
{

    public UtilizadorExistenteException () {
        System.out.println("Este utilizador já existe!");
    }
}