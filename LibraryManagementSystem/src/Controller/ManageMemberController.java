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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.MemberTM;
import javafx.scene.control.*;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageMemberController implements Initializable {
    public ImageView img_Home;
    public JFXButton btnAddNew;
    public JFXTextField txtMembrID;
    public JFXTextField txtMembrName;
    public JFXTextField txtAddress;
    public JFXTextField txtContctNumber;
    public TableView<MemberTM> tblMembrDetails;
    public JFXButton btnAdd;
    public JFXButton btnDelete;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblMembrDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("memberId"));
        tblMembrDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("memberName"));
        tblMembrDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("memberAddress"));
        tblMembrDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("memberCntctNo"));


        ObservableList<MemberTM> memberTMS = FXCollections.observableList(LibrarryDatabase.memberTMS);
        tblMembrDetails.setItems(memberTMS);

        tblMembrDetails.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MemberTM>() {
            @Override
            public void changed(ObservableValue<? extends MemberTM> observable, MemberTM oldValue, MemberTM newValue) {
                MemberTM selectedItem = tblMembrDetails.getSelectionModel().getSelectedItem();
                txtMembrID.setText(selectedItem.getMemberId());
                txtMembrName.setText(selectedItem.getMemberName());
                txtAddress.setText(selectedItem.getMemberAddress());
                txtContctNumber.setText(selectedItem.getMemberCntctNo()+"");

                btnAdd.setText("Update");
                btnDelete.setDisable(false);
            }
        });
    }

    public void navigateHome(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/DashboardForm.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) this.img_Home.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();


    }

    public void btnAddNewOnAction(ActionEvent actionEvent) {

        txtMembrID.clear();
        txtMembrName.clear();
        txtAddress.clear();
        txtContctNumber.clear();
        btnAdd.setDisable(false);

        int arraySize = tblMembrDetails.getItems().size();
        arraySize++;

        if (arraySize<10){

            txtMembrID.setText("M00"+arraySize);
        }else if (arraySize<100){

            txtMembrID.setText("M0"+arraySize);
        }


//        int maxID =0;
//
//        for (MemberTM item : tblMembrDetails.getItems()) {
//
//            int id = Integer.parseInt(item.getMemberId().replace("M",""));
//            if (id>maxID){
//
//                maxID=id;
//                maxID++;
//                if (maxID<10){
//
//                    txtMembrID.setText("M00"+maxID);
//
//
//                }else if (maxID<100){
//
//                    txtMembrID.setText("M0"+maxID);
//                }
//
//            }
//
//        }



    }

    public void txtMembrIDOnAction(ActionEvent actionEvent) {
    }

    public void txtMembrNameOnAction(ActionEvent actionEvent) {
    }

    public void txtAddressOnAction(ActionEvent actionEvent) {
    }

    public void txtContctNumberOnAction(ActionEvent actionEvent) {
    }

    public void btnAddOnAction(ActionEvent actionEvent) {

        if (btnAdd.getText().equals("Update")){
            MemberTM selectedItem = tblMembrDetails.getSelectionModel().getSelectedItem();
            selectedItem.setMemberName(txtMembrName.getText());
            selectedItem.setMemberAddress(txtAddress.getText());
            selectedItem.setMemberCntctNo(Integer.parseInt(txtContctNumber.getText()));
            tblMembrDetails.refresh();
            return;


        }
        

            ObservableList<MemberTM> addToTbl = tblMembrDetails.getItems();
            addToTbl.add(new MemberTM(txtMembrID.getText(),txtMembrName.getText(),txtAddress.getText(),Integer.parseInt(txtContctNumber.getText())));
            btnAdd.setDisable(true);
            txtMembrID.clear();
            txtMembrName.clear();
            txtAddress.clear();
            txtContctNumber.clear();
            txtMembrID.requestFocus();

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

        ObservableList<MemberTM> items = tblMembrDetails.getItems();
        MemberTM selectedItem = tblMembrDetails.getSelectionModel().getSelectedItem();
        items.remove(selectedItem);
        tblMembrDetails.refresh();
    }

    public void mouseEnteredAction(MouseEvent mouseEvent) {

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

    public void mouseExitedAction(MouseEvent mouseEvent) {

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
