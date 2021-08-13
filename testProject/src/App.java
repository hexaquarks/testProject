import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;
import java.awt.Color;

import javax.swing.SwingUtilities;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    public static SampleController controller;

    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("testProjectFx.fxml"));
			Parent root = loader.load();

			controller = (SampleController) loader.getController();

            Scene scene = new Scene(root, 600 , 400);

            BorderPane scenePanel = (BorderPane) scene.lookup("#mainPanel");
		    SwingNode swingNode = new SwingNode();
            MainPanel mainPanel = new MainPanel();

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    mainPanel.setSize(535, 318);
		            mainPanel.setBackground(Color.black);
		            mainPanel.physicsTimer.start();
		            mainPanel.fpsTimer.start();
                    swingNode.setContent(mainPanel);
                }
            });
            scenePanel.setCenter(swingNode);


            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    mainPanel.initializeParticles(15, 100 ,5);
                    controller.initializeButtons(mainPanel);
                }
            });
           
            
            stage.setTitle("FXML Welcome");
            stage.setScene(scene);
            stage.show();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
