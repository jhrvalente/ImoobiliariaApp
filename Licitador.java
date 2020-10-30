
/**
 * Write a description of class Licitador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Set;
import java.lang.Double;
import java.io.Serializable;

public class Licitador implements Serializable,Comparable<Licitador>
{
    private String id;
    private double incremento, limite; 
    private int intervalo, prox;
    
    public Licitador(String idC, double lim, double inc, double minutos){
        id = idC;
        incremento = inc;
        limite = lim;
        intervalo = (int) minutos;
        prox = (int) minutos;
    }
    
    public Licitador(){
        this("",0,0,0);
    }
    
    public Licitador(Licitador l){
        id = l.getId();
        incremento = l.getIncremento();
        limite = l.getLimite();
        intervalo = l.getIntervalo();
        prox = l.getProxLicitacao();
    }
    
    public String getId(){return id;}
    
    public double getIncremento(){return incremento;}
    
    public double getLimite(){return limite;}
    
    public int getIntervalo(){return intervalo;}
    
    public int getProxLicitacao(){return prox;}
    
    public void actualizaLicitacao(){prox += intervalo;}
    
    public Licitador clone(){return new Licitador(this);}
    
    public int compareTo(Licitador l){
        int b=0;
        if(prox == l.getProxLicitacao()) b = 0;
        if(prox < l.getProxLicitacao()) b = -1;
        if(prox > l.getProxLicitacao()) b = 1;
        return b;
    }
    
    public String toString(){
        return (id+"Prox: "+prox+"\n");
    }
    
    public boolean equals(Object o){
        if(this==o) return true;
        if(this==null||this.getClass()!=o.getClass()) return false;
        Licitador l = (Licitador) o;
        return (id.equals(l.getId()) && incremento == l.getIncremento() && limite == l.getLimite() 
                && intervalo == l.getIntervalo() && prox == l.getProxLicitacao());
    }
}
