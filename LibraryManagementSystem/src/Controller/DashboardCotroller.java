package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardCotroller implements Initializable {
    public ImageView img_returnBooks;
    public ImageView img_managerMembr;
    public ImageView img_manageBooks;
    public ImageView img_issuBooks;
    public ImageView img_home;
    public AnchorPane root;
    public Label lblMenu;
    public Label lblDesc;
    public JFXButton btnExit;


    public void navigate(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() instanceof ImageView){

            ImageView icon = (ImageView) mouseEvent.getSource();

            Parent root = null;


            switch (icon.getId()){
                case "img_managerMembr" :
                    root = FXMLLoader.load(this.getClass().getResource("/view/ManageMemberForm.fxml"));
                    break;
                case "img_manageBooks" :
                    root = FXMLLoader.load(this.getClass().getResource("/view/ManageBooksForm.fxml"));
                    break;

                case "img_issuBooks" :
                    root = FXMLLoader.load(this.getClass().getResource("/view/IssueBooksForm.fxml"));
                    break;

                case "img_returnBooks" :
                    root = FXMLLoader.load(this.getClass().getResource("/view/ReturnBooksForm.fxml"));
                    break;


            }


            if (root!= null){

                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();



            }
        }

    }

    public void mouseEnteredAction(MouseEvent mouseEvent) {

        if (mouseEvent.getSource() instanceof ImageView){
            ImageView icon = (ImageView) mouseEvent.getSource();

            switch(icon.getId()){
                case "img_managerMembr":
                    img_managerMembr.setOpacity(0.35);
                    lblDesc.setText("Manage Members");
                    break;
                case "img_manageBooks":
                    img_manageBooks.setOpacity(0.35);
                    lblDesc.setText("Manage Books");
                    break;
                case "img_issuBooks":
                    img_issuBooks.setOpacity(0.35);
                    lblDesc.setText("Issue a Book");
                    break;
                case "img_returnBooks":
                    img_returnBooks.setOpacity(0.35);
                    lblDesc.setText("Returning a book");
                    break;
            }

            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }

    }

    public void mouseExitedAction(MouseEvent mouseEvent) {

        if (mouseEvent.getSource() instanceof ImageView){
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();


        }
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            switch (icon.getId()) {
                case "img_managerMembr":
                    img_managerMembr.setOpacity(1);
                    break;
                case "img_manageBooks":
                    img_manageBooks.setOpacity(1);
                    break;
                case "img_issuBooks":
                    img_issuBooks.setOpacity(1);
                    break;
                case "img_returnBooks":
                    img_returnBooks.setOpacity(1);
                    break;
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void btnExitOnAction(ActionEvent actionEvent) {
        Platform.exit();
    }
}
