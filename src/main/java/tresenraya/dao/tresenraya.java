package tresenraya.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import tresenraya.conexion.ConnS;
import tresenraya.modelo.Modelotresenraya;
import tresenraya.util.ErrorLogger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author acer
 */
public class tresenraya implements JuegoenrayaI {

    ConnS intance = ConnS.getInstance();
    Connection conexion = intance.getConnection();
    PreparedStatement ps;
    ResultSet rs;
    ErrorLogger log = new ErrorLogger(tresenraya.class.getName());
 
@Override
    public List listarResultado() {
                List<Modelotresenraya> lista = new ArrayList();
        String sql = "select * from resultados";
        try {
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println("trese:"+rs.getRow());
            while (rs.next()) {
                Modelotresenraya to = new Modelotresenraya();
                //to.setIdresultado(rs.getInt("id_resultado"));
                to.setNombre_partida(rs.getString("nombre_partida"));
                to.setNombre_jugador1(rs.getString("nombre_jugador1"));
                to.setNombre_jugador2(rs.getString("nombre_jugador2"));
                to.setGanador(rs.getString("ganador"));
                to.setPunto(rs.getInt("punto"));
                to.setEstado(rs.getString("estado"));

                lista.add(to);
            }

        } catch (Exception e) {
            System.out.println("Error "+e.getMessage());
        }
        //System.out.println("hh:"+lista.size());
        return lista;
    }

    @Override
    public int createResultado(Modelotresenraya re) {
        int exec = 0;
        int i = 0;
        String sql = "insert into resultados(nombre_partida, nombre_jugador1, nombre_jugador2, ganador, punto, estado)"
                + " values(?,?,?,?,?,?)";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(++i, re.getNombre_partida());
            ps.setString(++i, re.getNombre_jugador1());
            ps.setString(++i, re.getNombre_jugador2());
            ps.setString(++i, re.getGanador());
            ps.setInt(++i, re.getPunto());
            ps.setString(++i, re.getEstado());

            exec = ps.executeUpdate();
        } catch (Exception e) {
        }
        return exec;
    }

    @Override
    public int updateresultado(Modelotresenraya re) {
               System.out.println("actualizar re.getIdresultado:" + re.getIdresultado());
        int comit =0;
        String sql= "Update resultados SET"
                +"nombre_partida=?,"
                +"nombre_jugador1=?,"
                +"nombre_jugador2=?,"
                +"ganador=?,"
                +"punto=?,"
                +"estado=?"
                +"WHERE id_resultado=?,";
        int i=0;
        try{
            ps=conexion.prepareStatement(sql);
            ps.setString(++i, re.getNombre_partida());
            ps.setString(++i, re.getNombre_jugador1());
            ps.setString(++i, re.getNombre_jugador2());
            ps.setString(++i, re.getGanador());
            ps.setInt(++i, re.getPunto());
            ps.setString(++i, re.getEstado());
            ps.setInt(++i, re.getIdresultado());
            
            comit= ps.executeUpdate();
        }catch(SQLException ex){
            log.log(Level.SEVERE, "update", ex);
        }
        return comit;
    }
    

}
