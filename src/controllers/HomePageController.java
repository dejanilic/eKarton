/*
 * 
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

 
/**
 * The Class HomePageController.
 */
public class HomePageController implements Initializable {
	
	/** The border pane. */
	@FXML
	private BorderPane borderPane;
	
    /** The search input text field. */
    @FXML
    private JFXTextField searchInputTextField;

	/** The top anchor pane. */
	@FXML
	private AnchorPane topAnchorPane;

	/** The drawer. */
	@FXML
	private JFXDrawer drawer;

	/** The statistics btn. */
	@FXML
	private JFXButton statisticsBtn;

	/** The change pasword btn. */
	@FXML
	private JFXButton changePaswordBtn;

	/** The quit btn. */
	@FXML
	private JFXButton quitBtn;

	/** The hamburger. */
	@FXML
	private JFXHamburger hamburger;
	
    /** The main search btn. */
    @FXML
    private JFXButton mainSearchBtn;
    
    /** The doctor name lbl. */
    @FXML
    private Label doctorNameLbl;
    
    /** The username. */
    private static String username;
    
    /** The text input. */
    private static JFXTextField textInput = new JFXTextField();
	
	/** The search. */
	private Image power, changePassword, statistics, search;
	
	/** The image search. */
	private ImageView imagePower, imageChangePassword, imageStatistics, imageSearch;

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		textInput = searchInputTextField;
		username = LoginPageController.getUserName();
		doctorNameLbl.setText("Dr. " + username);
		initIcons();
		quitBtn.setGraphic(imagePower);
		setButtonGraphic();
		try {
			VBox box = FXMLLoader.load(getClass().getResource("/resources/view/homePageDrawerContent.fxml"));
			drawer.setSidePane(box);
			HamburgerBasicCloseTransition transition = new HamburgerBasicCloseTransition(hamburger);
			transition.setRate(-1);
			hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
				transition.setRate(transition.getRate() * -1);
				transition.play();
				if (drawer.isShown()) {
					drawer.close();
				} else {
					drawer.open();
				}
			});
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Starts a new search files page window.
	 *
	 * @return void
	 * @throws Exception the exception
	 */
	public void searchFiles() throws Exception{
		Stage window = new Stage();
		Scene scene;
		window.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("/resources/view/filesPage.fxml"));
		window.setTitle("eKarton - Prikaz kartona v1.5.1");
		scene = new Scene(root, 800, 500);
		scene.getStylesheets().add("/resources/css/style.css");
		window.setScene(scene);
		window.showAndWait();
	}
	
	/**
	 * Gets the input.
	 *
	 * @return the input
	 */
	public static String getInput() {
		return textInput.getText();
	}
	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public static String getUsername() {
		return username;
	}
	
	/**
	 * Checks once again if you are sure to exit the application.
	 * 
	 * @return void
	 * */
	public void exit() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("eKarton - Poruka v1.5.1");
		alert.setHeaderText("Izlaz");
		alert.setContentText("Da li ste sigurni da želite da izađete iz programa?");
		if (alert.showAndWait().get() == ButtonType.OK)
			((Stage) quitBtn.getScene().getWindow()).close();
	}
	
	/**
	 * Starts a new statistics page window.
	 *
	 * @return void
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void showStatistics() throws IOException {
		Stage window = new Stage();
		Scene scene;
		window.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("/resources/view/statisticsInfoPage.fxml"));
		window.setTitle("eKarton - Prikaz kartona v1.5.1");
		scene = new Scene(root, 386, 448);
		scene.getStylesheets().add("/resources/css/style.css");
		window.setScene(scene);
		window.showAndWait();
	}
	
	/**
	 * Changes an user's password.
	 *
	 * @return void
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void changePassword() throws IOException {
		Stage window = new Stage();
		Scene scene;
		window.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("/resources/view/changePasswordForm.fxml"));
		window.setTitle("eKarton - Prikaz kartona v1.5.1");
		scene = new Scene(root, 386, 448);
		scene.getStylesheets().add("/resources/css/style.css");
		window.setScene(scene);
		window.showAndWait();
	}
	
	/**
	 * Sets control button's image.
	 * 
	 * @return void
	 * */
	private void setButtonGraphic() {
		changePaswordBtn.setGraphic(imageChangePassword);
		statisticsBtn.setGraphic(imageStatistics);
    	mainSearchBtn.setGraphic(imageSearch);
	}
	
	/**
	 * Initializes images.
	 * 
	 * @return void
	 * */
	private void initIcons() {
		power = new Image(getClass().getResourceAsStream("/resources/images/power.png"));
		imagePower = new ImageView(power);
		changePassword = new Image(getClass().getResourceAsStream("/resources/images/changePassword.png"));
		imageChangePassword = new ImageView(changePassword);
		statistics = new Image(getClass().getResourceAsStream("/resources/images/chart.png"));
		imageStatistics = new ImageView(statistics);
		search = new Image(getClass().getResourceAsStream("/resources/images/mainSearch.png"));
		imageSearch = new ImageView(search);
		imageSearch.setOpacity(0.7);
	}
}
