
package appagenda;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Pablo Rodriguez Pino 2DAM
 */
public class Main extends Application {
    
    //Establecemos conexión con la base de datos
    private EntityManagerFactory emf;
    private EntityManager em;
    
    @Override
    public void start(Stage primaryStage)throws IOException{
        
        //Conectamos y cargamos el archivo fxml y lo asociamos como elemento raíz de la ventana
        StackPane rootMain = new StackPane();
        FXMLLoader fxmlLoader = new
            FXMLLoader(getClass().getResource("AgendaView.fxml"));
        Pane rootAgendaView=fxmlLoader.load();
        rootMain.getChildren().add(rootAgendaView);
        
        //  Conexión a la BD creando los objetos EntityManager y EntityManagerFactory
        emf=Persistence.createEntityManagerFactory("AppAgendaPU");
        em=emf.createEntityManager();
        
        // Asociacion con el controlador mediante un objeto para realizar las funciones de control de la ventana
        AgendaViewController agendaViewController = (AgendaViewController)fxmlLoader.getController();

        // Pasamos el EntityManager a la clase controladora mediante set metodo get creado
        agendaViewController.setEntityManager(em);
        agendaViewController.cargarTodasPersonas();


        
        //Creamos la escena
        Scene scene = new Scene(rootMain,600,400);
        
        //Personalizamos el Stage
        primaryStage.setTitle("App Agenda");
        primaryStage.setScene(scene);
        primaryStage.show();
        }

    @Override
    public void stop() throws Exception {
        em.close();
        emf.close();
        try{
            DriverManager.getConnection("jdbc:derby:BDAgenda;shutdown=true");
        } catch(SQLException ex) {}
    }
    
    


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
