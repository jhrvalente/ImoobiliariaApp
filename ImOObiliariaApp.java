
import java.io.IOException;
import java.util.Scanner;
import java.util.*;
import java.util.regex.Pattern;
import java.io.*;

public class ImOObiliariaApp implements Serializable{

    private static Imoobiliaria imob;
    
    public static void main(String[] args) throws UtilizadorExistenteException,SemAutorizacaoException,ImovelExisteException,ImovelInexistenteException, EstadoInvalidoException, IOException, ClassNotFoundException{
        imob=new Imoobiliaria();
        Menu mp=setMenuPrincipal();
        Menu mv=setMenuVendedores();
        Menu mc=setMenuCompradores();
        imob.initApp();
        imob=carregarDados();
        int op=-1;
        boolean sair=false;
        while(sair!=true){    
            if(imob.getUtilizadorComSessaoIniciada()==null){
             mp.executa();
             op=mp.getOpcao();
            switch(op){     
            case 1: {inserirUtilizador(imob);
                     break;}
            case 2: {iniciarSessao(imob);
                     break;}
            case 3: {imoveisTipo(imob);
                     break;}
            case 4: {consultarHabitaveis(imob);
                      break;}
            case 5: {mapeamentoImVendedor(imob);
                      break;}
            case 0: System.exit(0);
            default: System.exit(0);
                    }}
            else if(imob.getUtilizadorComSessaoIniciada() instanceof Vendedor){
             mv.executa();
             op=mv.getOpcao();
            switch(op){
            case 1: {inserirUtilizador(imob);
                     break;}
            case 2: {iniciarSessao(imob);
                     break;}
            case 3: {imoveisTipo(imob);
                     break;}
            case 4: {consultarHabitaveis(imob);
                      break;}
            case 5: {registarImovel(imob);
                     break;}
            case 6: {ultimasConsultas(imob);
                     break;}
            case 7: {alterarEstado(imob);
                     break;}
            case 8: {maisConsultados(imob);
                     break;}
            case 9: {mapeamentoImVendedor(imob);
                      break;}
            case 10:{initLeilao(imob);
                     break;}
            case 11:{inscricaoLeilao(imob);
                     break;}
            case 12:{simLeilao(imob);
                     break;}
            case 0: {imob.fechaSessao();
                     break;}
            default: System.exit(0);
                    }}         
                     
            else if(imob.getUtilizadorComSessaoIniciada() instanceof Comprador){         
                mc.executa();
                op=mc.getOpcao();
                switch(op){
            case 1: {inserirUtilizador(imob);
                     break;}
            case 2: {iniciarSessao(imob);
                     break;}
            case 3: {imoveisTipo(imob);
                     break;}
            case 4: {consultarHabitaveis(imob);
                      break;}
            case 5: {marcarFavorito(imob);
                    break;}
            case 6: {imoveisFavoritosPreco(imob);
                    break;}
            case 7: {mapeamentoImVendedor(imob);
                      break;}
            case 0: {imob.fechaSessao();
                     break;}
            default: System.exit(0);
                   }}
            gravaEstado(imob);
     }  
    }
    
    private static void gravaEstado(Imoobiliaria imob) throws IOException { 
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Estado.dat")); 
                    oos.writeObject(imob); 
                       oos.flush(); 
        oos.close(); 
    }
    
    
        private static Imoobiliaria carregarDados()  throws IOException, ClassNotFoundException {
            Imoobiliaria imob;
            try{ ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Estado.dat"));
      
             imob= (Imoobiliaria) ois.readObject();
        
            ois.close();}
            catch(FileNotFoundException e){
                imob = new Imoobiliaria();
            }
        return imob;
    }
    
