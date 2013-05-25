/**
 * 
 */
package Impl;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import inGCore.InGSys.InGApplication;

/**
 * @author Dave (http://about.me/david.herrera)
 *
 */
public class Default extends InGApplication{

	public Default() {}
	
	@Override
	public void start(Stage stage) {
		StackPane pane = new StackPane();
		stage.setTitle("Test Title");
		Button btn = new Button();
		btn.setText("Click");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		pane.getChildren().add(btn);
		stage.setScene(new Scene(pane, 500, 600));
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
