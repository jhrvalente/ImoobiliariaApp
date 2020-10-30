
/**
 * Write a description of class Imoobiliaria here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.io.Serializable;
import java.util.Set;

public class Imoobiliaria implements Serializable
{
    private List<Utilizador> utilizadoresRegistados;
    private Utilizador utilizadorComSessaoIniciada;
    private Map<String, Imovel> conjuntoImoveis;
    private Map<String, Anuncio> conjuntoAnuncios;
    private Leilao leilao;

    public Imoobiliaria(){
        this.utilizadoresRegistados = new ArrayList<>();
        this.conjuntoImoveis = new HashMap<>();
    }

    public Imoobiliaria(Imoobiliaria imobiliaria){
        this.utilizadoresRegistados = imobiliaria.getUtilizadoresRegistados();
        this.conjuntoImoveis = imobiliaria.getConjuntoImoveis();
    }

    public List<Utilizador> getUtilizadoresRegistados(){
        List<Utilizador> lista = new ArrayList<>();

        for(Utilizador utilizador:utilizadoresRegistados){
            lista.add(utilizador);
        }
        return lista;
    }
    
    public void initApp(){
        utilizadoresRegistados=new ArrayList<>();
        utilizadorComSessaoIniciada=null;
        leilao=null;
        conjuntoImoveis= new HashMap<>();
        conjuntoAnuncios= new HashMap<>();
    }
   
    public Map<String, Imovel> getConjuntoImoveis(){
        Map<String, Imovel> map = new HashMap<>();
        for(Imovel i:conjuntoImoveis.values())
            map.put(i.getId(),i);
        return map;
    }

    public Utilizador getUtilizadorComSessaoIniciada(){
        return this.utilizadorComSessaoIniciada;
    }
    
    public boolean existeUtilizador(Utilizador user){
        if(utilizadoresRegistados == null) return false;
        for(Utilizador usr: utilizadoresRegistados)
            if(usr.getEmail().equals(user.getEmail()) == true) return true;
        return false;
        }
    
    public void registarUtilizador(Utilizador utilizador) throws UtilizadorExistenteException {
        Utilizador aux=(Utilizador) utilizador.clone();
        if(existeUtilizador(aux) == true) throw new UtilizadorExistenteException();  
        else utilizadoresRegistados.add(aux);  
    }
 
    public void iniciaSessao(String email, String password) throws SemAutorizacaoException{
        boolean sucesso=false;
        
        if(email==null||password==null||utilizadoresRegistados==null)  return ;
        for(Utilizador utilizador: utilizadoresRegistados)
           if((utilizador.getEmail()).equals(email) && (utilizador.getPassword()).equals(password)){
                utilizadorComSessaoIniciada=utilizador.clone();
                utilizadorComSessaoIniciada.setAutenticado(true);
                sucesso=true; break;
            }
        if(sucesso==false) throw new SemAutorizacaoException();
        
    }     
    
    public Imovel getImovelId(String idImovel){
        Imovel i;
        if(conjuntoImoveis!=null)
            i = (Imovel) conjuntoImoveis.get(idImovel).clone();
        else i=null;
        return i;
    }
    
    public List<Imovel> getImovel(String classe,int preco){
        List<Imovel> lista=new ArrayList<>();
        Consulta c = new Consulta();
        if(conjuntoAnuncios == null) return null;
        for (Map.Entry<String, Anuncio> entry : conjuntoAnuncios.entrySet()){
            if( entry.getValue().getImovel().getClass().getName().equals(classe)  && entry.getValue().getImovel().getPrecoPedido()<=preco) {
                if(utilizadorComSessaoIniciada == null) c = new Consulta(new GregorianCalendar(),"Utilizador não autenticado! ");
                else c = new Consulta(new GregorianCalendar(),utilizadorComSessaoIniciada.getEmail());
                (entry.getValue().getImovel().getVendedor()).insereStack(c.clone());
                entry.getValue().insereConsulta(c.clone());
                lista.add(entry.getValue().getImovel().clone());
            }
        }   
        return lista;
        }
    
    public List<Consulta> getConsultas() throws SemAutorizacaoException{
        if(utilizadorComSessaoIniciada instanceof Vendedor == false) throw new SemAutorizacaoException(); 
        else return ((Vendedor)utilizadorComSessaoIniciada).getUltConsultas();
    }
    
       public void iniciaLeilao(Imovel im, int horas) throws SemAutorizacaoException{
         if(utilizadorComSessaoIniciada.estaAutenticado() && utilizadorComSessaoIniciada instanceof Vendedor){
             leilao = new Leilao(im,horas);
            }
         else throw new SemAutorizacaoException();
    }
    
    
    public void adicionaComprador(String idComprador, double limite, double incrementos, double minutos) throws LeilaoTerminadoException{
        if(leilao != null){
            leilao.addComprador(idComprador,limite,incrementos,minutos);
        }
        else throw new LeilaoTerminadoException();
    }
    
    public String simulaLeilao(){
        try{
            StringBuilder sb = new StringBuilder();
            sb.append("Leilão iniciado!\n");
            sb.append("Duracão do Leilão:").append(leilao.getDuracaoLeilao()).append(" minutos\n");
            int decorrido;
            for(decorrido = leilao.proxLicitacao(); decorrido > 0 && decorrido < leilao.getDuracaoLeilao();decorrido = leilao.proxLicitacao()){
                String lct = leilao.licitacao();
                if(lct.equals("")==false){
                    sb.append("Tempo percorrido: ").append(decorrido).append(" minutos\n");
                    sb.append(lct).append("\n");
                }
            }
            sb.append("Leilão terminou!\n");
            sb.append("Valor licitado: ").append(leilao.getValorLeiloado()).append("\n");
            if(leilao.getValorLeiloado() >= leilao.getImovelLeiloado().getPrecoMinimoAceite()){
                sb.append("Imovel reservado!\n");
            }
            else sb.append("Valor licitado não ultrapassa o valor minimo aceite pelo imovel!\n");
            return sb.toString();
        }
        catch(NullPointerException e){
            return "";
        }
    }
    
    public Comprador encerraLeilao(){
        Comprador c = null;
        if(leilao.getValorLeiloado()>leilao.getImovelLeiloado().getPrecoMinimoAceite()){
            Iterator<Utilizador> i = utilizadoresRegistados.iterator();
            String id = leilao.getMaiorLicitador();
            String idImovel = leilao.getImovelLeiloado().getId();
            Imovel im = (Imovel) conjuntoImoveis.get(idImovel);
            conjuntoImoveis.remove(idImovel);
            im.setEstado("Reservado");
            conjuntoImoveis.put(idImovel,im);
            boolean b = true;
            for(;i.hasNext() && b;){
                Utilizador u = (Utilizador) i.next();
                if(u.getEmail().equals(leilao.getMaiorLicitador()) && u instanceof Comprador){ 
                    b = false;
                    c = (Comprador) u;
                }

            }
        }
        leilao = null;
        return c;
    }
   
    public Map<Imovel,Vendedor> getMapeamentoImoveis (){
        Map<Imovel,Vendedor> map=new HashMap<>();
        Consulta c = new Consulta();
        if(conjuntoAnuncios==null) return null;
        for (Map.Entry<String, Anuncio> entry : conjuntoAnuncios.entrySet()){
            if(utilizadorComSessaoIniciada == null) c = new Consulta(new GregorianCalendar(),"Não autenticado! ");
            else c = new Consulta(new GregorianCalendar(),utilizadorComSessaoIniciada.getEmail());
            Anuncio  a = (entry.getValue());
            (entry.getValue()).insereConsulta(c.clone());
            ((Vendedor)a.getImovel().getVendedor()).insereStack(c.clone());
            Imovel aux=a.getImovel().clone();
            map.put(aux,aux.getVendedor().clone());
        }
        return map;
    }
    
    public List <Habitavel> getHabitaveis(int preco){
         List<Habitavel> lista=new ArrayList<>();
         Habitavel h;
         Consulta c;
         if(conjuntoAnuncios !=null)
         for (Map.Entry<String, Anuncio> entry : conjuntoAnuncios.entrySet())
             if(entry.getValue().getImovel().getPrecoPedido()<=preco){
                 if(entry.getValue().getImovel() instanceof LojaHabit){
                      h=(Habitavel) entry.getValue().getImovel();
                      if(utilizadorComSessaoIniciada == null) c = new Consulta(new GregorianCalendar(),"Utilizador não autenticado! ");
                      else c = new Consulta(new GregorianCalendar(),utilizadorComSessaoIniciada.getEmail());
                      (entry.getValue().getImovel().getVendedor()).insereStack(c);
                      entry.getValue().insereConsulta(c);
                      lista.add(h);
                    }
                 if(entry.getValue().getImovel() instanceof Moradia){
                      h=(Habitavel) entry.getValue().getImovel();
                      if(utilizadorComSessaoIniciada == null) c = new Consulta(new GregorianCalendar(),"Utilizador não autenticado! ");
                      else c = new Consulta(new GregorianCalendar(),utilizadorComSessaoIniciada.getEmail());
                      Anuncio aux = entry.getValue();
                      c=new Consulta(new GregorianCalendar(),"");
                      (aux.getImovel().getVendedor()).insereStack(c);
                      aux.insereConsulta(c);
                      entry.setValue(entry.getValue());
                      lista.add(h);}
                 if(entry.getValue().getImovel() instanceof Apartamento){
                      h=(Habitavel) entry.getValue().getImovel();
                      Anuncio aux =  entry.getValue();
                      if(utilizadorComSessaoIniciada == null) c = new Consulta(new GregorianCalendar(),"Utilizador não autenticado! ");
                      else c = new Consulta(new GregorianCalendar(),utilizadorComSessaoIniciada.getEmail());
                      ((Vendedor)aux.getImovel().getVendedor()).insereStack(c);
                      aux.insereConsulta(c);
                      lista.add(h);}
                    }
         return lista;
        }
        
    public void registaImovel(Imovel im) throws ImovelExisteException, SemAutorizacaoException{
        if(utilizadorComSessaoIniciada instanceof Vendedor){
            if(conjuntoImoveis.containsKey(im.getId())) throw new ImovelExisteException();
            else{
                if(conjuntoImoveis==null) conjuntoImoveis=new HashMap<>();
                conjuntoImoveis.put(im.getId(),im);
                if(conjuntoAnuncios==null) conjuntoAnuncios=new HashMap<>();
                conjuntoAnuncios.put(im.getId(),new Anuncio(im));
                if(im.getEstado().equals("Vendido")){((Vendedor) utilizadorComSessaoIniciada).insereHistorico(im);}
                else ((Vendedor) utilizadorComSessaoIniciada).inserePortefolio(im);
            }
        }
        else throw new SemAutorizacaoException();
    }
    
    public void setEstado(String idImovel, String estado) throws ImovelInexistenteException, SemAutorizacaoException, EstadoInvalidoException{
        if(utilizadorComSessaoIniciada instanceof Vendedor == false) throw new SemAutorizacaoException(); 
        if(estado.equals("Vendido") || estado.equals("Em Venda") || estado.equals("Reservado")){
            if(utilizadorComSessaoIniciada.estaAutenticado() && utilizadorComSessaoIniciada instanceof Vendedor){
            if(conjuntoImoveis.containsKey(idImovel)){
                Imovel i = (Imovel) conjuntoImoveis.get(idImovel);
                conjuntoImoveis.remove(idImovel);
                Imovel novo = i.clone();
                novo.setEstado(estado);
                conjuntoImoveis.put(idImovel,novo);
                if(estado.equals("Vendido")){
                    ((Vendedor) utilizadorComSessaoIniciada).vendido(i,novo);
                }
            }
            else throw new ImovelInexistenteException();
            }
        else throw new SemAutorizacaoException();
        }
        else throw new EstadoInvalidoException();
    }
    
    public Set<String> getTopImoveis(int n){
        Set<String> set = new HashSet<>();
        if(utilizadorComSessaoIniciada instanceof Vendedor){
            List<Imovel> lista = ((Vendedor) utilizadorComSessaoIniciada).getPortefolioImoveisEmVenda();
            for(Imovel i:lista){
                String id = i.getId();
                if(conjuntoAnuncios.containsKey(id)){
                    Anuncio a = (Anuncio) conjuntoAnuncios.get(id);
                    if(a.numConsultas() > n) set.add(id);
                }
            }
        }
        return set;
    }
 
    public void setFavorito(String idImovel) throws ImovelInexistenteException, SemAutorizacaoException{
        if(utilizadorComSessaoIniciada instanceof Comprador && utilizadorComSessaoIniciada.estaAutenticado()){
            if(conjuntoImoveis.containsKey(idImovel)){
                ((Comprador) utilizadorComSessaoIniciada).addFavorito((Imovel) conjuntoImoveis.get(idImovel));
            }
            else throw new ImovelInexistenteException();
        }
        else throw new SemAutorizacaoException();
    }
    
    public TreeSet<Imovel> getFavoritos() throws SemAutorizacaoException{
        if(utilizadorComSessaoIniciada.estaAutenticado() && utilizadorComSessaoIniciada instanceof Comprador){
            TreeSet<Imovel> ts = new TreeSet<Imovel>();
            for(Imovel i:((Comprador) utilizadorComSessaoIniciada).getListaFavoritos()){
                ts.add(i);
                Anuncio a = (Anuncio) conjuntoAnuncios.get(i.getId());
                conjuntoAnuncios.remove(i.getId());
                Consulta c = new Consulta();
                a.insereConsulta(c);
                conjuntoAnuncios.put(i.getId(),a);
            }
            return ts;
        }
        else throw new SemAutorizacaoException();
    }
     
    public void fechaSessao(){
        utilizadorComSessaoIniciada=null;
    }
    
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null||o.getClass()!=this.getClass()) return false;
        Imoobiliaria im=(Imoobiliaria) o;
        return (utilizadoresRegistados.equals(im.getUtilizadoresRegistados())&&
            utilizadorComSessaoIniciada.equals(im.getUtilizadorComSessaoIniciada()));
    }

    public Imoobiliaria clone(){
        return new Imoobiliaria(this);
    }

    public String toString(){
         StringBuilder sb = new StringBuilder();
         sb.append("Utilizadores registados: \n");
         if(utilizadorComSessaoIniciada==null) sb.append("Nenhum utilizador autenticado! "); 
         else sb.append(utilizadoresRegistados.toString());
         sb.append("Utilizadores autenticado: \n");
         if(utilizadorComSessaoIniciada==null) sb.append("Nenhum utilizador autenticado! "); 
         else sb.append(utilizadorComSessaoIniciada.toString());
         sb.append("Imóveis: \n");
         if(conjuntoImoveis==null) sb.append("Conjunto vazio! "); 
         else sb.append(conjuntoImoveis.toString());
         sb.append("Anúncios: \n");
         if(conjuntoAnuncios==null) sb.append("Conjunto vazio! "); 
         else sb.append(conjuntoAnuncios.toString());
         sb.append("Leilão: \n");
         if(leilao==null) sb.append("Conjunto vazio! "); 
         else sb.append(leilao.toString());
         return sb.toString();
    }
   
 public void gravaObj(String fich) throws IOException { 
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fich)); 
        oos.writeObject(this); 
        oos.flush(); 
        oos.close(); 
    } 

    public static Imoobiliaria leObj(String fich) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fich));
      
        Imoobiliaria te= (Imoobiliaria) ois.readObject();
        
        ois.close();
        return te;
    }

    public void log(String f, boolean ap) throws IOException {
        FileWriter fw = new FileWriter(f, ap);
        fw.write("\n----------- LOG - LOG - LOG - LOG - LOG ----------------\n");
        fw.write(this.toString());
        fw.write("\n----------- LOG - LOG - LOG - LOG - LOG ----------------\n");
        fw.flush();
        fw.close();
    }
    
}
