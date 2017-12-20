package eu.telecomnancy.pcd2k17;



import javax.ws.rs.core.Application;


import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Temp extends Application {

	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/temp.fxml"));

		Scene scene = new Scene(root, 600, 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Temp");
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.show();
	}

	public static void switchWindow(Stage window, Application app) {
		try {
			((Temp) app).start(window);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}