     private static void setJanelaResposta(String resposta){
        System.out.println("                                                   ");
        System.out.println("                                                   ");
         System.out.println("***************************************************");
        System.out.println("*                                                 *");
        System.out.println("        "+ resposta +" ");
        System.out.println("*                                                 *");
        System.out.println("***************************************************");
        System.out.println("                                                   ");
        System.out.println("                                                   ");
    }
   
    
     private static Menu setMenuPrincipal(){
        String[] op = { "---------------------------------------------------",
                        "                ImOObiliariaApp v1.0                ",
                        "---------------------------------------------------",
                        "***************************************************",
                        "*   Seja benvindo à nossa aplicação.              *",
                        "*   Seleccione uma das opções seguintes.          *",
                        "***************************************************",
                        "*      1. Registar utilizador                     *",
                        "*      2. Iniciar sessão                          *",
                        "*      3. Consultar imóveis de um dado tipo       *",
                        "*      4. Obter lista de imóveis habitáveis       *",
                        "*      5. Obter mapeamento <Imovel, Vendedor>     *",                        
                        "*      0. Sair da aplicação                       *", 
                        "***************************************************"};
        Menu m=new Menu(op);
        return m;
    }
    
     private static Menu setMenuVendedores(){
        String[] op = { "***************************************************",
                        "*               MENU DE UTILIZADOR                *",
                        "***************************************************",
                        "*      1.  Registar utilizador                    *",
                        "*      2.  Iniciar sessão                         *",
                        "*      3.  Consultar imóveis de um dado tipo      *",
                        "*      4.  Obter lista de imóveis habitáveis      *",
                        "*      5.  Registar imóvel                        *",
                        "*      6.  Listar as últimas 10 consultas         *",
                        "*      7.  Alterar estado do imovel               *",
                        "*      8.  Listar os imóveis mais consultados     *",
                        "*      9.  Obter mapeamento <Imovel, Vendedor>    *", 
                        "*      10. Iniciar leilao                         *",
                        "*      11. Inscrever compradores no leilao        *",
                        "*      12. Simular leilão                         *",
                        "*      0.  Terminar Sessão                        *",
                        "***************************************************"};
        Menu m=new Menu(op);
        return m;
    }
    
     private static Menu setMenuCompradores(){
        String[] op = {  "***************************************************",
                        "*               MENU DE UTILIZADOR                *",
                        "***************************************************",
                        "*      1. Registar utilizador                     *",
                        "*      2. Iniciar sessão                          *",
                        "*      3. Consultar imóveis de um dado tipo       *",
                        "*      4. Obter lista de imóveis habitáveis       *", 
                        "*      5. Marcar imóvel como favorito             *",
                        "*      6. Consultar imóveis favoritos             *",
                        "*      7. Obter mapeamento <Imovel, Vendedor>     *", 
                        "*      0. Terminar Sessão                         *", 
                        "***************************************************"};
        Menu m=new Menu(op);
        return m;
    }
    
    
    private static int leInt(String msg){
        Scanner sc = new Scanner(System.in);
        int n = -1, m = -1;
        
        try {
        System.out.print(msg);
        n=sc.nextInt();
         
         if((msg.equals("Ano de nascimento: ")) && (n<1900 || n>1998)|| (msg.equals("Mês de nascimento: ")) && (n<1 || n>12) || (msg.equals("Dia de Nascimento: ")) && ((n<1 || n>31)))
         throw new InputMismatchException();
        }  catch(InputMismatchException e) {
            System.out.println("Opção Inválida!");
            return leInt(msg);
        }
        return n;
    }
    
    private static String leEstado(){
        Scanner sc = new Scanner(System.in);
        String s = "";
        
         
        try {
            System.out.println("Indique o estado: Em Venda, Reservado, Vendido");
            s=sc.nextLine();
            if(s.equals("Em Venda")||s.equals("Reservado")||s.equals("Vendido")||s.equals("Em venda"))  ;
            else {System.out.println("Opção Inválida!");
                  return leEstado();}
        } catch(InputMismatchException e) {
            System.out.println("Opção Inválida!");
            return leEstado();
        }
        return s;
        }
    
    private static double leDouble(String msg){
        Scanner sc = new Scanner(System.in);
        double d = -1;
        
         
        try {
            System.out.print(msg);
            d=sc.nextDouble();
           
        } catch(InputMismatchException e) {
            System.out.println("Opção Inválida!");
            return leDouble(msg);
        }
        return d;
        }
    
