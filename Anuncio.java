
import java.util.*;
import java.io.Serializable;

/**
 * Write a description of class Anuncio here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Anuncio implements Serializable
{
    private Imovel imovel;
    private List<Consulta> listaConsultas;
    
    public Anuncio(Imovel imovel){
        this.imovel = imovel;
        listaConsultas = new ArrayList<>();
    }
    
    public Anuncio(Anuncio anuncio){
        this.imovel = anuncio.getImovel();
        this.listaConsultas = anuncio.getListaConsultas();
    }
    
    public Imovel getImovel(){
        return imovel;
    }
    
    public List<Consulta> getListaConsultas(){
        List<Consulta> lista = new ArrayList<>();
        
        for(Consulta consulta:listaConsultas){
            lista.add(consulta);
        }
        return lista;
    }
       
    public void insereConsulta(Consulta c){
        if(listaConsultas==null) listaConsultas = new ArrayList<>();
        listaConsultas.add(c);
    }
    
    public void setImovel(Imovel imovel){
        this.imovel = imovel;
    }
    
    public void setListaConsultas(List<Consulta> lista){
        this.listaConsultas = lista;
    }
    
    public int numConsultas(){
        return listaConsultas.size();
    }
    
    public boolean equals(Object obj){
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Anuncio o = (Anuncio) obj;
        boolean b = true;
        Iterator<Consulta> i = o.getListaConsultas().iterator();
        for(;b && i.hasNext();){
            Consulta c = (Consulta) i.next();
            Iterator<Consulta> i2 = listaConsultas.iterator();
            for(;b && i2.hasNext();){
                Consulta c2 = (Consulta) i2.next();
                b = b && c2.equals(c);
            }
        }
        return (b && imovel.equals(o.getImovel()));
    }
    
    public Anuncio clone(){
        return new Anuncio(this);
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(imovel.toString()).append("\n");
        sb.append("Número de Consultas: ").append(listaConsultas.size()).append("\n");
        return sb.toString();
    }
        public int hashCode()
{
	return imovel.hashCode();
}
    
}
