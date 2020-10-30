
/**
 * Write a description of class Loja here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.Serializable;

public class LojaHabit extends Imovel implements Serializable,Habitavel
{
    /** Indica a área da loja */
    private double area;
    /** Boolean que indica se a loja tem WC */
    private boolean temWC;
    /** Negócio viável */
    private String tipoNegocioViavel;
    /** Número da porta */
    private int nPorta, nQuartos, nWCs;
    /** Habitação*/
    private Apartamento apartamento;
    
    /**
     * Construtor parametrizado
     * @param idImovel
     * @param localizacao
     * @param precoPedido
     * @param precoMinimoAceite
     * @param estado
     * @param area
     * @param vendedor
     * @param temWC
     * @param tipoNegocioViavel
     * @param nPorta
     */
    public LojaHabit(String idImovel, String localizacao, double precoPedido, double precoMinimoAceite, String estado, Vendedor vendedor, double area, boolean temWC, String tipoNegocioViavel, int nPorta,int nQuartos, int nWCs, Apartamento ap){
        super(idImovel, localizacao, precoPedido, precoMinimoAceite, estado, vendedor);
        this.area = area;
        this.temWC = temWC;
        this.nQuartos = nQuartos;
        this.nWCs = nWCs;
        this.tipoNegocioViavel = tipoNegocioViavel;
        this.nPorta = nPorta;
        this.apartamento=ap;
    }
    /**
     * Construtor vazio
     */
    public LojaHabit(){
        super();
        this.area = 0;
        this.temWC = false;
        this.nQuartos = 0;
        this.nWCs = 0;
        this.tipoNegocioViavel = "";
        this.nPorta = 0;
        this.apartamento=new Apartamento();
    }
    /**
     * Construtor por cópia
     * @param loja
     */
    public LojaHabit(LojaHabit loja){
        super(loja.getId(), loja.getLocalizacao(), loja.getPrecoPedido(), loja.getPrecoMinimoAceite(), loja.getEstado(), loja.getVendedor());
        this.area = loja.getArea();
        this.temWC = loja.getTemWC();
        this.tipoNegocioViavel = loja.getTipoNegocioViavel();
        this.nPorta = loja.getNumPorta();
        this.nQuartos = loja.getNQuartos();
        this.nWCs = loja.getNWCs();
        this.apartamento=loja.getApartamento().clone();
    }
    /**
     * Obter a lista área
     * @return area
     */
    public double getArea(){
        return area;
    }
    
    public int getNQuartos(){
        return nQuartos;
    }
    
    public int getNWCs(){
        return nWCs;
    }
    
    /**
     * Obter a informação se tem WC
     */
    public boolean getTemWC(){
        return temWC;
    }
    /**
     * Obter a string do negócio viável
     */
    public String getTipoNegocioViavel(){
        return tipoNegocioViavel;
    }
    /**
     * Obter o número da porta
     */
    public int getNumPorta(){
        return nPorta;
    }
    
    public Apartamento getApartamento(){
        return apartamento;}
    
    /**
     * Alterar a área da loja
     */
    public void setArea(double area){
        this.area = area;
    }
    /**
     * Alterar a variável wc
     */
    public void setTemWC(boolean temWC){
        this.temWC = temWC;
    }
    /**
     * Alterar a variável negócio viável
     */
    public void setTipoNegocioViavel(String tipoNegocioViavel){
        this.tipoNegocioViavel = tipoNegocioViavel;
    }
    /**
     * Alterar o número da porta
     */
    public void setNumPorta(int nPorta){
        this.nPorta = nPorta;
    }
    
    public void setApartamento(Apartamento ap){
        apartamento=ap.clone();
    }
    
    /**
     * Método equals
     */
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null||o.getClass()!=this.getClass()) return false;
        LojaHabit lc=(LojaHabit) o;
        return (area==lc.getArea()&&temWC==lc.getTemWC()&&tipoNegocioViavel==lc.getTipoNegocioViavel()&&nPorta==getNumPorta());
    }
    
    public LojaHabit clone(){
        return new LojaHabit(this);
    }
    
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append(super.getEstado()).append(": Tipo: Loja Habitacional");
        sb.append("; ID: ").append(super.getId()).append("\n");
        sb.append("Área: ");
        sb.append(area);
        sb.append(", Tem wc? ");
        sb.append(temWC);
        sb.append(", Negócio: ");
        sb.append(tipoNegocioViavel);
        sb.append(", Num. da porta: ");
        sb.append(nPorta);
        sb.append(",");
        sb.append(apartamento.toString());
        sb.append("\n");
        return sb.toString();
    }
}