    private static String leLinha(String msg){
        Scanner sc = new Scanner(System.in);
        String l = "";
        Pattern p0 = Pattern.compile(".*@.*\\..*");
        Pattern p1 = Pattern.compile("[a-zA-Z\\s]*");
        Pattern p2 = Pattern.compile("[a-zA-Z]++[a-zA-Z1-9\\s\\~\\´\\`\\ª\\º\\-\\,\\.\\^]*");
        

        try {
            System.out.print(msg);
            l=sc.nextLine(); 
            if((msg.equals("Email: ") && !p0.matcher(l).matches()) ||(msg.equals("Nome: ")) && !p1.matcher(l).matches( )||(msg.equals("Morada: ")) && !p2.matcher(l).matches()) {
                throw new InputMismatchException();
            }
          
        } catch(InputMismatchException e) {
            System.out.println("Opção Inválida!");
            return leLinha(msg);
            
        }
        return l;
    }
    
    private static boolean leBoolean(){
        String opt;
        Boolean bool = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("t - True, f- False");
        try{opt=sc.nextLine();
        if(opt.equals("t")||opt.equals("T")) return bool=true;
        else if (opt.equals("f")||opt.equals("F")) return bool=false;
        else {
            System.out.print("Opção inválida! ");
            return leBoolean();
        }
        } catch(InputMismatchException e){
            opt="";
        }
        return bool;
    }
     
        
    private static void setHeader(String str) {
        System.out.println("---------------------------------------------------");
        System.out.println("                 " + str + "                       ");
        System.out.println("---------------------------------------------------");
    }
    
    private static void mapeamentoImVendedor(Imoobiliaria imob){
        Map<Imovel,Vendedor> map = imob.getMapeamentoImoveis();
        if(map!=null)
            for (Map.Entry<Imovel,Vendedor> entry : map.entrySet())
                System.out.println("Vendedor: "+entry.getValue().toString()+"\n"+"Imóvel: "+entry.getKey().toString());
        else setJanelaResposta("Sem imóveis disponiveis! ");    
            }
    
    private static void inserirUtilizador(Imoobiliaria imob) throws UtilizadorExistenteException {
            String mail, nome, pass, morada, linha;
            int ano,mes,dia;
            
            setHeader("Registo de Utilizador");
            mail = ImOObiliariaApp.leLinha("Email: ");
            nome = ImOObiliariaApp.leLinha("Nome: ");
            pass = ImOObiliariaApp.leLinha("Password: ");
            morada = ImOObiliariaApp.leLinha("Morada: ");
            ano= ImOObiliariaApp.leInt("Ano de nascimento: ");
            mes= ImOObiliariaApp.leInt("Mês de nascimento: ");
            dia= ImOObiliariaApp.leInt("Dia de nascimento: ");
            GregorianCalendar gc=new GregorianCalendar(ano,mes,dia);
            linha= ImOObiliariaApp.leLinha("Indique se é um comprador ou vendedor (c/v): ");
            try{if("c".equals(linha)||"C".equals(linha)) imob.registarUtilizador(new Comprador(mail,nome,pass,morada,gc,false,new ArrayList<>()));
                else if("v".equals(linha)||"V".equals(linha)) imob.registarUtilizador(new Vendedor(mail,nome,pass,morada,gc,false)); 
                else {setJanelaResposta("Registo Inválido! ");
                       imob = new Imoobiliaria();
            }}
            catch(UtilizadorExistenteException e){
                imob = new Imoobiliaria();
            }
        }
        
    private static void terminarSessao(Imoobiliaria imob){
        imob.fechaSessao();
        setJanelaResposta("Sessão terminada com sucesso! ");
    }
    
    private static void iniciarSessao(Imoobiliaria imob) throws SemAutorizacaoException{
        String passwd,email;
        setHeader("Iniciar sessão");
        if(imob.getUtilizadorComSessaoIniciada()!=null) {setJanelaResposta("Termine a sessão atual! ");
                                                          return ;}
        email = ImOObiliariaApp.leLinha("Email: ");
        passwd = ImOObiliariaApp.leLinha("Password: ");
        try{imob.iniciaSessao(email,passwd);}  
        catch(SemAutorizacaoException e){
            iniciarSessao(imob);
        }
        setJanelaResposta("Sessão iniciada com sucesso! ");
    }
    
