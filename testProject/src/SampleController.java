import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class SampleController {

    @FXML
    private BorderPane mainPanel;

    @FXML
    private Button addParticle;

    public void initializeButtons(MainPanel mainPanel) {
        addParticle.addEventHandler(ActionEvent.ACTION, event -> mainPanel.addParticle(
            100, 5
        ));
    }

}
