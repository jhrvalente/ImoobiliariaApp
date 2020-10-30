
/**
 * Write a description of class Imovel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.Serializable;

public abstract class Imovel implements Serializable, Comparable<Imovel>
{
    private Vendedor vendedor;
    private String idImovel, localizacao, estado;
    private double precoPedido, precoMinimoAceite;
    
    public Imovel(String idImovel, String localizacao, double precoPedido, double precoMinimoAceite, String estado, Vendedor vendedor){
        this.idImovel = idImovel;
        this.localizacao = localizacao;
        this.precoPedido = precoPedido;
        this.precoMinimoAceite = precoMinimoAceite;
        this.estado = estado;
        this.vendedor = vendedor;
    }
    
    public Imovel(){
        this("", "", 0.0, 0.0, "", new Vendedor());
    }
    
    public Imovel(Imovel imovel){
        this.idImovel = imovel.getId();
        this.localizacao = imovel.getLocalizacao();
        this.precoPedido = imovel.getPrecoPedido();
        this.precoMinimoAceite = imovel.getPrecoMinimoAceite();
        this.estado = imovel.getEstado();
        this.vendedor = imovel.getVendedor();
    }
    
    public Vendedor getVendedor(){
        return vendedor;
    }
    
    public String getId(){
        return idImovel;
    }
    
    public String getLocalizacao(){
        return localizacao;
    }
    
    public double getPrecoPedido(){
        return precoPedido;
    }
    
    public double getPrecoMinimoAceite(){
        return precoMinimoAceite;
    }
    
    public String getEstado(){
        return estado;
    }
    
    public void setId(String idImovel){
        this.idImovel = idImovel;
    }
    
    public void setLocalizacao(String localizacao){
        this.localizacao = localizacao;
    }
    
    public void setPrecoPedido(double precoPedido){
        this.precoPedido = precoPedido;
    }
    
    public void setPrecoMinimoAceite(double precoMinimoAceite){
        this.precoMinimoAceite = precoMinimoAceite;
    }
    
    public void setEstado(String estado){
        this.estado = estado;
    }
    
    public int compareTo(Imovel i){
        double preco = i.getPrecoPedido();
        int res=0;
        if(preco == precoPedido) res = 0;
        if(preco > precoPedido) res = -1;
        if(preco < precoPedido) res = 1;
        return res;
    }
    
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null||o.getClass()!=this.getClass()) return false;
        Imovel im=(Imovel) o;
        return (vendedor.equals(im.getVendedor())&&idImovel.equals(im.getId())&&localizacao.equals(im.getLocalizacao())&&estado.equals(im.getEstado())
                &&precoPedido==im.getPrecoPedido()&&precoMinimoAceite==im.getPrecoMinimoAceite());
    } 
    
    public abstract Imovel clone();
    
    public String toString(){
        StringBuilder sb=new StringBuilder();
        vendedor.toString();
        sb.append(", Id: ");
        sb.append(idImovel);
        sb.append(", Localização: ");
        sb.append(localizacao);
        sb.append(", Estado: ");
        sb.append(estado);
        sb.append(", Preço: ");
        sb.append(precoPedido);
        sb.append(", Minimo aceitável: ");
        sb.append(precoMinimoAceite);
        sb.append("\n");
        return sb.toString();
    }
    
    public int hashCode(){
        return idImovel.hashCode()+localizacao.hashCode();}
}