     private static Menu setMenuImovel(){
        String[] op = { "***************************************************",
                        "*                    IMÓVEIS                      *",
                        "***************************************************",
                        "*  Indique o tipo de imóvel a inserir             *",
                        "*      1. Moradia                                 *",
                        "*      2. Apartamento                             *",
                        "*      3. Loja Comercial                          *",
                        "*      4. Loja Habitacional                       *",
                        "*      5. Terreno                                 *",
                        "***************************************************"};
        Menu m=new Menu(op);
        return m;
    }
    
    private static void registarImovel(Imoobiliaria imob) throws ImovelExisteException, SemAutorizacaoException{
        String localizacao,est;
        String idImovel;
        double pp,pma;
        Vendedor vendedor;
        if(imob.getUtilizadorComSessaoIniciada() instanceof Vendedor == false) {System.out.println("Opção disponível apenas para vendedores! ");return ;}
        else{      
            Menu m = setMenuImovel();
            m.executa();
            int id = m.getOpcao();
                try{vendedor=(Vendedor)imob.getUtilizadorComSessaoIniciada();}
                catch(ClassCastException e){
                    setJanelaResposta("Opção disponível apenas para vendedores! ");
                    imob = new Imoobiliaria();
                    return ;
                }
                idImovel= leLinha("Id do imóvel: ");
                localizacao= leLinha("Localização: ");
                pp = leDouble("Preço pedido: ");
                pma = leDouble("Preço minimo aceite: ");
                est = leEstado();
                id=m.getOpcao();
               
            switch (id) {
                case 1:
                    {   
                        Moradia nova=criaMoradia(idImovel,localizacao,pp,pma,est,vendedor);
                        imob.registaImovel(nova);
                        break;
                    }
                case 2:
                    {
                        Apartamento novo=criaApartamento(idImovel,localizacao,pp,pma,est,vendedor);
                        imob.registaImovel(novo);
                        break;
                    }
                case 3:
                    {
                        LojaComercial nova=criaLojaComercial(idImovel,localizacao,pp,pma,est,vendedor);
                        imob.registaImovel(nova);
                        break;
                    }
                case 4:
                    {
                        LojaHabit nova=criaLojaHabit(idImovel,localizacao,pp,pma,est,vendedor);
                        imob.registaImovel(nova);
                        break;
                    }
                case 5:
                    {
                        Terreno novo=criaTerreno(idImovel,localizacao,pp,pma,est,vendedor);
                        imob.registaImovel(novo);
                        break;
                    }
                default:
                    System.out.print("Opção inválida! ");
                    imob = new Imoobiliaria();
                    break;
            }
}
    }
    
    private static void ultimasConsultas(Imoobiliaria imob) throws SemAutorizacaoException{
            List<Consulta> lista = new ArrayList<>(); 
            try{lista = imob.getConsultas();}
            catch(SemAutorizacaoException e){
                imob = new Imoobiliaria();
            }
            if(lista != null){
            for(Consulta c: lista)
                System.out.println(c.toString());}
            else System.out.print("NULL");
    }

    private static void alterarEstado(Imoobiliaria imob) throws ImovelInexistenteException, EstadoInvalidoException, SemAutorizacaoException{
        String idImovel,estado;
        if(imob.getUtilizadorComSessaoIniciada() instanceof Vendedor == false) {setJanelaResposta("Opção disponível apenas para vendedores! "); return ;}
        idImovel = leLinha("Indique o id do imóvel: ");
        estado = leEstado();
        try { imob.setEstado(idImovel,estado);}
        catch(SemAutorizacaoException e){
            imob=new Imoobiliaria();
            setJanelaResposta("Não está registado ou não é vendedor! ");
        }
        catch(ImovelInexistenteException e){
            imob=new Imoobiliaria();
            setJanelaResposta("Imóvel inexistente! ");
        }
        catch(EstadoInvalidoException e){
            imob=new Imoobiliaria();
            setJanelaResposta("Estado inválido! ");}
    }
    
