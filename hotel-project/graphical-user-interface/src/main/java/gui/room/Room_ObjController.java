package gui.room;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class Room_ObjController {

    @FXML
    private Pane room_obj_pane;
    
    @FXML
    private Label number_label;

    @FXML
    private Label type_l;

    @FXML
    private Label client_name_l;

    @FXML
    private Circle statut_obj;

    public void setClient_name_l(String value) {
        this.client_name_l.setText(value);
    }

    public void setNumber_label(String value) {
        this.number_label.setText(value);
    }

    public void setStatut_obj(Circle statut_obj) {
        this.statut_obj = statut_obj;
    }

    public void setType_l(String value) {
        this.type_l.setText(value);
    }

    public Pane getRoom_obj_pane() {
        return room_obj_pane;
    }

    @FXML
    void openRoomView(MouseEvent event) {
        //TODO: open room view
    }

}
