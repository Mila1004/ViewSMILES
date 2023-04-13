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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;

public class View {
    //class variables
    double resolutionHeight;
    double resolutionWidth;

    public View(Stage aStage, Dimension aResolution) {
        this.resolutionHeight = aResolution.height;
        //sets given resHeight as this
        this.resolutionWidth = aResolution.width;
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
        aStage.setMaximized(false);
        aStage.show();
    }

    private void buildScene(Stage aStage) {
        Control control = new Control();

        ImageView tmpImageView = new ImageView();
        tmpImageView.setCache(true);
        tmpImageView.setPreserveRatio(true);
        VBox tmpImageViewVBox = new VBox();
        tmpImageViewVBox.setSpacing(20);
        tmpImageViewVBox.prefHeightProperty().bind(aStage.heightProperty().multiply(0.75));
        tmpImageViewVBox.prefWidthProperty().bind(aStage.widthProperty().multiply(0.65));
        tmpImageViewVBox.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        tmpImageViewVBox.getChildren().add(tmpImageView);


        TextField tmpUserInputTextField = new TextField();
        tmpUserInputTextField.setAlignment(Pos.TOP_LEFT);
        tmpUserInputTextField.setPadding(new Insets(5, 5, 5, 5));
        tmpUserInputTextField.textProperty().addListener(observable -> {

            Dimension tmpStageSize = new Dimension();
            tmpStageSize.height = (int) (aStage.getHeight() * 0.95);
            tmpStageSize.width = (int) (aStage.getWidth() * 0.95);
            System.out.println(tmpStageSize);
            tmpImageViewVBox.setMaxSize(tmpStageSize.width, tmpStageSize.height);
            Image tmpMoleculeImage = control.setParser(tmpUserInputTextField.getText(), tmpStageSize);
            tmpImageView.setImage(tmpMoleculeImage);
        });
        //region Button
        Button tmpRedrawButton = new Button("Redraw!");
        tmpRedrawButton.setPadding(new Insets(5));
        tmpRedrawButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Dimension tmpCurrentImageViewSize = new Dimension();
                tmpCurrentImageViewSize.width = tmpImageView.scaleXProperty().intValue();
                tmpCurrentImageViewSize.height = tmpImageView.scaleYProperty().intValue();
                Image tmpScaledMoleculeImage = control.setParser(tmpUserInputTextField.getText(), tmpCurrentImageViewSize);
                tmpImageView.setImage(tmpScaledMoleculeImage);
            }
        });
        //endregion
        HBox tmpInputHBox = new HBox(20, tmpUserInputTextField, tmpRedrawButton);
        tmpInputHBox.setAlignment(Pos.TOP_LEFT);
        tmpInputHBox.setSpacing(20);

        Text tmpErrorText = new Text("Non SMILES Letter detected!");
        HBox tmpErrorHBox = new HBox(20, tmpErrorText);
        tmpErrorHBox.setMaxSize(tmpUserInputTextField.getMaxWidth(),tmpUserInputTextField.getMaxHeight());


        VBox allVBox = new VBox(20, tmpInputHBox, tmpImageViewVBox);
        allVBox.setPadding(new Insets(10, 5, 5, 10));
        if(control.getCanParse(tmpUserInputTextField.getText()) == false) {
            allVBox.getChildren().add(tmpErrorHBox);
        } else {allVBox.getChildren().remove(tmpErrorHBox);}

        Scene scene = new Scene(allVBox, resolutionWidth * 0.75, resolutionHeight * 0.75);
        aStage.setScene(scene);
    }

    private void redrawButtonPressed() {

    }
    //endregion
    }