    private static void imoveisFavoritosPreco(Imoobiliaria imob){
        Comprador c;
        try{c=(Comprador) imob.getUtilizadorComSessaoIniciada();}
        catch(ClassCastException e){
            setJanelaResposta("Opção disponivel apenas para compradores! ");
            imob = new Imoobiliaria(); 
            return ;}
        try{
        List<Imovel> lista= c.getListaFavoritos();
        for(Imovel im: lista)
            System.out.println(im.toString());}
        catch(NullPointerException e){
             setJanelaResposta("Não existem imóveis na lista de favoritos! ");
        }
    }

    private static void marcarFavorito(Imoobiliaria imob) throws ImovelInexistenteException, SemAutorizacaoException{
        Comprador c;
        String idImovel;
        if(imob.getUtilizadorComSessaoIniciada() instanceof Comprador == false) {System.out.println("Opção disponivel apenas para compradores! "); return ;}
        try{c=(Comprador) imob.getUtilizadorComSessaoIniciada();}
        catch(ClassCastException e){
            setJanelaResposta("Opção disponivel apenas para compradores! ");
            imob = new Imoobiliaria(); 
            return ;}
        idImovel = leLinha("Id do imóvel: ");
        try{imob.setFavorito(idImovel);}
        catch(SemAutorizacaoException e){
            imob=new Imoobiliaria();
        }
    }
    
    private static void maisConsultados(Imoobiliaria imob){
        int nCons;
        Set<String> set = new HashSet<>();
        nCons = leInt("Número minimo de consultas: ");
        set = imob.getTopImoveis(nCons);
        if(set!=null)
        for(String str : set)
            System.out.println("Código do imóvel: "+str);
    }
    
    private static void imoveisTipo(Imoobiliaria imob) throws ImovelInexistenteException{
        String tipo;
        double precoM;
        List<Imovel> lista;
        Map<Imovel,Vendedor> map;
        Menu m;
        
        tipo = leLinha("Tipo do imóvel: ");
        precoM = leInt("Preço máximo do imóvel: ");                                                                                               
        lista = imob.getImovel(tipo, (int) precoM);
        if(lista == null) {setJanelaResposta("Imóveis indisponiveis! "); return ;}
        lista.stream().forEach((im) -> {
            System.out.print(im.toString());
        });
}
    
    private static void consultarHabitaveis(Imoobiliaria imob) {
        double precoM;
        List<Habitavel> lista = new ArrayList<>();
        precoM = leInt("Preço máximo do imóvel: ");
               
        lista = imob.getHabitaveis((int) precoM);
        
        lista.stream().forEach((im) -> {
            System.out.print(im.toString());
        });
    }
    
    private static Moradia criaMoradia(String id,String loc, double pp, double pma, String estado,Vendedor vend){
         String tipo;
         double areaImplantacao, areaTotalCoberta, areaTerrenoEnvolvente;
         int nPorta, nQuartos, nWCs;
         areaImplantacao = leDouble("Área de implantação: ");
         areaTotalCoberta = leDouble("Área total coberta: ");
         areaTerrenoEnvolvente = leDouble("Área total envolvente: ");
         nPorta = leInt("Número da porta: ");
         nQuartos = leInt("Número de quartos: ");
         nWCs = leInt("Número de WC's: ");
         return new Moradia(id,loc,pp,pma,estado,vend,"Moradia",areaImplantacao,areaTotalCoberta,areaTerrenoEnvolvente,nPorta,nQuartos,nWCs);
    }
    
    private static Apartamento criaApartamento (String id,String loc, double pp, double pma, String estado,Vendedor vend){
         String tipo;
         double areaTotal;
         int nPorta, nQuartos, nWCs, nAndar;
         boolean temGaragem;
         areaTotal = leDouble("Área total: ");
         nQuartos = leInt("Número de quartos: ");
         nWCs = leInt("Número de WC's: ");
         nPorta = leInt("Número da porta: ");
         nAndar = leInt("Número de andar: ");
         System.out.println("Possui garagem? ");
         temGaragem = leBoolean();
         return new Apartamento (id,loc,pp,pma,estado,vend,"Apartamento",areaTotal,nQuartos,nWCs,nPorta,nAndar,temGaragem);
    }
        
