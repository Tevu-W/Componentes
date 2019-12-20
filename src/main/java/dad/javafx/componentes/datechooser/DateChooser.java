package dad.javafx.componentes.datechooser;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Year;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class DateChooser extends GridPane implements Initializable {

	// Modelo
	ObjectProperty<LocalDate> date = new SimpleObjectProperty<LocalDate>();

	ListProperty<Integer> diasProperty = new SimpleListProperty<Integer>(FXCollections.observableArrayList());
	ListProperty<String> mesProperty = new SimpleListProperty<String>(FXCollections.observableArrayList());
	ListProperty<Integer> añoProperty = new SimpleListProperty<Integer>(FXCollections.observableArrayList());

	// Vista

	@FXML
	private GridPane root;

	@FXML
	private HBox combos;

	@FXML
	private ComboBox<Integer> diaCombo;

	@FXML
	private ComboBox<String> mesCombo;

	@FXML
	private ComboBox<Integer> añoCombo;

	public DateChooser() {

		super();

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DateChooser.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		diaCombo.itemsProperty().bindBidirectional(diasProperty);
		añoCombo.itemsProperty().bindBidirectional(añoProperty);
		mesCombo.itemsProperty().bindBidirectional(mesProperty);

		date.set(LocalDate.now());

		// System.out.println(date.get());

		mesProperty.addAll("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
				"Octubre", "Noviembre", "Diciembre");
		
		
		for (int i = date.get().getYear(); i >= 1900; i--) {
			añoProperty.addAll(i);
		}	

	}

	@FXML
	void MesAction(ActionEvent event) {

		cambioDiasMes(mesCombo.getSelectionModel().getSelectedItem());

	}

	void cambioDiasMes(String mes) {

		diasProperty.clear();
		switch (mes) {
		case "Enero":
		case "Marzo":
		case "Mayo":
		case "Julio":
		case "Agosto":
		case "Octubre":
		case "Diciembre":
			for (int i = 1; i <= 31; i++) {
				diasProperty.add(i);
			}
			break;

		case "Abril":
		case "Junio":
		case "Septiembre":
		case "Noviembre":

			diasProperty.clear();
			for (int i = 1; i <= 30; i++) {
				diasProperty.add(i);
			}
			break;

		case "Febrero":
			cambioDias("Febrero", String.valueOf(añoCombo.getSelectionModel().getSelectedItem()));
		default:
			break;
		}

	}

	void cambioDias(String mes, String año) {

		boolean bisiesto;

		bisiesto = Year.of(Integer.valueOf(año)).isLeap();

		diasProperty.clear();
		switch (mes) {
		case "Enero":
		case "Marzo":
		case "Mayo":
		case "Julio":
		case "Agosto":
		case "Octubre":
		case "Diciembre":
			for (int i = 1; i <= 31; i++) {
				diasProperty.add(i);
			}
			break;

		case "Abril":
		case "Junio":
		case "Septiembre":
		case "Noviembre":

			diasProperty.clear();
			for (int i = 1; i <= 30; i++) {
				diasProperty.add(i);
			}
			break;
		case "Febrero":
			if (bisiesto == true) {
				diasProperty.clear();
				for (int i = 1; i <= 29; i++) {
					diasProperty.add(i);
				}
			} else {
				diasProperty.clear();
				for (int i = 1; i <= 28; i++) {
					diasProperty.add(i);
				}
			}
			break;
		default:
			break;
		}
	}

	@FXML
	void AñoAction(ActionEvent event) {

		String seleccionado = String.valueOf(añoCombo.getSelectionModel().getSelectedItem());

		cambioDias(mesCombo.getSelectionModel().getSelectedItem(), seleccionado);

	}

	@FXML
	void consultar(ActionEvent event) {

		Alert informacion = new Alert(AlertType.INFORMATION);

		informacion.setTitle("DateChooser");
		informacion.setHeaderText("Fecha Seleccionada");

		if (date.get().getDayOfMonth() < 10) {
			informacion.setContentText(
					"La fecha seleccionada es: 0" + date.get().getDayOfMonth() + "/" + date.get().getMonthValue() + "/" + date.get().getYear());
		} else {
			informacion.setContentText(
					"La fecha seleccionada es: " + date.get().getDayOfMonth() + "/" + date.get().getMonthValue() + "/" + date.get().getYear());
		}
		
		informacion.showAndWait();

	}

	@FXML
	void inicializar(ActionEvent event) {

		int n = 0;
		switch (mesCombo.getSelectionModel().getSelectedItem()) {

		case "Enero":
			n = 1;
			break;
		case "Febrero":
			n = 2;
			break;
		case "Marzo":
			n = 3;
			break;
		case "Abril":
			n = 4;
			break;
		case "Mayo":
			n = 5;
			break;
		case "Junio":
			n = 6;
			break;
		case "Julio":
			n = 7;
			break;
		case "Agosto":
			n = 8;
			break;
		case "Septiembre":
			n = 9;
			break;
		case "Octubre":
			n = 10;
			break;
		case "Noviembre":
			n = 11;
			break;
		case "Diciembre":
			n = 12;
			break;

		}

		int dia;
		String año;

		dia = diaCombo.getSelectionModel().getSelectedItem();
		año = String.valueOf(añoCombo.getSelectionModel().getSelectedItem());

		date.set(LocalDate.of(Integer.valueOf(año), n, dia));
		System.out.println(date.get());

	}

	public ObjectProperty<LocalDate> dateProperty() {
		return this.date;
	}

	public LocalDate getDate() {
		return this.dateProperty().get();
	}

	public void setDate(final LocalDate date) {
		this.dateProperty().set(date);
	}

	public ListProperty<Integer> diasPropertyProperty() {
		return this.diasProperty;
	}

	public ObservableList<Integer> getDiasProperty() {
		return this.diasPropertyProperty().get();
	}

	public void setDiasProperty(final ObservableList<Integer> diasProperty) {
		this.diasPropertyProperty().set(diasProperty);
	}

	public ListProperty<String> mesPropertyProperty() {
		return this.mesProperty;
	}

	public ObservableList<String> getMesProperty() {
		return this.mesPropertyProperty().get();
	}

	public void setMesProperty(final ObservableList<String> mesProperty) {
		this.mesPropertyProperty().set(mesProperty);
	}

	public ListProperty<Integer> añoPropertyProperty() {
		return this.añoProperty;
	}

	public ObservableList<Integer> getAñoProperty() {
		return this.añoPropertyProperty().get();
	}

	public void setAñoProperty(final ObservableList<Integer> añoProperty) {
		this.añoPropertyProperty().set(añoProperty);
	}

}
