package Controller;

import LibraryBas.LibrarryDatabase;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateTimeStringConverter;
import util.BooksTM;
import util.IssuedBookdTM;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class ReturnBooksController implements Initializable {
    public ImageView imgHome;
    public JFXTextField txtSrch;
    public TableView<IssuedBookdTM> tbl_IssueData;

    public JFXButton btnReturned;
    public JFXTextField txtRtnDay;
    public Label lblTotalFine;

    final String DATE_FORMAT = "yyyy/MM/dd";
    DateFormat DateFormat = new SimpleDateFormat(DATE_FORMAT);
    DateTimeFormatter dateFormat8 = DateTimeFormatter.ofPattern(DATE_FORMAT);

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        tbl_IssueData.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("date"));
        tbl_IssueData.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("issueID"));
        tbl_IssueData.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("bookId"));
        tbl_IssueData.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("memberId"));
        tbl_IssueData.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("returningDay"));

        ObservableList<IssuedBookdTM> issuedBookdTMS = FXCollections.observableList(LibrarryDatabase.issuedBookdTMS);
        tbl_IssueData.setItems(issuedBookdTMS);

        ObservableList<IssuedBookdTM> tempArray = FXCollections.observableArrayList(LibrarryDatabase.issuedBookdTMS);

        txtSrch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){

                    tbl_IssueData.setItems(issuedBookdTMS);
                    tbl_IssueData.refresh();
                    return;

                }else {

                    tbl_IssueData.setItems(tempArray);

                    for (IssuedBookdTM issuedBookdTM : LibrarryDatabase.issuedBookdTMS) {

                        if (issuedBookdTM.getIssueID().contains(newValue) || issuedBookdTM.getMemberId().contains(newValue) || issuedBookdTM.getBookId().contains(newValue) || issuedBookdTM.getDate().contains(newValue)){

                            ObservableList<IssuedBookdTM> items = tbl_IssueData.getItems();
                            items.clear();
                            items.add(new IssuedBookdTM(issuedBookdTM.getIssueID(),issuedBookdTM.getMemberId(),issuedBookdTM.getBookId(),issuedBookdTM.getDate(),issuedBookdTM.getReturningDay()));
                            tbl_IssueData.refresh();

                        }

                    }

                }
            }
        });

        tbl_IssueData.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<IssuedBookdTM>() {
            @Override
            public void changed(ObservableValue<? extends IssuedBookdTM> observable, IssuedBookdTM oldValue, IssuedBookdTM newValue) {
                IssuedBookdTM selectedItem = tbl_IssueData.getSelectionModel().getSelectedItem();


                getDifferenceDays(selectedItem.getReturningDay());





            }
        });

    }

    public void navigateHome(MouseEvent mouseEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/view/DashboardForm.fxml"));
        Scene scene = new Scene(root);
        Stage primarySatge = (Stage) this.imgHome.getScene().getWindow();
        primarySatge.setScene(scene);
        primarySatge.centerOnScreen();
    }

    public void btnReturnedOnAction(ActionEvent actionEvent) {
       IssuedBookdTM selectedBook =  tbl_IssueData.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Did you collect the total fine from Member??",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES){

            selectedBook.setReturningDay(null);
            tbl_IssueData.refresh();

            for (BooksTM booksTM : LibrarryDatabase.booksTMS) {
                if (selectedBook.getBookId().equals(booksTM.getBookId())){

                    booksTM.setStatus("Available");

                }

            }



        }









    }

    public  void getDifferenceDays(LocalDateTime returnDay) {


        LocalDateTime currentDay = LocalDateTime.now();

        int diffNano = (int) java.time.Duration.between(returnDay,currentDay).toDays();
        double totalFine = 15*diffNano;
        if (totalFine<0){
            lblTotalFine.setText("There is no fine");
            return;
        }else {lblTotalFine.setText(String.valueOf(totalFine));}

        System.out.println(diffNano);






    }
}
