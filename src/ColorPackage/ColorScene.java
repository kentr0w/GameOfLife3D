package ColorPackage;


import Constatnce.Constance;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Optional;


public class ColorScene {

    private FlowPane root;
    private String string;
    private Scene scene;
    private Color color;
    public ColorScene(){
        showChoiceDialog();
    }

    private void showChoiceDialog(){
        ChoiceDialog<String> choiceDialog = new ChoiceDialog<>("Фон за кубиком", "Фон панели", "Фон кубиков");
        choiceDialog.setTitle("Choser");
        choiceDialog.setHeaderText(null);
        Optional<String> answer = choiceDialog.showAndWait();
        answer.ifPresent(str->{
            string = str;
            Initialize();
            Show();
        });
    }

    private void Initialize(){
        Button button = new Button("Select");

        final ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.RED);
        final Circle circle = new Circle(50);
        circle.setFill(colorPicker.getValue());
        colorPicker.setOnAction(event -> circle.setFill(colorPicker.getValue()));

        button.setOnMousePressed(event1 -> {
            color = colorPicker.getValue();
            switch (string) {
                case "Фон за кубиком":
                    Constance.setPhongMaterialMain(color);
                    break;
                case "Фон панели":
                    Constance.setPhongMaterialMinor(color);
                    break;
                case "Фон кубиков":
                    Constance.setPhongMaterial(color);
                    break;
            }
            this.scene.getWindow().hide();
        });

        root = new FlowPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.getChildren().addAll(circle, colorPicker, button);
    }

    private void Show(){
        scene = new Scene(root, 450, 250);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.requestFocus();
        stage.focusedProperty();
        stage.setTitle("Color");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public String getName(){
        return string;
    }

    public static String toRGBCode( Color color )
    {
        return String.format( "#%02X%02X%02X",
                (int)( color.getRed() * 255 ),
                (int)( color.getGreen() * 255 ),
                (int)( color.getBlue() * 255 ) );
    }
}
