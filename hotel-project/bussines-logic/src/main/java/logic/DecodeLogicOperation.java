package logic;

import java.util.ArrayList;
import java.util.List;
import logic.operations.*;

public class DecodeLogicOperation {
    public static List<String> decodeLogicOperation(OperationType type, List<String> data) {
        List<String> result = new ArrayList<>();
        switch (type){
            case LOGIN:
                result = LoginOperation.authenticationOperation(data);
                break;
            case CLIENT_INFO:
                
                break;
            case CREATED_RESERV:
                break;
            case HOTEL_AND_SERVICES:
                break;
            case RECEPTIONIST:
                break;
            case ROOM_RAITING:
                break;
            default:
                break;
        }

        return result;
    }
}
