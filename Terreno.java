
/**
 * Write a description of class Terreno here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.Serializable;

public class Terreno extends Imovel implements Serializable
{
    /** Área disponivel para construção */
    private double areaDisponivelConstrucao;
    /** Diametro das canalizações do terreno */
    private double diametroCanalizacoes;
    /** Máximo de KWh suportados */
    private double maximoKWhSuportados;
    /** Tipo do terreno */
    private String tipoTerreno;
    /** Rede de água instalada */
    private boolean redeAguaInstalada;
    /** Rede elétrica instalada */
    private boolean redeElectricaInstalada;
    /** Acesso à rede de esgotos */
    private boolean temAcessoRedeEsgotos;
    
   /**
    * Construtor parametrizado
    */
    public Terreno(String idImovel, String localizacao, double precoPedido, double precoMinimoAceite, String estado, Vendedor vendedor, double areaDisponivelConstrucao, String tipoTerreno, double diametroCanalizacoes, double maximoKWhSuportados, boolean redeAguaInstalada, boolean redeElectricaInstalada, boolean temAcessoRedeEsgotos){
        super(idImovel, localizacao, precoPedido, precoMinimoAceite, estado, vendedor);
        this.areaDisponivelConstrucao = areaDisponivelConstrucao;
        this.tipoTerreno = tipoTerreno;
        this.diametroCanalizacoes = diametroCanalizacoes;
        this.maximoKWhSuportados = maximoKWhSuportados;
        this.redeAguaInstalada = redeAguaInstalada;
        this.redeElectricaInstalada = redeElectricaInstalada;
        this.temAcessoRedeEsgotos = temAcessoRedeEsgotos;
    }
    /**
     * Construtor vazio
     */
    public Terreno(){
        super();
        this.areaDisponivelConstrucao = 0;
        this.tipoTerreno = "";
        this.diametroCanalizacoes = 0;
        this.maximoKWhSuportados = 0;
        this.redeAguaInstalada = false;
        this.redeElectricaInstalada = false;
        this.temAcessoRedeEsgotos = false;
    }
    /**
     * Construtor por cópia
     */
    public Terreno(Terreno terreno){
        super(terreno.getId(), terreno.getLocalizacao(), terreno.getPrecoPedido(), terreno.getPrecoMinimoAceite(), terreno.getEstado(), terreno.getVendedor());
        this.areaDisponivelConstrucao = terreno.getAreaDisponivelConstrucao();
        this.tipoTerreno = terreno.getTipoTerreno();
        this.diametroCanalizacoes = terreno.getDiametroCanalizacoes();
        this.maximoKWhSuportados = terreno.getMaximoKWhSuportados();
        this.redeAguaInstalada = terreno.getRedeAguaInstalada();
        this.redeElectricaInstalada = terreno.getRedeElectricaInstalada();
        this.temAcessoRedeEsgotos = terreno.getTemAcessoRedeEsgotos();
    }
    /**
     * Obter a área disponivel
     */
    public double getAreaDisponivelConstrucao(){
        return areaDisponivelConstrucao;
    }
    /**
     * Obter o tipo de terreno
     */
    public String getTipoTerreno(){
        return tipoTerreno;
    }
    /**
     * Obter o diametro das canalizações
     */
    public double getDiametroCanalizacoes(){
        return diametroCanalizacoes;
    }
    /**
     * Obter o máximo de kwh suportados
     */
    public double getMaximoKWhSuportados(){
        return maximoKWhSuportados;
    }
    /**
     * Obter rede de água instalada
     */
    public boolean getRedeAguaInstalada(){
        return redeAguaInstalada;
    }
    /**
     * Obter a rede elétrica instalada
     */
    public boolean getRedeElectricaInstalada(){
        return redeElectricaInstalada;
    }
    /**
     * Obter acesso a rede de esgotos 
     */
    public boolean getTemAcessoRedeEsgotos(){
        return temAcessoRedeEsgotos;
    }
    /**
     * Alterar a área disponivel para construção
     */
    public void setAreaDisponivelConstrucao(double areaDisponivelConstrucao){
        this.areaDisponivelConstrucao = areaDisponivelConstrucao;
    }
    /**
     * Alterar o tipo de terreno
     */
    public void setTipoTerreno(String tipoTerreno){
        this.tipoTerreno = tipoTerreno;
    }
    /**
     * Alterar o diametro das canalizações
     */
    public void setDiametroCanalizacoes(double diametroCanalizacoes){
        this.diametroCanalizacoes = diametroCanalizacoes;
    }
    /**
     * Alterar o máximo de kwh suportados
     */
    public void setMaximoKWhSuportados(double maximoKWhSuportados){
        this.maximoKWhSuportados = maximoKWhSuportados;
    }
    /**
     * Alterar a rede de água instalada
     */
    public void setRedeAguaInstalada(boolean redeAguaInstalada){
        this.redeAguaInstalada = redeAguaInstalada;
    }
    /**
     * Alterar rede elétrica instalada
     */
    public void setRedeElectricaInstalada(boolean redeElectricaInstalada){
        this.redeElectricaInstalada = redeElectricaInstalada;
    }
    /**
     * Alterar acesso a rede de esgotos
     */
    public void setTemAcessoRedeEsgotos(boolean temAcessoRedeEsgotos){
        this.temAcessoRedeEsgotos = temAcessoRedeEsgotos;
    }
    /**
     * Método equals
     */
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null||o.getClass()!=this.getClass()) return false;
        Terreno tr=(Terreno) o;
        return (areaDisponivelConstrucao==tr.getAreaDisponivelConstrucao()&&tr.getDiametroCanalizacoes()==diametroCanalizacoes&&
                tr.getMaximoKWhSuportados()==maximoKWhSuportados&&tipoTerreno==tr.getTipoTerreno()&&tr.getTemAcessoRedeEsgotos()==temAcessoRedeEsgotos&&tr.getRedeElectricaInstalada()==redeElectricaInstalada);
            }
    
    public Terreno clone(){
        return new Terreno(this);
    }
    
    public String toString(){
        StringBuffer sb=new StringBuffer();
        sb.append(super.getEstado()).append(": Tipo: Terreno");
        sb.append("Área: ");
        sb.append(areaDisponivelConstrucao);
        sb.append(", diametro canalizações: ");
        sb.append(diametroCanalizacoes);
        sb.append(", KWh suportados: ");
        sb.append(maximoKWhSuportados);
        sb.append(", Tipo terreno: ");
        sb.append(tipoTerreno);
        sb.append(", Rede de água: ");
        sb.append(redeAguaInstalada);
        sb.append(",Rede elétrica: ");
        sb.append(redeElectricaInstalada);
        sb.append(", Rede de esgotos: ");
        sb.append(temAcessoRedeEsgotos);
        sb.append("\n");
        return sb.toString();
    }
    
}