    private static LojaComercial criaLojaComercial (String id,String loc, double pp, double pma, String estado,Vendedor vend){
         double area;
         boolean temWC;
         String tipoNegocioViavel;
         int nPorta;
         area = leDouble("Área: ");
         System.out.print("Tem WC? ");
         temWC = leBoolean();
         tipoNegocioViavel = leLinha("Tipo de negócio viável: ");
         nPorta = leInt("Núm. da porta: ");
         return new LojaComercial (id,loc,pp,pma,estado,vend,area,temWC,tipoNegocioViavel,nPorta);
    }
    
     private static LojaHabit criaLojaHabit (String id,String loc, double pp, double pma, String estado,Vendedor vend){
         double area;
         boolean temWC;
         String tipoNegocioViavel;
         int nPorta,nWCs,nQuartos;
         Apartamento ap;
         area = leDouble("Área: ");
         temWC = leBoolean();
         tipoNegocioViavel = leLinha("Tipo de negócio viável: ");
         nPorta = leInt("Núm. da porta: ");
         nQuartos = leInt("Núm. de quartos: ");
         nWCs = leInt("Núm. de WC's: ");
         System.out.print("Insira as informações da parte habitável: ");
         ap=criaApartamento(id,loc,pp,pma,estado,vend);
         return new LojaHabit (id,loc,pp,pma,estado,vend,area,temWC,tipoNegocioViavel,nPorta,nQuartos,nWCs,ap);
    }
    
    private static Terreno criaTerreno (String id,String loc, double pp, double pma, String estado,Vendedor vend){
         double areaDC,dc,mks;
         String tt;
         boolean rai,rei,are;
         areaDC = leDouble("Área disponivel para construção: ");
         tt = leLinha("Tipo de terreno ");
         mks = leDouble("Máximo de KWh suportados: ");
         dc = leDouble("Diametro das canalizações: ");
         rai = leBoolean();
         System.out.print("Rede elétrica instalada: ");
         rei = leBoolean();
         System.out.print("Acesso a rede de esgotos? ");
         are = leBoolean();
         return new Terreno (id,loc,pp,pma,estado,vend,areaDC,tt,dc,mks,rai,rei,are);
    } 
 
    private static void initLeilao(Imoobiliaria imob){
        String id = leLinha("ID do imovel que deseja leiloar: ");
        Imovel i =imob.getImovelId(id);
        int dur = leInt("Duracao do leilao: ");
        try{ 
            imob.iniciaLeilao(i,dur);
        }
        catch(SemAutorizacaoException e){
            imob=new Imoobiliaria();
            System.out.print("Não está registado ou não é vendedor! ");
        }
    }

    private static void inscricaoLeilao(Imoobiliaria imob){
        Iterator<Utilizador> i = imob.getUtilizadoresRegistados().iterator();
        do{
            Utilizador u = (Utilizador) i.next();
            if(u instanceof Comprador){
                String linha=leLinha("Deseja inscrever o utilizador:\n"+u.toString());
                if(linha.equals("Sim")){ 
                    double lim = leDouble("Limite Máximo de licitacao: ");
                    double intervalo = leDouble("Intervalo entre licitacoes: ");
                    double inc = leDouble("Incrementos: ");
                    try{
                        imob.adicionaComprador(u.getEmail(),lim,inc,intervalo);
                    }
                    catch(LeilaoTerminadoException e){
                        imob=new Imoobiliaria();
                    }
                }
            }
        }while(i.hasNext());
    }
    
    private static void simLeilao(Imoobiliaria imob){
        String s = imob.simulaLeilao();
        System.out.println(s);
        int i = leInt("0.Encerrar Leilao\n");
        if(i==0){
            StringBuilder sb = new StringBuilder();
            s = imob.encerraLeilao().toString();
            sb.append("\n").append("Vencedor do Leilão: ").append(s);
            System.out.println(sb.toString());
        }
    }
    
    
    
  }

