
/**
 * Write a description of class SemAutorizacaoException here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SemAutorizacaoException extends Exception
{
    
    public SemAutorizacaoException () {
        System.out.println("___________________________________________________");
        System.out.println("Não possui autorização para realizar esta operação!");
        System.out.println("Volte a introduzir as suas credenciais");
        System.out.println("___________________________________________________");
    }
}
