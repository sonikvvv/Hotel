package gui.references;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import base_classes.classes.ClientUsedServices;
import base_classes.classes.Clients;
import base_classes.classes.Reservation;
import base_classes.classes.Room;
import base_classes.classes.User;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import logic.DecodeOperation;
import logic.OperationType;

public class ReferencesHomeController implements Initializable {

    @FXML
    private GridPane sub_grid = new GridPane();

    @FXML
    private ComboBox<String> recep_choice;

    @FXML
    private DatePicker toDate;

    @FXML
    private DatePicker fromDate;

    @FXML
    void comboBox(ActionEvent event) {
        
    }

    @FXML
    void RoomRaiting(ActionEvent event) {
        removeNodeByRowColumnIndex(0, 2, sub_grid);
        TableView<Room> room_rait_table = new TableView<>();
        TableColumn<Room, String> room_num_col = new TableColumn<>("Number");
        TableColumn<Room, String> type_col = new TableColumn<>("Type");
        TableColumn<Room, Number> raiting_col = new TableColumn<>("Rating");
        ObservableList<Room> activ = FXCollections.observableArrayList();

        room_num_col.setCellValueFactory(new PropertyValueFactory<>("r_number"));
        type_col.setCellValueFactory(new PropertyValueFactory<>("r_type"));
        raiting_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Room,Number>,ObservableValue<Number>>(){
            @Override
            public ObservableValue<Number> call(CellDataFeatures<Room, Number> param) {
                return new SimpleDoubleProperty(param.getValue().getRait().get(0).getRait_value());
            }
        });

        LocalDate fromD = fromDate.getValue();
        LocalDate toD = toDate.getValue();

        List<String> data = new ArrayList<>();
        data.add(fromD.toString());
        data.add(toD.toString());

        List<?> room = DecodeOperation.decodeLogicOperation(OperationType.ROOM_RAITING, null, data);
        if (room != null && room.size() != 0) {
            for (Object object : room) {
                Room tmp = (Room) object;
                activ.add(tmp);
            }
        }

        room_rait_table.getColumns().add(room_num_col);
        room_rait_table.getColumns().add(type_col);
        room_rait_table.getColumns().add(raiting_col);

        room_rait_table.getItems().setAll(activ);

