package mvcpro.view;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mvcpro.controlled.UiMessageBoxController;

public class UiMessageBox{

    private  Stage newStage;
    private  Scene scene;
    private UiMessageBoxController uiMessageBoxController;
    private double lastx_distance,lasty_distance;
    public  UiMessageBox(){
        init();
    }
    public  void init(){
            try{
                newStage=new Stage();
                //
                //获取控制类对象
                //
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/ui_messagebox_layout.fxml"));
                Pane root =loader.load();

                //
                //获取控制类对象引用
                //
                uiMessageBoxController=loader.getController();
                uiMessageBoxController.setUiMessageBox(newStage);

                //
                //设置背景颜色
                //
                Background bg=new Background(new BackgroundFill(Color.valueOf("282828BF"),new CornerRadii(7),new Insets(0)));
                root.setBackground(bg);

                scene=new Scene(root);

                //
                //加载CSS文件
                //
                scene.getStylesheets().add(getClass().getResource("/css/ui_messagebox_style.css").toExternalForm());

                //
                //设置场景透明
                //
                scene.setFill(Paint.valueOf("#FFFFFF00"));
                newStage.initStyle(StageStyle.TRANSPARENT);
                newStage.setScene(scene);
                scene.setOnMousePressed(event -> {
                    lastx_distance=event.getScreenX()-newStage.getX();
                    lasty_distance=event.getScreenY()-newStage.getY();
                });

                scene.setOnMouseDragged(event -> {
                    newStage.setX(event.getScreenX()-lastx_distance);
                    newStage.setY(event.getScreenY()-lasty_distance);
                });

            }catch(Exception e){
                e.printStackTrace();
            }
    }
    public void showMessageBox(String newTitle,String newText){
        uiMessageBoxController.getText().setText(newText);
        uiMessageBoxController.getTitle().setText(newTitle);
        newStage.show();
    }

    public void setModality(Modality modality){
        newStage.initModality(modality);
    }

}
