
/**
 * Write a description of class Moradia here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.Serializable;

public class Moradia extends Imovel implements Serializable,Habitavel
{
    private String tipo;
    private double areaImplantacao, areaTotalCoberta, areaTerrenoEnvolvente;
    private int nPorta, nQuartos, nWCs;
    
    public Moradia(String id, String loc, double pr, double prMin, String st, Vendedor vend, String tipo, double areaImplantacao, double areaTotalCoberta, double areaTerrenoEnvolvente, int nQuartos, int nWCs, int nPorta){
        super(id,loc,pr,prMin,st,vend);
        this.tipo = tipo;
        this.nQuartos = nQuartos;
        this.nWCs = nWCs;
        this.areaImplantacao = areaImplantacao;
        this.areaTotalCoberta = areaTotalCoberta;
        this.areaTerrenoEnvolvente = areaTerrenoEnvolvente;
        this.nPorta = nPorta;
    }
    
    public Moradia(){
        super();
        this.tipo = "";
        this.nQuartos = 0;
        this.nWCs = 0;
        this.areaImplantacao = 0;
        this.areaTotalCoberta = 0;
        this.areaTerrenoEnvolvente = 0;
        this.nPorta = 0;
    }
    
    public Moradia(Moradia moradia){
        super(moradia);
        this.tipo = moradia.getTipo();
        this.areaImplantacao = moradia.getAreaImplantacao();
        this.areaTotalCoberta = moradia.getAreaTotalCoberta();
        this.areaTerrenoEnvolvente = moradia.getAreaTerrenoEnvolvente();
        this.nQuartos = moradia.getNQuartos();
        this.nWCs = moradia.getNWCs();
        this.nPorta = moradia.getNumPorta();
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public double getAreaImplantacao(){
        return areaImplantacao;
    }
    
    public double getAreaTotalCoberta(){
        return areaTotalCoberta;
    }
    
    public double getAreaTerrenoEnvolvente(){
        return areaTerrenoEnvolvente;
    }
    
    public int getNQuartos(){
        return nQuartos;
    }
    
    public int getNWCs(){
        return nWCs;
    }
    
    public int getNumPorta(){
        return nPorta;
    }
    
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    
    public void setAreaImplantacao(double areaImplantacao){
        this.areaImplantacao = areaImplantacao;
    }
    
    public void setAreaTotalCoberta(double areaTotalCoberta){
        this.areaTotalCoberta = areaTotalCoberta;
    }
    
    public void setAreaTerrenoEnvolvente(double areaTerrenoEnvolvente){
        this.areaTerrenoEnvolvente = areaTerrenoEnvolvente;
    }
    
    public void setNumPorta(int nPorta){
        this.nPorta = nPorta;
    }
    
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null||o.getClass()!=this.getClass()) return false;
        Moradia m=(Moradia) o;
        return (areaImplantacao==m.getAreaImplantacao()&&m.getTipo().equals(tipo)&&areaTotalCoberta==m.getAreaTotalCoberta()&&
                areaTerrenoEnvolvente==m.getAreaTerrenoEnvolvente()&&nPorta==m.getNumPorta());
    }
    
    public Moradia clone(){
        return new Moradia(this);
    }
    
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("Tipo: ");
        sb.append(tipo);
        sb.append(", Área implantação: ");
        sb.append(areaImplantacao);
        sb.append(", Área coberta: ");
        sb.append(areaTotalCoberta);
        sb.append(", Área terreno envolvente: ");
        sb.append(areaTerrenoEnvolvente);
        sb.append(", Num. da porta: ");
        sb.append(nPorta);
        sb.append("\n");
        return sb.toString();
    }
}
