package it.polito.tdp.regine;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.regine.model.Regine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class FXMLController {

	private Regine regine;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Spinner<Integer> spinN;

	@FXML
	private StackPane paneScacchiera;

	@FXML
	void handleSolve(ActionEvent event) {

		Integer N = spinN.getValue();
		
		// salvo le caselle della scacchiera
		List<Label> caselle = new ArrayList<Label>();

		// crea la scacchiera
		GridPane gp = new GridPane();
		gp.setGridLinesVisible(true);
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				Label cell = new Label("");
				caselle.add(cell);
				cell.setAlignment(Pos.CENTER);
				cell.setPrefWidth(20);
				cell.setPrefHeight(20);
				if ((row + col) % 2 == 0) {
					cell.setBackground(
							new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
					cell.setTextFill(Color.BLACK);
				} else {
					cell.setBackground(
							new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
					cell.setTextFill(Color.BLACK);
				}
				gp.add(cell, row, col);
			}
		}
		paneScacchiera.getChildren().clear();
		paneScacchiera.getChildren().add(gp);
		gp.setAlignment(Pos.CENTER);

		// Risolvi il problema ricorsivo
		regine = new Regine();
		List<Integer> soluzione = regine.risolvi(N);

		// "Dipingi" la soluzione
		if (soluzione != null) {
			for (int row = 0; row < N; row++) {
				int col = soluzione.get(row);
				Label cell = caselle.get(col * N + row);
				cell.setText("♕");
			}
		}

	}

	@FXML
	void initialize() {
		assert spinN != null : "fx:id=\"spinN\" was not injected: check your FXML file 'Scene.fxml'.";
		assert paneScacchiera != null : "fx:id=\"paneScacchiera\" was not injected: check your FXML file 'Scene.fxml'.";
		spinN.setValueFactory(new IntegerSpinnerValueFactory(2, 20));
	}
}
