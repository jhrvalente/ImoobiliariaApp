
/**
 * Write a description of class Leilao here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Set;
import java.util.TreeSet;
import java.io.Serializable;
import java.util.Iterator;

public class Leilao implements Serializable
{
    private int duracao;
    private Imovel imovel;
    private double valor;
    private String idLicitador;
    private TreeSet<Licitador> licitadores;
    
    /**Construtores*/
    public Leilao(Imovel i, int horas){
        duracao = horas*60;
        imovel = i;
        valor = 0;
        idLicitador = "";
        licitadores = new TreeSet<>();
    }
    
    public Leilao(){
        duracao = 0;
        imovel = null;
        valor = 0;
        idLicitador = "";
        licitadores = new TreeSet<>();
    }
    
    public Leilao(Leilao l){
        duracao = l.getDuracaoLeilao();
        imovel = l.getImovelLeiloado();
        valor = l.getValorLeiloado();
        idLicitador = l.getMaiorLicitador();
        licitadores = l.getLicitadores();
    }
        
    /**Getters*/
    public int getDuracaoLeilao(){
        return duracao;
    }
    
    public double getValorLeiloado(){
        return valor;
    }
    
    public Imovel getImovelLeiloado(){
        return imovel;
    }
    
    public String getMaiorLicitador(){
        return idLicitador;
    }
    
    public TreeSet<Licitador> getLicitadores(){
        TreeSet<Licitador> ts = new TreeSet<>();
        for(Licitador l:licitadores){
            ts.add(l.clone());
        }
        return ts;
    }
    
    /**Adicionar Licitador*/
    public void addComprador(String idComprador, double limite, double incrementos, double minutos){
        Licitador l = new Licitador(idComprador, limite, incrementos, minutos);
        licitadores.add(l);
    }
    
    /**Realizar nova licitacao*/
    public String licitacao(){
        try{
            if(licitadores.size() > 0){
                Iterator<Licitador> i = licitadores.iterator();
                Licitador l = (Licitador) i.next();
                licitadores.remove(l);
                String s = "";
                if(idLicitador.equals(l.getId())){
                    l.actualizaLicitacao();
                    if(l.getProxLicitacao() < duracao){
                        licitadores.add(l);
                    }
                }
                else {
                    if(valor <= l.getLimite()){
                        valor += l.getIncremento();
                        StringBuilder sb = new StringBuilder();
                        sb.append("Nova licitacao: ").append(l.getId()).append(" - Valor Leiloado: ").append(valor);
                        s=sb.toString();
                        l.actualizaLicitacao();
                        idLicitador = l.getId();
                        if(l.getProxLicitacao() < duracao){
                            licitadores.add(l);
                        }
                    }
                }
                return s;
            }
            else return "";
        }
        catch(NullPointerException e){
            return "";
        }
    }
    
    /**Intervalo de espera antes de nova licitacao*/
    public int proxLicitacao(){
        try{
            if(licitadores!=null && licitadores.size()>0){
                Iterator<Licitador> i = licitadores.iterator();
                Licitador l = (Licitador) i.next();
                int intervalo = l.getProxLicitacao();
                if(intervalo > duracao) intervalo = -duracao; 
                return intervalo;
            }
            else return 0;
        }
        catch(NullPointerException e){
            return 0;
        }
    }
    
    /**Clone*/
    public Leilao clone(){
        return new Leilao(this);
    }
    
    /**Equals*/
    public boolean equals(Object obj){
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Leilao o = (Leilao) obj;
        boolean b = true;
        Iterator<Licitador> i = o.getLicitadores().iterator();
        for(;b && i.hasNext();){
            Licitador l = (Licitador) i.next();
            Iterator<Licitador> i2 = licitadores.iterator();
            for(;b && i2.hasNext();){
                Licitador l2 = (Licitador) i2.next();
                b = b && l2.equals(l);
            }
        }
        return (b && idLicitador.equals(o.getMaiorLicitador()) && valor == o.getValorLeiloado() 
                && imovel.equals(o.getImovelLeiloado()) && duracao == o.getDuracaoLeilao());
    }
    
    /**toString*/
    public String toString(){
        return "";
    }
}
