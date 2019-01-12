/*
 * 
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

 
/**
 * The Class DrawerController.
 */
public class DrawerController implements Initializable {
	
	/** The create btn. */
	@FXML
	private JFXButton createBtn;

	/** The edit btn. */
	@FXML
	private JFXButton editBtn;

	/** The show btn. */
	@FXML
	private JFXButton showBtn;

	/** The reserve btn. */
	@FXML
	private JFXButton reserveBtn;

	/** The reserved btn. */
	@FXML
	private JFXButton reservedBtn;

	/** The edit. */
	private Image createNew, reserve, reservedPatients, edit;
	
	/** The image edit. */
	private ImageView imageCreateNew, imageReserve, imageReservedPatients, imageEdit;

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initIcons();
		iniIconsOpacity();
		initIconsTranslateX();
		createBtn.setGraphic(imageCreateNew);
		editBtn.setGraphic(imageEdit);
		reserveBtn.setGraphic(imageReserve);
		reservedBtn.setGraphic(imageReservedPatients);

	}

	/**
	 * Starts a new record page window.
	 *
	 * @return void
	 * @throws Exception the exception
	 */
	public void startNewRecordPage() throws Exception {
		Stage window = new Stage();
		Scene scene;
		window.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("/resources/view/newRecordPage.fxml"));
		window.setTitle("eKarton - Kreiranje novog kartona v1.5.1");
		scene = new Scene(root, 800, 500);
		window.setResizable(false);
		scene.getStylesheets().add("/resources/css/style.css");
		window.setScene(scene);
		window.showAndWait();
	}

	
	/**
	 * Starts a new edit record page window.
	 *
	 * @return void
	 * @throws Exception the exception
	 */
	public void startEditRecordPage() throws Exception {
		Stage window = new Stage();
		Scene scene;
		window.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("/resources/view/editRecordPage.fxml"));
		window.setTitle("eKarton - Izmena postojaćeg kartona v1.5.1");
		scene = new Scene(root, 800, 500);
		window.setResizable(false);
		scene.getStylesheets().add("/resources/css/style.css");
		window.setScene(scene);
		window.showAndWait();
	}

	/**
	 * Starts a new reservation page window.
	 *
	 * @return void
	 * @throws Exception the exception
	 */
	public void startReservePage() throws Exception {
		Stage window = new Stage();
		Scene scene;
		window.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("/resources/view/reservePage.fxml"));
		window.setTitle("eKarton - Zakazivanje pregleda v1.5.1");
		scene = new Scene(root, 800, 500);
		window.setResizable(false);
		scene.getStylesheets().add("/resources/css/style.css");
		window.setScene(scene);
		window.showAndWait();
	}

	/**
	 * Starts a new reserved records page window.
	 *
	 * @return void
	 * @throws Exception the exception
	 */
	public void startReservedPage() throws Exception {
		Stage window = new Stage();
		Scene scene;
		window.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("/resources/view/reservedPage.fxml"));
		window.setTitle("eKarton - Zakazani pregleda v1.5.1");
		scene = new Scene(root, 800, 500);
		scene.getStylesheets().add("/resources/css/style.css");
		window.setScene(scene);
		window.showAndWait();
	}

	/**
	 * Initializes control icons.
	 * 
	 * @return void
	 * */
	private void initIcons() {
		createNew = new Image(getClass().getResourceAsStream("/resources/images/create.png"));
		reserve = new Image(getClass().getResourceAsStream("/resources/images/reserve.png"));
		reservedPatients = new Image(getClass().getResourceAsStream("/resources/images/reserved.png"));
		edit = new Image(getClass().getResourceAsStream("/resources/images/edit.png"));
	}

	
	/**
	 * Adjusts control icons opacity.
	 * 
	 * */
	private void iniIconsOpacity() {
		imageCreateNew = new ImageView(createNew);
		imageCreateNew.setOpacity(0.5);
		imageReserve = new ImageView(reserve);
		imageReserve.setOpacity(0.5);
		imageReservedPatients = new ImageView(reservedPatients);
		imageReservedPatients.setOpacity(0.5);
		imageEdit = new ImageView(edit);
		imageEdit.setOpacity(0.5);
	}

	/**
	 * Translates control icons on the X axis.
	 * 
	 * @return void
	 * */
	private void initIconsTranslateX() {
		imageCreateNew.setTranslateX(-10);
		imageReserve.setTranslateX(-7);
		imageReservedPatients.setTranslateX(-25);
		imageEdit.setTranslateX(-10);
	}
}
