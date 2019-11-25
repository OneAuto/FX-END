package mvcpro.view.main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

//使用单列模式
public class UiMainFrame extends Application {

    private double lastx_distance;
    private double lasty_distance;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage mainStage=primaryStage;
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/ui_main_layout.fxml"));
        Pane root=loader.load();
        UiMainLayoutController uiMainLayoutController=loader.getController();
        uiMainLayoutController.setMainStage(mainStage);


        //
        //设置背景颜色
        //
        Background bg=new Background(new BackgroundFill(Color.valueOf("282828BF"),new CornerRadii(7),new Insets(0)));
        root.setBackground(bg);
        Scene scene=new Scene(root,783,387);

        //
        //加载CSS文件
        //
        scene.getStylesheets().add(getClass().getResource("/ui_main_style.css").toExternalForm());

        scene.setFill(Paint.valueOf("#FFFFFF00"));
        mainStage.setScene(scene);
        mainStage.initStyle(StageStyle.TRANSPARENT);
        mainStage.show();

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                lastx_distance=event.getScreenX()-mainStage.getX();
                lasty_distance=event.getScreenY()-mainStage.getY();
            }
        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mainStage.setX(event.getScreenX()-lastx_distance);
                mainStage.setY(event.getScreenY()-lasty_distance);
            }
        });
    }

}
