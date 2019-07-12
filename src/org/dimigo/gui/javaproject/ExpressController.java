package org.dimigo.gui.javaproject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ExpressController implements Initializable {
    @FXML ComboBox<WayDirection> cbSearch;
    @FXML TextField txtSearch;
    @FXML TableView<ExpressWay> upTableView;
    @FXML TableView<ExpressWay> dnTableView;
    @FXML TableView<ExpressWay> subnameTable;
    @FXML TableView<ExpressWay> distanceTable;
    @FXML TableView<ExpressWay> speedTable;
    @FXML TableView<ExpressWay> timeTable;
    @FXML WebView webView;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        // CombodBox 항목 추가
        ObservableList<WayDirection> comboItems = FXCollections.observableArrayList();
        comboItems.add(new WayDirection("상행","up"));
        comboItems.add(new WayDirection("하행","dn"));
        cbSearch.setItems(comboItems);
    }

    @FXML public void handleSearchAction(ActionEvent event){
        try {
            WayDirection item = cbSearch.getSelectionModel().getSelectedItem();
            String type = item.getValue();
            System.out.printf("type: %s\n", type);

            if ("up".equals(type)) {
                upTableView.setVisible(true);
                dnTableView.setVisible(false);

                List<ExpressWay> upList = ExpresswayCrawling.getexpressList(type);

                ObservableList<ExpressWay> data = FXCollections.observableArrayList(upList);
                OutputStream output = null;
                output = new FileOutputStream("src/org/dimigo/gui/javaproject/expressup.txt");
                for (ExpressWay e : upList) {
                    String name = e.getName();
                    String subname = e.getSubname();
                    String distance = e.getDistance();
                    String speed = e.getSpeed();
                    String time = e.getTime();
                    byte[] by = name.getBytes();
                    byte[] by2 = subname.getBytes();
                    byte[] by3 = distance.getBytes();
                    byte[] by4 = speed.getBytes();
                    byte[] by5 = time.getBytes();
                    output.write(by);
                    output.write(" ".getBytes());
                    output.write(by2);
                    output.write(" ".getBytes());
                    output.write(by3);
                    output.write(" ".getBytes());
                    output.write(by4);
                    output.write(" ".getBytes());
                    output.write(by5);
                    output.write("\n".getBytes());
                }

                upTableView.setItems(data);
                bindupTableColumn();

                WebEngine webEngine = webView.getEngine();
                upTableView.getSelectionModel().selectedItemProperty().addListener(
                        new ChangeListener<ExpressWay>() {
                            @Override
                            public void changed(ObservableValue<? extends ExpressWay> observableValue, ExpressWay oldValue, ExpressWay newValue) {
                                if (newValue != null) {
                                    webEngine.load("https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=" + newValue.getName() + "현황");
                                }
                            }
                        }
                );
            } else if ("dn".equals(type)) {
                upTableView.setVisible(false);
                dnTableView.setVisible(true);

                List<ExpressWay> dnList = ExpresswayCrawling.getexpressList(type);

                ObservableList<ExpressWay> data = FXCollections.observableArrayList(dnList);
                OutputStream output = null;
                output = new FileOutputStream("src/org/dimigo/gui/javaproject/expressdn.txt");
                for (ExpressWay e : dnList) {
                    String name = e.getName();
                    String subname = e.getSubname();
                    String distance = e.getDistance();
                    String speed = e.getSpeed();
                    String time = e.getTime();
                    byte[] by = name.getBytes();
                    byte[] by2 = subname.getBytes();
                    byte[] by3 = distance.getBytes();
                    byte[] by4 = speed.getBytes();
                    byte[] by5 = time.getBytes();
                    output.write(by);
                    output.write(" ".getBytes());
                    output.write(by2);
                    output.write(" ".getBytes());
                    output.write(by3);
                    output.write(" ".getBytes());
                    output.write(by4);
                    output.write(" ".getBytes());
                    output.write(by5);
                    output.write("\n".getBytes());
                }

                dnTableView.setItems(data);
                binddnTableColumn();

                WebEngine webEngine = webView.getEngine();
                dnTableView.getSelectionModel().selectedItemProperty().addListener(
                        new ChangeListener<ExpressWay>() {
                            @Override
                            public void changed(ObservableValue<? extends ExpressWay> observableValue, ExpressWay oldValue, ExpressWay newValue) {
                                if (newValue != null) {
                                    webEngine.load("https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=" + newValue.getName() + "현황");
                                }
                            }
                        }
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch(NullPointerException nul){
            System.out.println("상행 또는 하행을 선택하세요.");
        }
    }

    private void bindupTableColumn(){
        TableColumn name = upTableView.getColumns().get(0);
        name.setCellValueFactory(new PropertyValueFactory<ExpressWay, String>("name"));
    }

    private void binddnTableColumn(){
        TableColumn name = dnTableView.getColumns().get(0);
        name.setCellValueFactory(new PropertyValueFactory<ExpressWay, String>("name"));
    }

    @FXML public void handleSearchAction2(ActionEvent event){
        try {
            WayDirection item = cbSearch.getSelectionModel().getSelectedItem();
            String type = item.getValue();
            String text = txtSearch.getText();
            if(text.equals(""))
                System.out.println("검색할 고속도로명을 입력하세요");
            else {
                System.out.printf("type: %s\n", type);
                System.out.printf("type: %s\n", text);
                if (type.equals("up")) {
                    List<ExpressWay> upList = ExpresswayCrawling.getexpressList(type);
                    for (int i = 0; i < upList.size(); i++) {
                        if (text.equals(upList.get(i).getName())) {
                            List<ExpressWay> theList = new ArrayList<>();
                            theList.add(new ExpressWay(upList.get(i).getName(), upList.get(i).getSubname(), upList.get(i).getDistance(), upList.get(i).getSpeed(), upList.get(i).getTime()));
                            ObservableList<ExpressWay> data = FXCollections.observableArrayList(theList);
                            subnameTable.setItems(data);
                            distanceTable.setItems(data);
                            speedTable.setItems(data);
                            timeTable.setItems(data);
                            bindTableColumn();
                        }
                    }
                } else if (type.equals("dn")) {
                    List<ExpressWay> dnList = ExpresswayCrawling.getexpressList(type);
                    for (int i = 0; i < dnList.size(); i++) {
                        if (text.equals(dnList.get(i).getName())) {
                            List<ExpressWay> theList = new ArrayList<>();
                            theList.add(new ExpressWay(dnList.get(i).getName(), dnList.get(i).getSubname(), dnList.get(i).getDistance(), dnList.get(i).getSpeed(), dnList.get(i).getTime()));
                            ObservableList<ExpressWay> data = FXCollections.observableArrayList(theList);
                            subnameTable.setItems(data);
                            distanceTable.setItems(data);
                            speedTable.setItems(data);
                            timeTable.setItems(data);
                            bindTableColumn();
                        }
                    }
                }
            }
        } catch(NullPointerException nul){
            System.out.println("상행 또는 하행을 선택 한 후");
            System.out.println("검색할 고속도로명을 입력하세요.");
        }
    }
    private void bindTableColumn() {
        TableColumn subname = subnameTable.getColumns().get(0);
        subname.setCellValueFactory(new PropertyValueFactory<ExpressWay, String>("subname"));
        TableColumn distance = distanceTable.getColumns().get(0);
        distance.setCellValueFactory(new PropertyValueFactory<ExpressWay, String>("distance"));
        TableColumn speed = speedTable.getColumns().get(0);
        speed.setCellValueFactory(new PropertyValueFactory<ExpressWay, String>("speed"));
        TableColumn time = timeTable.getColumns().get(0);
        time.setCellValueFactory(new PropertyValueFactory<ExpressWay, String>("time"));
    }
}