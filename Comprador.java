/**
 * Write a description of class Comprador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.io.Serializable;

public class Comprador extends Utilizador implements Serializable
{
    /** Lista de imóveis favoritos de um comprador */
    private List<Imovel> listaImoveisFavoritos;
    
    /**
     * Construtor parametrizado
     */
    public Comprador(String email, String nome, String password, String morada, GregorianCalendar dataNascimento, boolean estaAutenticado,List<Imovel> list){
        super(email, nome, password, morada, dataNascimento, estaAutenticado);    
        this.listaImoveisFavoritos = list;
    }
    
    
    /**
     * Construtor vazio
     */
    public Comprador(){
        super(" ", " ", " ", " ", new GregorianCalendar(), false);
        this.listaImoveisFavoritos = new ArrayList<>();
    }
    
    
    /**
     * Construtor por cópia
     */
    public Comprador(Comprador comprador){
        super(comprador.getEmail(), comprador.getNome(), comprador.getPassword(), comprador.getMorada(), comprador.getDataNascimento(), comprador.estaAutenticado());    
        this.listaImoveisFavoritos = comprador.getListaFavoritos();
    }
    
    
    /**
     * Obter lista de imóveis favoritos
     * @return listaImoveisFavoritos
     */
    public List<Imovel> getListaFavoritos(){
        List<Imovel> lista = new ArrayList<>();
        for(Imovel i:listaImoveisFavoritos){
            lista.add(i.clone());
        }
        return lista;
    }
    
    public void addFavorito(Imovel i){
        listaImoveisFavoritos.add(i);
    }
    
    public void remFavorito(Imovel i){
        listaImoveisFavoritos.remove(i);
    }
    
    /**
     * Altera a lista de favoritos
     * @param listaImoveisFavoritos
     */
    public void setListaFavoritos(List<Imovel> listaImoveisFavoritos){
        this.listaImoveisFavoritos = listaImoveisFavoritos;
    }
   
    
     /**
     * Método equals
     */
    public boolean equals(Object o){
        if(this==o) return true;
        if(this==null||this.getClass()!=o.getClass()) return false;
        Comprador cp = (Comprador) o;
        if(super.equals(cp)==false) return false;
        for(Imovel imovel: cp.getListaFavoritos())
            if(this.listaImoveisFavoritos.contains(imovel)==true) ;
            else return false;
        if(cp.getListaFavoritos().size()!=this.listaImoveisFavoritos.size()) return false;
        return true;
    }
    
    
    /**
     * Método toString, que retorna a string com as informações dos imóveis.
     */
    public String toString(){
        StringBuffer sb=new StringBuffer();
        sb.append(super.toString());
        return sb.toString();
    }
    
    /**
     * Método que retorna o clone da lista favoritos.
     */
    public Comprador clone(){
        return new Comprador(this);
    }
}
