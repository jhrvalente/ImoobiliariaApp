
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.Serializable;

/**
 * Write a description of class Utilizador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Utilizador implements Serializable
{
    /** Email do utilizador */
    private String email;
    /** Nome do utilizador */
    private String nome;
    /** Palavra passe do utilizador */
    private String password;
    /** Morada do utilizador */
    private String morada;
    /** Data de nascimento do utilizador */
    private GregorianCalendar dataNascimento;
    /**Autenticacao*/
    private boolean estaAutenticado;
    /**
     * Construtor parametrizado
     * @param email
     * @param nome
     * @param password
     * @param morada
     * @param dataNascimento
     * @param estaAutenticado
     * @param imobiliaria
     */
    public Utilizador(String email, String nome, String password, String morada, GregorianCalendar dataNascimento, boolean estaAutenticado){
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.dataNascimento = dataNascimento;
        this.estaAutenticado = estaAutenticado;
    }
    /**
     * Construtor vazio /*
     */
    public Utilizador(){
        this("", "", "", "", new GregorianCalendar(), false);
    }
    /**
     * Construtor por cópia 
     * @param utilizador
     */
    public Utilizador(Utilizador utilizador){
        this.email = utilizador.getEmail();
        this.nome = utilizador.getNome();
        this.password = utilizador.getPassword();
        this.morada = utilizador.getMorada();
        this.dataNascimento = utilizador.getDataNascimento();
        this.estaAutenticado = utilizador.estaAutenticado();
    }
    
    /** 
     * Obter o email
     * @return email
     */
    public String getEmail(){
        return email;
    }
    
    
    /**
     * Obter o  nome
     * @return nome
     */
    public String getNome(){
        return nome;
    }
    
    
    /**
     * Obter a password
     * @return password
     */
    public String getPassword(){
        return password;
    }
    
    
    /**
     * Obter a morada
     * @return morada
     */
    public String getMorada(){
        return morada;
    }
    
    
    /**
     * Obter a data de nascimento
     * @return dataNascimento
     */
    public GregorianCalendar getDataNascimento(){
        return dataNascimento;
    }
    
    
    /**
     * Obter o boolean autenticado
     * @return estaAutenticado
     */
    public boolean estaAutenticado(){
        return estaAutenticado;
    }
    
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setMorada(String morada){
        this.morada = morada;
    }
    
    public void setDataNascimento(GregorianCalendar dataNascimento){
        this.dataNascimento = dataNascimento;
    }
    
    public void setAutenticado(boolean b){
        this.estaAutenticado=b;}
    
    public boolean equals(Object obj){
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Utilizador o = (Utilizador) obj;
        if(o.getEmail().equals(email)==false) return false;
        if(o.getNome().equals(nome)==false) return false; 
        if(o.getPassword().equals(password)==false) return false;
        if(o.getMorada().equals(morada)==false) return false;
        if(o.getDataNascimento().equals(dataNascimento)==false) return false;
        if(o.estaAutenticado()!=estaAutenticado) return false;
        boolean b = true;
        return b;
    }
    
    public abstract Utilizador clone();
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("E-mail: ").append(email);
        return sb.toString();
    }
    
        public int hashCode(){
        return email.hashCode()+nome.hashCode();
    }
   
}
