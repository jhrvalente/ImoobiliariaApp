
/**
 * Write a description of class Loja here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.Serializable;

public class LojaComercial extends Imovel implements Serializable
{
    /** Indica a área da loja */
    private double area;
    /** Boolean que indica se a loja tem WC */
    private boolean temWC;
    /** Negócio viável */
    private String tipoNegocioViavel;
    /** Número da porta */
    private int nPorta;
    
    /**
     * Construtor parametrizado
     */
    public LojaComercial(String idImovel, String localizacao, double precoPedido, double precoMinimoAceite, String estado, Vendedor vendedor, double area, boolean temWC, String tipoNegocioViavel, int nPorta){
        super(idImovel, localizacao, precoPedido, precoMinimoAceite, estado, vendedor);
        this.area = area;
        this.temWC = temWC;
        this.tipoNegocioViavel = tipoNegocioViavel;
        this.nPorta = nPorta;
    }
    /**
     * Construtor vazio
     */
    public LojaComercial(){
        super();
        this.area = 0;
        this.temWC = false;
        this.tipoNegocioViavel = "";
        this.nPorta = 0;
    }
    /**
     * Construtor por cópia
     */
    public LojaComercial(LojaComercial loja){
        super(loja.getId(), loja.getLocalizacao(), loja.getPrecoPedido(), loja.getPrecoMinimoAceite(), loja.getEstado(), loja.getVendedor());
        this.area = loja.getArea();
        this.temWC = loja.getTemWC();
        this.tipoNegocioViavel = loja.getTipoNegocioViavel();
        this.nPorta = loja.getNumPorta();
    }
    /**
     * Obter a lista área
     */
    public double getArea(){
        return area;
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
    /**
     * Método equals
     */
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null||o.getClass()!=this.getClass()) return false;
        LojaComercial lc=(LojaComercial) o;
        return (area==lc.getArea()&&temWC==lc.getTemWC()&&tipoNegocioViavel==lc.getTipoNegocioViavel()&&nPorta==getNumPorta());
    }
    
    public LojaComercial clone(){
        return new LojaComercial(this);
    }
    
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append(super.getEstado()).append(": Tipo: Loja Comercial");
        sb.append("; ID: ").append(super.getId()).append("\n");
        sb.append("Área: ");
        sb.append(area);
        sb.append(", Tem wc? ");
        sb.append(temWC);
        sb.append(", Negócio: ");
        sb.append(tipoNegocioViavel);
        sb.append(", Num. da porta: ");
        sb.append(nPorta);
        sb.append("\n");
        return sb.toString();
    }
}
