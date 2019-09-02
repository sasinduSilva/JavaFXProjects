package Controller;

import LibraryBas.LibrarryDatabase;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.ScaleTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.BooksTM;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ManageBooksController implements Initializable {
    public JFXButton btnNew;
    public JFXTextField txtBookID;
    public JFXTextField txtBookTitle;
    public JFXTextField txtAuthor;
    public Label lblStatus;
    public JFXButton btnAdd;
    public JFXButton btnDelete;
    public TableView<BooksTM> tblBooks;
    public ImageView ImgHome;
    public JFXTextField txtStatus;
    public JFXTextField txtSrchbook;
    public Label lblHome;
    public Label lblAddnewBook;
    public Label lblAddBookImg;

    public void navigateHome(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/DashboardForm.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) this.ImgHome.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }

    public void btnNewOnAction(ActionEvent actionEvent) {

        txtBookID.clear();
        txtBookTitle.clear();
        txtAuthor.clear();
        txtStatus.clear();
        btnAdd.setDisable(false);
        btnAdd.setText("Add");
        txtBookTitle.setDisable(false);
        txtAuthor.setDisable(false);
        txtStatus.setDisable(false);

        int arraySize = tblBooks.getItems().size();
        arraySize++;

        if (arraySize<10){

            txtBookID.setText("B00"+arraySize);
        }else if (arraySize<100){

            txtBookID.setText("B0"+arraySize);
        }



    }

    public void txtBookTitleOnAction(ActionEvent actionEvent) {
    }

    public void txtAuthorOnAction(ActionEvent actionEvent) {
    }

    public void btnAddOnAction(ActionEvent actionEvent) {

        if (btnAdd.getText().equals("Update")){
            BooksTM selectedItem = tblBooks.getSelectionModel().getSelectedItem();
            if (selectedItem==null){

                return;
            }


            selectedItem.setBookTitle(txtBookTitle.getText());
            selectedItem.setAuthorName(txtAuthor.getText());
            selectedItem.setStatus(txtStatus.getText());
            tblBooks.refresh();
            txtBookID.clear();
            txtBookTitle.clear();
            txtAuthor.clear();
            txtStatus.clear();
            txtSrchbook.requestFocus();
            txtStatus.setDisable(true);
            btnAdd.setText("Add");
            txtBookTitle.setDisable(true);
            txtAuthor.setDisable(true);
            return;


        }
        ObservableList<BooksTM> items = tblBooks.getItems();
        items.add(new BooksTM(txtBookID.getText(),txtBookTitle.getText(),txtAuthor.getText(),txtStatus.getText()));
        txtBookID.clear();
        txtBookTitle.clear();
        txtAuthor.clear();
        txtStatus.clear();
        btnAdd.setDisable(true);
        txtBookTitle.setDisable(true);
        txtAuthor.setDisable(true);
        txtStatus.setDisable(true);
        txtSrchbook.requestFocus();


    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {


        ObservableList<BooksTM> items = tblBooks.getItems();
        BooksTM selectedItem = tblBooks.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get()==ButtonType.YES){

            items.remove(selectedItem);
        }else if (buttonType.get()==ButtonType.NO){
            txtBookID.clear();
            txtBookTitle.clear();
            txtAuthor.clear();
            txtStatus.clear();
            tblBooks.refresh();


        }

//        txtBookID.clear();
//        txtBookTitle.clear();
//        txtAuthor.clear();
//        txtStatus.clear();
//        tblBooks.refresh();


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        tblBooks.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("bookId"));
        tblBooks.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        tblBooks.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("authorName"));
        tblBooks.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("status"));

        ObservableList<BooksTM> booksTMS = FXCollections.observableList(LibrarryDatabase.booksTMS);
        tblBooks.setItems(booksTMS);

        btnAdd.setDisable(true);
        txtBookID.setDisable(true);
        txtBookTitle.setDisable(true);
        txtAuthor.setDisable(true);
        txtStatus.setDisable(true);

        tblBooks.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BooksTM>() {
            @Override
            public void changed(ObservableValue<? extends BooksTM> observable, BooksTM oldValue, BooksTM newValue) {
                BooksTM selectedItem = tblBooks.getSelectionModel().getSelectedItem();

                btnAdd.setText("Update");
                btnAdd.setDisable(false);
                txtBookTitle.setText(selectedItem.getBookTitle());
                txtAuthor.setText(selectedItem.getAuthorName());
                txtStatus.setText(selectedItem.getStatus());
                txtBookID.setText(selectedItem.getBookId());
                txtStatus.setDisable(false);
                txtBookTitle.setDisable(false);
                txtAuthor.setDisable(false);


            }
        });

        ObservableList<BooksTM> tempArray = FXCollections.observableArrayList(LibrarryDatabase.booksTMS);

        txtSrchbook.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    ObservableList<BooksTM> defaultArray = FXCollections.observableList(LibrarryDatabase.booksTMS);
                    tblBooks.setItems(defaultArray);
                    tblBooks.refresh();

                    return;

                }else {

                    tblBooks.setItems(tempArray);

                    for (BooksTM booksTM : LibrarryDatabase.booksTMS) {

                        if (booksTM.getBookId().contains(newValue) || booksTM.getBookTitle().contains(newValue) || booksTM.getAuthorName().contains(newValue)){

                            ObservableList<BooksTM> items = tblBooks.getItems();
                            items.clear();
                            items.add(new BooksTM(booksTM.getBookId(),booksTM.getBookTitle(),booksTM.getAuthorName(),booksTM.getStatus()));
                            tblBooks.refresh();

                        }

                    }


                }



            }
        });


    }

    public void mouseEnteredOnAction(MouseEvent mouseEvent) {

        if (mouseEvent.getSource() instanceof ImageView){
            ImageView icon = (ImageView) mouseEvent.getSource();




            icon.setOpacity(0.35);


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

    public void mouseExitedOnAction(MouseEvent mouseEvent) {

        if (mouseEvent.getSource() instanceof ImageView){
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();
            icon.setOpacity(1);





        }
    }


}
