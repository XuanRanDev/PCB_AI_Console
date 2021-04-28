package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public ListView<ErrorClass> listview;
    public TableView<TotalClass> tabelView;
    ObservableList<ErrorClass> observableList;
    ObservableList<TotalClass> totalObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initTabelView();

        observableList = listview.getItems();

        initErrorClass(); //读取


        CustomList();



    }

    private void initTabelView() {

        totalObservableList = tabelView.getItems();
        initTableData();


    }

    /**
     * 初始化TabelView数据
     */
    private void initTableData() {

        initTableMoNiData();

        tabelView.setItems(totalObservableList);

        initTabelChildData();//初始化Tabel子项数据
    }

    private void initTabelChildData() {

        TableColumn<TotalClass,String> title_err_name = new TableColumn<>("瑕疵名称");
        TableColumn<TotalClass,String> title_err_num = new TableColumn<>("数量");

        title_err_name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TotalClass, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TotalClass, String> param) {

                SimpleStringProperty simpleStringProperty = new SimpleStringProperty(param.getValue().getTotal_err_cause());
                return simpleStringProperty;
            }
        });

        title_err_num.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TotalClass, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TotalClass, String> param) {

                SimpleStringProperty simpleStringProperty = new SimpleStringProperty(param.getValue().getTotal_err_num());
                return simpleStringProperty;
            }
        });

        //禁用排序
        title_err_name.setSortable(false);
        title_err_num.setSortable(false);

        //add
        tabelView.getColumns().add(title_err_name);
        tabelView.getColumns().add(title_err_num);

    }

    /**
     * 模拟TabelView数据，在release时请delete
     */
    private void initTableMoNiData() {

        String[] err_list = {"疑似不良","缺料","碰伤","缺料"};
        for (int i = 0; i <3; i++) {
            TotalClass totalClass = new TotalClass(err_list[i],"18" );
            totalObservableList.add(totalClass);
        }

    }

    /**
     * 自定义ListView
     */
    private void CustomList() {
        listview.setCellFactory(new Callback<ListView<ErrorClass>, ListCell<ErrorClass>>() {
            @Override
            public ListCell<ErrorClass> call(ListView<ErrorClass> errorClassListView) {

                ListCell<ErrorClass> listCell = new ListCell<>(){

                    /**
                     * @param data 当前瑕疵的类对象
                     * @param b ListView是否为空
                     */
                    @Override
                    protected void updateItem(ErrorClass data, boolean b) {
                        super.updateItem(data, b);

                        if (!b){

                            HBox hBox = new HBox(); //创建一个容器
                            hBox.setMinHeight(50);
                            hBox.setAlignment(Pos.CENTER_LEFT); //对齐方式

                            ImageView imageView = data.getErr_Image();
                            imageView.setFitHeight(80);
                            imageView.setFitWidth(90);
                            Label num = new Label(String.valueOf(data.getErr_no()));
                            Label cause = data.getErr_cause();

                            num.setPadding(new Insets(20));

                            hBox.getChildren().addAll(imageView,num,cause);
                            this.setGraphic(hBox);


                            //onClick

                            hBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {

                                    ShowDialog(data.getErr_Image());

                                }
                            });

                        }

                    }
                };
                return listCell;
            }
        });
    }

    private void ShowDialog(ImageView imageView) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Test");
        ImageView im = imageView;
        im.setFitWidth(1000);
        im.setFitHeight(600);
        alert.setGraphic(im);
        if (!alert.isShowing()) {
            alert.show();
        }

    }

    /**
     * 读取数据库中的内容
     */
    private void initErrorClass() {

        MoNiData(); //模拟数据

    }

    /**
     * 模拟数据
     */
    private void MoNiData() {


        for (int i = 0; i < 10; i++) {
            ErrorClass errorClass = new ErrorClass(new ImageView("sample/a.jpg"),i,"瑕疵详情："+"未知");
            observableList.add(errorClass);
        }

    }
}

class ErrorClass{

    ImageView err_Image; //错误图像
    int err_no;//第几个错误
    Label err_cause;//错误原因

    public ErrorClass(ImageView err_Image, int err_no, String err_cause) {
        this.err_Image = err_Image;
        this.err_no = err_no;
        this.err_cause = new Label(err_cause);
    }

    public ImageView getErr_Image() {
        return err_Image;
    }

    public int getErr_no() {
        return err_no;
    }

    public Label getErr_cause() {
        return err_cause;
    }
}
