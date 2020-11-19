
package ConsultaProvincias;

import entidades.Provincia;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Pablo Rodriguez Pino 2DAM
 */
public class ConsultaProvincias {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Conexión
        EntityManagerFactory emf=
            Persistence.createEntityManagerFactory("AppAgendaPU");
        EntityManager em=emf.createEntityManager();
        
        //Hacemos el query del select all
        Query queryProvincias = em.createNamedQuery("Provincia.findAll");
        
        //Metemos todos los datos en una lista
        List<Provincia> listProvincias = queryProvincias.getResultList(); 
        
        //Recorre la lista y la imprime
        for(int i=0;i<listProvincias.size();i++){
        Provincia provincia=listProvincias.get(i);
        System.out.println(provincia.getNombre());
        }
        
        /* Obtener el objeto Provincia cuyo ID sea 2:
        
            Provincia provinciaId2=em.find(Provincia.class,2);
        
            if (provinciaId2 != null){
                System.out.print(provinciaId2.getId() + ":");
                System.out.println(provinciaId2.getNombre());
            } else {
                System.out.println("No hay ninguna provincia con ID=2");
            }

        */
        
        /* Obtener el objeto Provincia cuyo nombre = Cadiz
        
        Query queryProvinciaCadiz = em.createNamedQuery("Provincia.findByNombre");
        
        queryProvinciaCadiz.setParameter("nombre", "Cadiz");
        
        List<Provincia> listProvinciasCadiz =queryProvinciaCadiz.getResultList();
        for(Provincia provinciaCadiz:listProvinciasCadiz){
            System.out.println(provinciaCadiz.getId()+":");
            System.out.println(provinciaCadiz.getNombre());
        
        
        */
        
        //Cerrar la conexion
        em.close();
        emf.close();
        try{
             DriverManager.getConnection("jdbc:derby:BDAgenda;shutdown=true");
           } catch (SQLException ex){}



    }
    
}
