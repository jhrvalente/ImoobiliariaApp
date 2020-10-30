
/**
 * Write a description of class Apartamento here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.Serializable;

public class Apartamento extends Imovel implements Serializable,Habitavel
{
    private String tipo;
    private double areaTotal;
    private int nPorta, nAndar, nQuartos, nWCs;
    private boolean temGaragem;
    /**
     * Construtor parametrizado
     * @param id
     * @param loc
     * @param pr
     * @param prMin
     * @param st
     * @param vend
     * @param tipo
     * @param areaTotal
     * @param nQuartos
     * @param nWCs
     * @param nPorta
     * @param nAndar
     * @param temGaragem 
     */
    public Apartamento(String id, String loc, double pr, double prMin, String st, Vendedor vend, String tipo, double areaTotal, int nQuartos, int nWCs, int nPorta, int nAndar, boolean temGaragem){
        super(id,loc,pr,prMin,st,vend);
        this.tipo = tipo;
        this.areaTotal = areaTotal;
        this.nPorta = nPorta;
        this.nAndar = nAndar;
        this.nQuartos = nQuartos;
        this.nWCs = nWCs;
        this.temGaragem = temGaragem;
    }
    /**
     * Construtor vazio
     */
    public Apartamento(){
        super();
        this.tipo = "";
        this.areaTotal = 0;
        this.nPorta = 0;
        this.nAndar = 0;
        this.nQuartos = 0;
        this.nWCs = 0;
        this.temGaragem = false;
    }
    /**
     * Construtor por cópia
     * @param apartamento 
     */
    public Apartamento(Apartamento apartamento){
        super(apartamento);
        this.tipo = apartamento.getTipo();
        this.areaTotal = apartamento.getAreaTotal();
        this.nPorta = apartamento.getNumPorta();
        this.nAndar = apartamento.getNumAndar();
        this.nQuartos = apartamento.getNQuartos();
        this.nWCs = apartamento.getNWCs();
        this.temGaragem = apartamento.temGaragem();
    }
    /**
     * Obter o tipo
     * @return 
     */
    public String getTipo(){
        return tipo;
    }
    /**
     * Obter a area total
     * @return 
     */
    public double getAreaTotal(){
        return areaTotal;
    }
    
    public int getNumPorta(){
        return nPorta;
    }
    
    public int getNumAndar(){
        return nAndar;
    }
    
    public boolean temGaragem(){
        return temGaragem;
    }
    
   
    public int getNQuartos(){
        return nQuartos;
    }
    
    public int getNWCs(){
        return nWCs;
    }
    
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    
    public void setAreaTotal(double areaTotal){
        this.areaTotal = areaTotal;
    }
    
    public void setNumPorta(int nPorta){
        this.nPorta = nPorta;
    }
    
    public void setNumAndar(int nAndar){
        this.nAndar = nAndar;
    }
    
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null||o.getClass()!=this.getClass()) return false;
        Apartamento ap=(Apartamento) o;
        return (ap.getTipo().equals(tipo)&&areaTotal==ap.getAreaTotal()&&nPorta==ap.getNumPorta()&&nAndar==ap.getNumAndar()&&temGaragem==ap.temGaragem());
    }
    
    public Apartamento clone(){
        return new Apartamento(this);
    }
    
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append(super.getEstado()).append(": Tipo: ");
        sb.append(tipo);
        sb.append("; ID: ").append(super.getId()).append("\n");
        sb.append("Área: ");
        sb.append(areaTotal);
        sb.append(", Num. da porta: ");
        sb.append(nPorta);
        sb.append(", Num. andar: ");
        sb.append(nAndar);
        sb.append(", Tem garagem? ");
        sb.append(temGaragem);
        sb.append("\n");
        return sb.toString();
    }

    
}
