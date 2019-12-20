package dad.javafx.componentes.datechooser;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DateChooserApp extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		DateChooser prueba = new DateChooser();
		
		BorderPane root = new BorderPane();
		root.setCenter(prueba);

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("DateChooser");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
