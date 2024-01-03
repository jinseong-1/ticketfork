package Resevation;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Resevation.fxml"));
			Parent root = loader.load();
			
			// BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Root.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			ResevationController controller = loader.getController();
			controller.setScene(scene);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