        sub_grid.add(room_rait_table, 2, 0);
    }

    @FXML
    void clientInfoBtn(ActionEvent event) {
        removeNodeByRowColumnIndex(0, 2, sub_grid);
        TableColumn<Clients, Integer> number_col = new TableColumn<>("ID");
        TableColumn<Clients, String> name_col = new TableColumn<>("Name");
        TableColumn<Clients, String> country_col = new TableColumn<>("Coutry");
        TableColumn<Clients, LocalDate> birth_col = new TableColumn<>("Birth date");
        TableColumn<Clients, String> passsport_number_col = new TableColumn<>("Passport N");
        TableColumn<Clients, LocalDate> passport_date_col = new TableColumn<>("Passport Date");
        TableColumn<Clients, String> vaucher_col = new TableColumn<>("Vaucher");
        TableColumn<Clients, String> sex_col = new TableColumn<>("Sex");
        ObservableList<Clients> activ = FXCollections.observableArrayList();
        TableView<Clients> tv = new TableView<>();

        number_col.setCellValueFactory(new PropertyValueFactory<Clients, Integer>("c_id"));
        name_col.setCellValueFactory(new PropertyValueFactory<Clients, String>("c_name"));
        birth_col.setCellValueFactory(new PropertyValueFactory<Clients, LocalDate>("c_bd"));
        passsport_number_col.setCellValueFactory(new PropertyValueFactory<Clients, String>("c_passport_number"));
        passport_date_col.setCellValueFactory(new PropertyValueFactory<Clients, LocalDate>("c_passport_date"));
        vaucher_col.setCellValueFactory(new PropertyValueFactory<Clients, String>("vaucher"));
        country_col.setCellValueFactory(
            new Callback<TableColumn.CellDataFeatures<Clients, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<Clients, String> param) {
                    return new SimpleStringProperty(param.getValue().getCountry().getCountry_name());
                }
            });
        sex_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Clients,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<Clients, String> param) {
                return new SimpleStringProperty(param.getValue().getC_sex());
            }
        });

        name_col.setMinWidth(250);

        LocalDate fromD = fromDate.getValue();
        LocalDate toD = toDate.getValue();

        List<String> data = new ArrayList<>();
        data.add(fromD.toString());
        data.add(toD.toString());

        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.CLIENT_INFO, null, data);
        if (result != null && result.size() != 0) {
            for (Object object : result) {
                activ.add((Clients) object);
            }
        }      

        tv.getColumns().add(number_col);
        tv.getColumns().add(name_col);
        tv.getColumns().add(birth_col);
        tv.getColumns().add(sex_col);
        tv.getColumns().add(country_col);
        tv.getColumns().add(passsport_number_col);
        tv.getColumns().add(passport_date_col);
        tv.getColumns().add(vaucher_col);

        tv.getItems().setAll(activ);        
        
        sub_grid.add(tv, 2, 0);
    }

    @FXML
    void clientRaiting(ActionEvent event) {
        removeNodeByRowColumnIndex(0, 2, sub_grid);
        TableColumn<Clients, Integer> number_col = new TableColumn<>("ID");
        TableColumn<Clients, String> name_col = new TableColumn<>("Name");
        TableColumn<Clients, Number> rait_col = new TableColumn<>("Rating");
        TableColumn<Clients, String> sex_col = new TableColumn<>("Sex");
        ObservableList<Clients> activ = FXCollections.observableArrayList();
        TableView<Clients> tv = new TableView<>();

        number_col.setCellValueFactory(new PropertyValueFactory<Clients, Integer>("c_id"));
        name_col.setCellValueFactory(new PropertyValueFactory<Clients, String>("c_name"));
        sex_col.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Clients, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<Clients, String> param) {
                        return new SimpleStringProperty(param.getValue().getC_sex());
                    }

                });
        rait_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Clients,Number>,ObservableValue<Number>>(){

            @Override
            public ObservableValue<Number> call(CellDataFeatures<Clients, Number> param) {
                return new SimpleDoubleProperty(param.getValue().getRait().get(0).getRait_value());
            }
            
        });

        name_col.setMinWidth(250);

        LocalDate fromD = fromDate.getValue();
        LocalDate toD = toDate.getValue();

        List<String> data = new ArrayList<>();
        data.add(fromD.toString());
        data.add(toD.toString());

        List<?> res = DecodeOperation.decodeLogicOperation(OperationType.CLIENT_RAITING, null, data);
        if (res != null && res.size() != 0) {
            for (Object object : res) {
                activ.add((Clients) object);
            }
        }

        tv.getColumns().add(number_col);
        tv.getColumns().add(name_col);
        tv.getColumns().add(sex_col);
        tv.getColumns().add(rait_col);

        tv.getItems().setAll(activ);

        sub_grid.add(tv, 2, 0);
    }

    @FXML
    void createdReservations(ActionEvent event) {
        removeNodeByRowColumnIndex(0, 2, sub_grid);
        TableView<Reservation> reserv_table = new TableView<>();
        TableColumn<Reservation, Integer> number_col = new TableColumn<>("ID");
        TableColumn<Reservation, String> status_col = new TableColumn<>("Status");
        TableColumn<Reservation, String> hotel_col = new TableColumn<>("Hotel");
        TableColumn<Reservation, String> client_name_col = new TableColumn<>("Client Name");
        TableColumn<Reservation, String> reserv_type_col = new TableColumn<>("Reserv. Type");
        TableColumn<Reservation, String> room_type_col = new TableColumn<>("Room Type");
        TableColumn<Reservation, String> cancel_type_col = new TableColumn<>("Cancel Type");
        TableColumn<Reservation, String> food_type_col = new TableColumn<>("Food Type");
        TableColumn<Reservation, LocalDate> date_col = new TableColumn<>("Date");
        TableColumn<Reservation, Number> adults_col = new TableColumn<>("Adults");
        TableColumn<Reservation, Number> kids_col = new TableColumn<>("Kids");
        TableColumn<Reservation, Number> babies_col = new TableColumn<>("Babies");
        TableColumn<Reservation, Number> price_col = new TableColumn<>("Price");
        TableColumn<Reservation, String> notes_col = new TableColumn<>("Note");
        ObservableList<Reservation> activ = FXCollections.observableArrayList();

        number_col.setCellValueFactory(new PropertyValueFactory<>("reservation_id"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("date_made"));
        
        status_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
                return new SimpleStringProperty(param.getValue().getReservation_form().getStatus());
            }
        });
        
        client_name_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
                return new SimpleStringProperty(param.getValue().getReservation_form().getClient_name());
            }
        });
        
        reserv_type_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
                return new SimpleStringProperty(param.getValue().getReservation_form().getReservation_type());
            }
        });
        
        room_type_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
                return new SimpleStringProperty(param.getValue().getReservation_form().getRoom_type());
            }
        });
        
        cancel_type_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
                return new SimpleStringProperty(param.getValue().getReservation_form().getCancel_type());
            }
        });
        
        food_type_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
                return new SimpleStringProperty(param.getValue().getReservation_form().getFood_type());
            }
        });
        
        adults_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,Number>,ObservableValue<Number>>(){
            @Override
            public ObservableValue<Number> call(CellDataFeatures<Reservation, Number> param) {
                return new SimpleIntegerProperty(param.getValue().getReservation_form().getAdults());
            }
        });

        kids_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,Number>,ObservableValue<Number>>(){
            @Override
            public ObservableValue<Number> call(CellDataFeatures<Reservation, Number> param) {
                return new SimpleIntegerProperty(param.getValue().getReservation_form().getKids());
            }
        });

        babies_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,Number>,ObservableValue<Number>>(){
            @Override
            public ObservableValue<Number> call(CellDataFeatures<Reservation, Number> param) {
                return new SimpleIntegerProperty(param.getValue().getReservation_form().getBabys());
            }
        });

        price_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,Number>,ObservableValue<Number>>(){
            @Override
            public ObservableValue<Number> call(CellDataFeatures<Reservation, Number> param) {
                return new SimpleDoubleProperty(param.getValue().getReservation_form().getTotal_price());
            }
        });

        notes_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
                return new SimpleStringProperty(param.getValue().getReservation_form().getNotes());
            }
        });

        hotel_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
                return new SimpleStringProperty(param.getValue().getHotel().getHotel_name());
            }
            
        });

        LocalDate fromD = fromDate.getValue();
        LocalDate toD = toDate.getValue();

        List<String> data = new ArrayList<>();
        data.add(fromD.toString());
        data.add(toD.toString());
        

        List<?> res = DecodeOperation.decodeLogicOperation(OperationType.CREATED_RESERVATIONS, null, data);
        if (res != null && res.size() != 0) {
            for (Object object : res) {
                activ.add((Reservation) object);
            }
        }

        reserv_table.getColumns().add(number_col);
        reserv_table.getColumns().add(status_col);
        reserv_table.getColumns().add(hotel_col);
        reserv_table.getColumns().add(client_name_col);
        reserv_table.getColumns().add(reserv_type_col);
        reserv_table.getColumns().add(room_type_col);
        reserv_table.getColumns().add(cancel_type_col);
        reserv_table.getColumns().add(food_type_col);
        reserv_table.getColumns().add(date_col);
        reserv_table.getColumns().add(adults_col);
        reserv_table.getColumns().add(kids_col);
        reserv_table.getColumns().add(babies_col);
        reserv_table.getColumns().add(price_col);
        reserv_table.getColumns().add(notes_col);

        reserv_table.getItems().setAll(activ);

        sub_grid.add(reserv_table, 2, 0);
    }

    public void removeNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        ObservableList<Node> childrens = gridPane.getChildren();
        for (Node node : childrens) {
            if (node instanceof TableView && GridPane.getRowIndex(node) == row
                    && GridPane.getColumnIndex(node) == column) {
                TableView<?> imageView = (TableView<?>)node;
                gridPane.getChildren().remove(imageView);
                break;
            }
        }
    }

    public void removeVbox(final int row, final int column, GridPane gridPane) {
        ObservableList<Node> childrens = gridPane.getChildren();
        boolean flag = false;
        if (childrens.size() > 1) {
            for (Node node : childrens) {
                if (node instanceof VBox) {
                    VBox imageView = (VBox) node;
                    if (flag == true) gridPane.getChildren().remove(imageView);
                    else flag = true;
                    break;
                }
            }
        }
    }

    @FXML
    void createdReservationsRecep(ActionEvent event) { //TODO: show more info for user 
        removeNodeByRowColumnIndex(0, 2, sub_grid);
        removeVbox(0, 2, sub_grid);
        
        String res = recep_choice.getSelectionModel().getSelectedItem();
        VBox vb = new VBox();
        HBox hb = new HBox(2);
        List<Label> labelList = new ArrayList<>();
        labelList.add(new Label("user name: " + res));
        labelList.add(new Label(", text"));
        hb.getChildren().setAll(labelList);

        TableView<Reservation> reserv_table = new TableView<>();
        reserv_table.setPrefHeight(660);
        TableColumn<Reservation, Integer> number_col = new TableColumn<>("ID");
        TableColumn<Reservation, String> status_col = new TableColumn<>("Status");
        TableColumn<Reservation, String> hotel_col = new TableColumn<>("Hotel");
        TableColumn<Reservation, String> client_name_col = new TableColumn<>("Client Name");
        TableColumn<Reservation, String> reserv_type_col = new TableColumn<>("Reserv. Type");
        TableColumn<Reservation, String> room_type_col = new TableColumn<>("Room Type");
        TableColumn<Reservation, String> cancel_type_col = new TableColumn<>("Cancel Type");
        TableColumn<Reservation, String> food_type_col = new TableColumn<>("Food Type");
        TableColumn<Reservation, LocalDate> date_col = new TableColumn<>("Date");
        TableColumn<Reservation, Number> adults_col = new TableColumn<>("Adults");
        TableColumn<Reservation, Number> kids_col = new TableColumn<>("Kids");
        TableColumn<Reservation, Number> babies_col = new TableColumn<>("Babies");
        TableColumn<Reservation, Number> price_col = new TableColumn<>("Price");
        TableColumn<Reservation, String> notes_col = new TableColumn<>("Note");
        ObservableList<Reservation> activ = FXCollections.observableArrayList();

        number_col.setCellValueFactory(new PropertyValueFactory<>("reservation_id"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("date_made"));
        
        status_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
                return new SimpleStringProperty(param.getValue().getReservation_form().getStatus());
            }
        });
        
        client_name_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
                return new SimpleStringProperty(param.getValue().getReservation_form().getClient_name());
            }
        });
        
        reserv_type_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
                return new SimpleStringProperty(param.getValue().getReservation_form().getReservation_type());
            }
        });
        
        room_type_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
                return new SimpleStringProperty(param.getValue().getReservation_form().getRoom_type());
            }
        });
        
        cancel_type_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
                return new SimpleStringProperty(param.getValue().getReservation_form().getCancel_type());
            }
        });
        
        food_type_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
                return new SimpleStringProperty(param.getValue().getReservation_form().getFood_type());
            }
        });
        
        adults_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,Number>,ObservableValue<Number>>(){
            @Override
            public ObservableValue<Number> call(CellDataFeatures<Reservation, Number> param) {
                return new SimpleIntegerProperty(param.getValue().getReservation_form().getAdults());
            }
        });

        kids_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,Number>,ObservableValue<Number>>(){
            @Override
            public ObservableValue<Number> call(CellDataFeatures<Reservation, Number> param) {
                return new SimpleIntegerProperty(param.getValue().getReservation_form().getKids());
            }
        });

        babies_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,Number>,ObservableValue<Number>>(){
            @Override
            public ObservableValue<Number> call(CellDataFeatures<Reservation, Number> param) {
                return new SimpleIntegerProperty(param.getValue().getReservation_form().getBabys());
            }
        });

        price_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,Number>,ObservableValue<Number>>(){
            @Override
            public ObservableValue<Number> call(CellDataFeatures<Reservation, Number> param) {
                return new SimpleDoubleProperty(param.getValue().getReservation_form().getTotal_price());
            }
        });

        notes_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
                return new SimpleStringProperty(param.getValue().getReservation_form().getNotes());
            }
        });

        hotel_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
                return new SimpleStringProperty(param.getValue().getHotel().getHotel_name());
            }
        });

        List<String> data = new ArrayList<>();
        data.add(fromDate.getValue().toString());
        data.add(toDate.getValue().toString());
        String receptionist = recep_choice.getSelectionModel().getSelectedItem();
        if (receptionist == null || receptionist == "") {
            Alert al = new Alert(AlertType.WARNING, "Don't leave drop-down menu empty!");
            al.showAndWait();
        } else {

            data.add(receptionist);
            
            List<?> result = DecodeOperation.decodeLogicOperation(OperationType.RECEPT_CREATED_RESERVATIONS, null, data);

            if (result != null && result.size() != 0) {
                for (Object object : result) {
                    activ.add((Reservation) object);
                }
            }
        }

        reserv_table.getColumns().add(number_col);
        reserv_table.getColumns().add(status_col);
        reserv_table.getColumns().add(hotel_col);
        reserv_table.getColumns().add(client_name_col);
        reserv_table.getColumns().add(reserv_type_col);
        reserv_table.getColumns().add(room_type_col);
        reserv_table.getColumns().add(cancel_type_col);
        reserv_table.getColumns().add(food_type_col);
        reserv_table.getColumns().add(date_col);
        reserv_table.getColumns().add(adults_col);
        reserv_table.getColumns().add(kids_col);
        reserv_table.getColumns().add(babies_col);
        reserv_table.getColumns().add(price_col);
        reserv_table.getColumns().add(notes_col);

        reserv_table.getItems().setAll(activ);

        vb.getChildren().add(hb);
        vb.getChildren().add(reserv_table);

        sub_grid.add(vb, 2, 0);

    }

    @FXML
    void usedServices(ActionEvent event) {
        removeNodeByRowColumnIndex(0, 2, sub_grid);
        TableColumn<ClientUsedServices, String> name_col = new TableColumn<>("Name");
        TableColumn<ClientUsedServices, String> category_col = new TableColumn<>("Category");
        TableColumn<ClientUsedServices, Integer> quantity_col = new TableColumn<>("Quantity");
        ObservableList<ClientUsedServices> activ = FXCollections.observableArrayList();
        TableView<ClientUsedServices> tv = new TableView<>();

        name_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClientUsedServices,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<ClientUsedServices, String> param) {
                return new SimpleStringProperty(param.getValue().getAddit_service().getTitle());
            }
        });

        category_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClientUsedServices,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<ClientUsedServices, String> param) {
                return new SimpleStringProperty(param.getValue().getAddit_service().getCategory().getCategory_title());
            }
        });

        quantity_col.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        LocalDate fromD = fromDate.getValue();
        LocalDate toD = toDate.getValue();

        List<String> data = new ArrayList<>();
        data.add(fromD.toString());
        data.add(toD.toString());

        List<?> res = DecodeOperation.decodeLogicOperation(OperationType.USED_SERVICES, null, data);
        if (res != null && res.size() != 0) {
            for (Object object : res) {
                activ.add((ClientUsedServices) object);
            }
        }

        tv.getColumns().add(name_col);
        tv.getColumns().add(category_col);
        tv.getColumns().add(quantity_col);

        tv.getItems().setAll(activ);

        sub_grid.add(tv, 2, 0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<?> receptionists = DecodeOperation.decodeLogicOperation(OperationType.GET_RECEPTIONIST, null, null);
        if (receptionists != null && receptionists.size() != 0) {
            for (Object object : receptionists) {
                User tmp = (User) object;
                recep_choice.getItems().add(tmp.getUser_name());
            }
        }
        fromDate.setValue(LocalDate.now());
        toDate.setValue(LocalDate.now());
        
    }

}

    