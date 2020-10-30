
import java.util.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Consulta implements Serializable
{
    private static GregorianCalendar data;
    private String email;
    
    public Consulta(GregorianCalendar data, String email){
        this.data = data;
        this.email = email;
    }
    
    public Consulta(){
        this(new GregorianCalendar(), "");
    }
    
    public Consulta(Consulta consulta){
        this.data = consulta.getData();
        this.email = consulta.getEmail();
    }
    
    public GregorianCalendar getData(){
        return data;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setData(GregorianCalendar data){
        this.data = data;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public boolean equals(Object o){
        if(this==o) return false;
        if(this==null||o.getClass()!=this.getClass()) return false;
        Consulta cs=(Consulta) o;
        return (data==cs.getData()&&email==cs.getEmail());
    }
    
    public Consulta clone(){
        return new Consulta(this);
    }
    
    public static String format() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fmt.setCalendar(data);
        String dateFormatted = fmt.format(data.getTime());
        return dateFormatted;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Email: ");
        sb.append(email);
        sb.append(" ,");
        sb.append("Data ");
        sb.append(format());
        sb.append("\n");
        return sb.toString();
    }
    
    public int hashCode(){
        return email.hashCode()+data.hashCode();
    }
    
}
