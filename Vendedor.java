/**
 * Write a description of class Vendedor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Map;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.io.Serializable;

public class Vendedor extends Utilizador implements Serializable
{  
    /** Lista de imóveis que um vendedor disponibiliza para venda */
    private List<Imovel> portefolioImoveisEmVenda, historicoImoveisVendidos;
    private List<Consulta> ultConsultas;
    private int elemsStack;
    
    
    /**
     * Construtor parametrizado
     * @param nAnuncios
     * @param nVisualizacoes
     * @param nVendas
     * @param nImoveisEmVenda
     * @param nImoveisVendidos
     * @param nImoveis
     */
    public Vendedor(String email, String nome, String password, String morada, GregorianCalendar dataNascimento, boolean estaAutenticado){
        super(email,nome,password,morada,dataNascimento,estaAutenticado);
        this.portefolioImoveisEmVenda = new ArrayList<>();
        this.historicoImoveisVendidos = new ArrayList<>();
    }
    /**
     * Construtor vazio
     */
    public Vendedor(){
        super();
        this.portefolioImoveisEmVenda = new ArrayList<>();
        this.historicoImoveisVendidos = new ArrayList<>();
    }
    
    
    /**
     * Construtor por cópia
     * @param vendedor
     */
    public Vendedor(Vendedor vendedor){
        super(vendedor);
        this.portefolioImoveisEmVenda = vendedor.getPortefolioImoveisEmVenda();
        this.historicoImoveisVendidos = vendedor.getHistoricoImoveisVendidos();
    }
    
    
    /**
     * Obter portefolio de imóveis à venda
     * return lista
     */
    public List<Imovel> getPortefolioImoveisEmVenda(){
        List<Imovel> lista = new ArrayList<>();
        for(Imovel imovel:portefolioImoveisEmVenda){
            lista.add(imovel);
        }
        return lista;
    }
    
    public void insereStack(Consulta c){
        if(ultConsultas == null) {ultConsultas = new ArrayList<>();
                                  elemsStack=0;}
        if(elemsStack<10) { 
              System.out.print(c.toString());
              ultConsultas.add(c);
              elemsStack++;}
        else {
              ultConsultas.remove(9);
              ultConsultas.add(c);
                }
    }
    
    public List<Consulta> getUltConsultas(){
        List<Consulta> copia = new ArrayList<>();
        if(ultConsultas == null) return null;
        for(Consulta c: ultConsultas)
            copia.add(c.clone());
        return copia;
    }
    
    /**
     * Obter o histórico de imóveis vendidos
     * @return historicoImoveisVendidos
     */
    public List<Imovel> getHistoricoImoveisVendidos(){
        List<Imovel> lista = new ArrayList<>();
        for(Imovel imovel:historicoImoveisVendidos){
            lista.add(imovel);
        }
        return lista;
    }
    
    
    public void inserePortefolio(Imovel i){
        portefolioImoveisEmVenda.add(i);
    }
    
    public void insereHistorico(Imovel i){
        historicoImoveisVendidos.add(i);
    }
    
    /**
     * Alterar o portefólio de imóveis à venda
     * @param portefolioImoveisEmVenda
     */
    public void setPortefolioImoveisEmVenda(List<Imovel> portefolioImoveisEmVenda){
        this.portefolioImoveisEmVenda = portefolioImoveisEmVenda;
    }
    
    
    /**
     * Alterar o histórico de imóveis à venda
     * @param historicoImoveisVendidos
     */
    public void setHistoricoImoveisVendidos(List<Imovel> historicoImoveisVendidos){
        this.historicoImoveisVendidos = historicoImoveisVendidos;
    }
    
    public void vendido(Imovel ant, Imovel dep){
        historicoImoveisVendidos.add(dep);   
        portefolioImoveisEmVenda.remove(ant);
    }
    
    /**
     * Método equals
     * @param o
     * @return boolean
     */
    public boolean equals(Object obj){
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Vendedor o = (Vendedor) obj;
        if(super.equals(o)==false) return false; 
        boolean b = true;
        Iterator<Imovel> i = o.getPortefolioImoveisEmVenda().iterator();
        for(;b && i.hasNext();){
            Imovel im = (Imovel) i.next();
            Iterator<Imovel> i2 = portefolioImoveisEmVenda.iterator();
            for(;b && i2.hasNext();){
                Imovel im2 = (Imovel) i2.next();
                b = b && im2.equals(im);
            }
        }
        i = o.getHistoricoImoveisVendidos().iterator();
        for(;b && i.hasNext();){
            Imovel im = (Imovel) i.next();
            Iterator<Imovel> i2 = historicoImoveisVendidos.iterator();
            for(;b && i2.hasNext();){
                Imovel im2 = (Imovel) i2.next();
                b = b && im2.equals(im);
            }
        }
        return b;
    }
    
    
    /**
     * Método clone
     */
    public Vendedor clone(){
        return new Vendedor(this);
    }
    
    
    /**
     * Método toString
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Vendedor: \n");
        sb.append(super.toString()).append("\n");
        return sb.toString();
    }
    
    
}