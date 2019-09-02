package Controller;

import LibraryBas.LibrarryDatabase;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class IssueBooksController implements Initializable {
    public ImageView imgHome;
    public Label lblDate;
    public JFXTextField txtIssueID;
    public JFXTextField txtMemberId;
    public JFXTextField txtBookId;
    public JFXButton btnIssue;
    public JFXTextField txtReturningDay;
    public TableView<IssuedBookdTM> tbl_IssueData;

    LocalDateTime returnDate01;

    public void navigate(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/DashboardForm.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) this.imgHome.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();

    }

    public void btnIssueOnAction(ActionEvent actionEvent) {
        LocalDateTimeStringConverter converter = new LocalDateTimeStringConverter();

        ObservableList<IssuedBookdTM> items = tbl_IssueData.getItems();
        items.add(new IssuedBookdTM(txtIssueID.getText(),txtMemberId.getText(),txtBookId.getText(),lblDate.getText(),returnDate01));
        for (BooksTM booksTM : LibrarryDatabase.booksTMS) {

            if (txtBookId.getText().equals(booksTM.getBookId())){
                booksTM.setStatus("Not Available");
            }

        }
        txtMemberId.clear();
        txtReturningDay.clear();
        txtBookId.clear();
        generateIssueID();

        final String DATE_FORMAT = "yyyy/MM/dd";
        DateFormat DateFormat = new SimpleDateFormat(DATE_FORMAT);
        DateTimeFormatter dateFormat8 = DateTimeFormatter.ofPattern(DATE_FORMAT);


        //Get current date
        Date currentDate = new Date();
        System.out.println("date :"+ currentDate);

        LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println("localDateTime : " + dateFormat8.format(localDateTime));

        //Adding one for the localDate
        localDateTime = localDateTime.plusYears(0).plusMonths(0).plusDays(7);
        System.out.println(dateFormat8.format(localDateTime));
        txtReturningDay.setText(dateFormat8.format(localDateTime));



    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tbl_IssueData.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("date"));
        tbl_IssueData.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("issueID"));
        tbl_IssueData.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("bookId"));
        tbl_IssueData.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("memberId"));
        tbl_IssueData.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("returningDay"));


        ObservableList<IssuedBookdTM> issuedBookdTMS = FXCollections.observableList(LibrarryDatabase.issuedBookdTMS);
        tbl_IssueData.setItems(issuedBookdTMS);


        lblDate.setText(String.valueOf(LocalDate.now()));

        generateIssueID();
        txtReturningDay.setDisable(true);


        final String DATE_FORMAT = "yyyy/MM/dd";
        DateFormat DateFormat = new SimpleDateFormat(DATE_FORMAT);
        DateTimeFormatter dateFormat8 = DateTimeFormatter.ofPattern(DATE_FORMAT);


        //Get current date
        Date currentDate = new Date();
        System.out.println("date :"+ currentDate);

        LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println("localDateTime : " + dateFormat8.format(localDateTime));

        //Adding one for the localDate
        localDateTime = localDateTime.plusYears(0).plusMonths(0).plusDays(7);
        returnDate01 = localDateTime;
        System.out.println(dateFormat8.format(localDateTime));
        txtReturningDay.setText(dateFormat8.format(localDateTime));






    }
    public void generateIssueID(){

        int arraySize = tbl_IssueData.getItems().size();
        arraySize++;

        if (arraySize<10){

            txtIssueID.setText("I00"+arraySize);
        }else if (arraySize<100){

            txtIssueID.setText("I0"+arraySize);
        }else {

            txtIssueID.setText("I"+arraySize);
        }
    }
}
