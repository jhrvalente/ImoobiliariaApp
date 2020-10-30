
/**
 * Write a description of class ImovelInexistenteException here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ImovelInexistenteException extends Exception
{
   public ImovelInexistenteException () {
        System.out.println("___________________________________________________");
        System.out.println("Não existem imóveis que cumpram os requisitos ");
        System.out.println("pretendidos!");
        System.out.println("___________________________________________________");
    }
}
