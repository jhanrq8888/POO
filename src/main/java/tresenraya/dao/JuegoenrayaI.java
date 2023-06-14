/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tresenraya.dao;
import tresenraya.modelo.Modelotresenraya;
import java.util.List;


/**
 *
 * @author acer
 */
public interface JuegoenrayaI {
    public List listarResultado(); 
   public int createResultado(Modelotresenraya re);
   public int updateresultado(Modelotresenraya re);
   
   
    
}
