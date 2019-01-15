package controllers;

import controllers.priorityController.AggiornaCitta;
import model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Map;

import static controllers.Login_Page_Controller.getUtente;

public class City_Page_Controller {

    @FXML   //fx:id="zone_list"
    private ListView<Button> zone_list;

    @FXML   //  fx:id="colStatus"
    private TableColumn<Zona, String> colStatus; // Value injected by FXMLLoader

    @FXML   //  fx:id="colTemperatura"
    private TableColumn<Zona, Double> colTemperatura; // Value injected by FXMLLoader

    @FXML   //  fx:id="colUmidità"
    private TableColumn<Zona, Double> colUmidità; // Value injected by FXMLLoader

    @FXML   //  fx:id="colPressione"
    private TableColumn<Zona, Integer> colPressione; // Value injected by FXMLLoader

    @FXML   //  fx:id="colLuminosità"
    private TableColumn<Zona, Integer> colLuminosità; // Value injected by FXMLLoader

    @FXML   //  fx:id="colMalfunzionamenti"
    private TableColumn<Zona, Integer> colMalfunzionamenti; // Value injected by FXMLLoader

    @FXML   // fx:id="table"
    private TableView<Zona> table;

    @FXML
    void initialize() {
        Aggiornamento a = new Aggiornamento();
        a.start();
    }


    public class Aggiornamento extends Thread {
        boolean inizializzato = false;
        public void run() {
            while (true) {
                try {
                    String username = getUtente();
                    System.out.println(username);
                    String color;
                    Etichetta e = new Etichetta();
                    AggiornaCitta a = new AggiornaCitta(username, "Citta");
                    Map<String, Integer> map = a.run();
                    for (Map.Entry<String, Integer> entry : map.entrySet()) {
                        switch (entry.getValue()) {
                            case 0:
                                color = "GREEN";
                                break;
                            case 1:
                                color = "YELLOW";
                                break;
                            case 2:
                                color = "ORANGE";
                                break;
                            case 3:
                                color = "RED";
                                break;
                            case 4:
                                color = "PURPLE";
                                break;
                            default:
                                color = "VERDE";
                        }
                        if (!inizializzato) {
                            e.crea(entry.getKey(), color);
                            zone_list.getItems().add(Etichetta.getButton());
                            System.out.println("OK");

                        }
                        else {
                            e.setColor(color);
                            System.out.println("OK");
                        }
                    }
                    inizializzato = true;
                } catch (Exception e) {
                    e.getStackTrace();
                }

                try {
                    sleep(10000);
                } catch (Exception ex) {
                    ex.getStackTrace();
                }
            }
        }
    }


}