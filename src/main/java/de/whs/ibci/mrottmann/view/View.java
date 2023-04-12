/* This software is licensed under the GNU General Public License.
   The experience created while using this software may vary by system
   and the author takes no responsibility for correct use of this software.

   @author Maximilian Rottmann
 */
package de.whs.ibci.mrottmann.view;

import de.whs.ibci.mrottmann.control.Control;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;

public class View {
    //class variables
    double resolutionHeight;
    double resolutionWidth;

    public View(Stage aStage, double aResolutionHeight, double aResolutionWidth) {
        this.resolutionHeight = aResolutionHeight;
        //sets given resHeight as this
        this.resolutionWidth = aResolutionWidth;
        //sets given resWidth as this
        try {
            initWindow(aStage);
            //calls Window initialisation with respective stage given by constructor
        } catch (Exception initViewException) {

        }


    }

    //region Public Methods

    //endregion

    //region Private Methods
    private void initWindow(Stage aStage) {
        buildScene(aStage);
        aStage.setMaximized(true);
        aStage.show();
    }

    private void buildScene(Stage aStage) {
        Control control = new Control();

        ImageView tmpImageView = new ImageView();
        tmpImageView.setCache(true);
        VBox tmpImageViewVBox = new VBox();
        tmpImageViewVBox.setSpacing(20);
        tmpImageViewVBox.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        tmpImageViewVBox.getChildren().add(tmpImageView);

        TextField tmpUserInputTextField = new TextField();
        tmpUserInputTextField.setAlignment(Pos.TOP_LEFT);
        tmpUserInputTextField.setPadding(new Insets(5, 5, 5, 5));
        tmpUserInputTextField.textProperty().addListener(observable -> {
            //Control control = new Control();
            Dimension tmpStageSize = new Dimension();
            tmpStageSize.height = (int) aStage.getHeight();
            tmpStageSize.width = (int) aStage.getWidth();
            control.setParser(tmpUserInputTextField.getText(), tmpStageSize);
        });

        Button tmpRedrawButton = new Button("Redraw!");
        tmpRedrawButton.setPadding(new Insets(5));
        tmpRedrawButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        HBox tmpInputHBox = new HBox(20, tmpUserInputTextField, tmpRedrawButton);
        tmpInputHBox.setAlignment(Pos.TOP_LEFT);
        tmpInputHBox.setSpacing(20);

        TextArea tmpErrorText = new TextArea("Non SMILES Letter detected!");
        tmpErrorText.setMaxSize(tmpUserInputTextField.getMaxWidth(),tmpUserInputTextField.getMaxHeight());
        HBox tmpErrorHBox = new HBox(20, tmpErrorText);
        tmpErrorHBox.setMaxSize(tmpUserInputTextField.getMaxWidth(),tmpUserInputTextField.getMaxHeight());


        VBox allVBox = new VBox(20, tmpInputHBox, tmpImageViewVBox);
        allVBox.setPadding(new Insets(10, 5, 5, 10));
        if(control.getCanParse(tmpUserInputTextField.getText()) == false) {
            allVBox.getChildren().add(tmpErrorHBox);
        } else {allVBox.getChildren().remove(tmpErrorHBox);}

        Scene scene = new Scene(allVBox, resolutionWidth, resolutionHeight);
        aStage.setScene(scene);
    }

    private void redrawButtonPressed() {

    }
    //endregion
    }
