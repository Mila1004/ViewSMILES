/* This software is licensed under the GNU General Public License.
   The experience created while using this software may vary by system
   and the author takes no responsibility for correct use of this software.

   @author Maximilian Rottmann
 */
package de.whs.ibci.mrottmann.view;

import de.whs.ibci.mrottmann.control.Control;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.awt.*;

public class View {

    //region Class Constructor

    /**
     * Class Constructor of View Class.
     *
     * @param aStage  Stage of Application launched in Control, null if null
     * @throws RuntimeException if initWindow {@link View#initWindow(Stage)} fails
     */
    public View(Stage aStage) {
        try {
            initWindow(aStage);
        } catch (Exception initViewException) {
            throw new RuntimeException(initViewException);
        }
    }
    //endregion

    //region Private Methods

    /**
     * Gets ResolutionWidth from control.
     *
     * @param aResolution  resolution of screen, null returns null
     * @return resolution width as double
     */
    private double getResolutionWidth(Dimension aResolution) {
        double tmpResolutionWidth = aResolution.width;
        return tmpResolutionWidth;
    }

    /**
     * Gets ResolutionHeight from control
     *
     * @param aResolution  resolution of screen, null returns  null
     * @return resolution height as double
     */
    private double getResolutionHeight(Dimension aResolution) {
        double tmpResolutionHeight = aResolution.height;
        return tmpResolutionHeight;
    }

    /**
     * Initializes JavaFX stage.
     *
     * @param aStage  stage given from control, null if null
     */
    private void initWindow(Stage aStage) {
        try {
            buildScene(aStage);
            aStage.show();
        } catch (Exception initWindowException) {
            throw new RuntimeException(initWindowException);
        }

    }

    /**
     * Builds JavaFX scene.
     *
     * @param aStage  stage given from control, null if null
     */
    private void buildScene(Stage aStage) {
        //region Variable Declaration and Initialization
        Control control = new Control();
        VBox tmpAllVBox = new VBox(20);
        ImageView tmpImageView = new ImageView();
        VBox tmpImageViewVBox = new VBox();
        TextField tmpUserInputTextField = new TextField();
        Text tmpErrorText = new Text();
        HBox tmpErrorHBox = new HBox(20);
        HBox tmpInputHBox = new HBox(20);
        //endregion

        //region ImageView Properties
        tmpImageView.setCache(true);
        tmpImageView.setPreserveRatio(true);
        //endregion

        //region Text Properties
        tmpErrorText.setText("Non SMILES Letter detected!");
        tmpErrorText.setFill(Color.RED);
        tmpErrorText.setFont(Font.font(24));
        //endregion

        //region tmpImageViewVBox Properties
        tmpImageViewVBox.setSpacing(20);
        tmpImageViewVBox.prefHeightProperty().bind(aStage.heightProperty().multiply(0.75));
        tmpImageViewVBox.prefWidthProperty().bind(aStage.widthProperty().multiply(0.65));
        tmpImageViewVBox.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        tmpImageViewVBox.getChildren().add(tmpImageView);
        //endregion

        //region Stage Properties
        aStage.setMaximized(false);
        //endregion

        //region tmpUserInputTextField Properties
        tmpUserInputTextField.setAlignment(Pos.TOP_LEFT);
        tmpUserInputTextField.setPadding(new Insets(5, 5, 5, 5));
        //region TextFieldListener
        tmpUserInputTextField.textProperty().addListener(observable -> {
            tmpErrorHBox.setMaxSize(tmpUserInputTextField.getMaxWidth(),tmpUserInputTextField.getMaxHeight());
            Dimension tmpStageSize = new Dimension();
            tmpStageSize.height = (int) tmpImageViewVBox.getHeight();
            tmpStageSize.width = (int) tmpImageViewVBox.getWidth();
            if(!control.getCanParse(tmpUserInputTextField.getText())) {
                tmpErrorHBox.setVisible(true);
            } else {
                tmpErrorHBox.setVisible(false);
                try {
                    Image tmpMoleculeImage = control.setParser(tmpUserInputTextField.getText(), tmpStageSize);
                    tmpImageView.setImage(tmpMoleculeImage);
                } catch (Exception viewMoleculeImageSetException) {
                    throw new RuntimeException(viewMoleculeImageSetException);
                }
            }
        });
        //endregion
        //endregion

        //region Button Properties
        Button tmpRedrawButton = new Button("Redraw!");
        tmpRedrawButton.setPadding(new Insets(5));
        tmpRedrawButton.setOnAction(event -> {
            Dimension tmpCurrentImageViewVBoxSize = new Dimension();
            tmpCurrentImageViewVBoxSize.height = (int) (tmpImageViewVBox.getHeight());
            tmpCurrentImageViewVBoxSize.width = (int) (tmpImageViewVBox.getWidth());
            Image tmpScaledMoleculeImage = control.setParser(tmpUserInputTextField.getText(), tmpCurrentImageViewVBoxSize);
            tmpImageView.setImage(tmpScaledMoleculeImage);
        });
        //endregion

        //region HBox Properties
        tmpInputHBox.getChildren().addAll(tmpUserInputTextField, tmpRedrawButton);
        tmpInputHBox.setAlignment(Pos.TOP_LEFT);
        tmpInputHBox.setSpacing(20);
        tmpErrorHBox.getChildren().add(tmpErrorText);
        tmpErrorHBox.setMaxSize(tmpUserInputTextField.getMaxWidth(),tmpUserInputTextField.getMaxHeight());
        tmpErrorHBox.setVisible(false);
        //endregion

        //region tmpAllVBox Properties
        tmpAllVBox.getChildren().addAll(tmpInputHBox, tmpImageViewVBox);
        tmpAllVBox.setPadding(new Insets(10, 5, 5, 10));
        tmpAllVBox.getChildren().add(tmpErrorHBox);
        //endregion

        //region Set Scene and Stage
        Scene scene = new Scene(tmpAllVBox, getResolutionWidth(control.getScreenResolution()) * 0.75,
                getResolutionHeight(control.getScreenResolution()) * 0.75);
        aStage.setScene(scene);
        //endregion
    }
    //endregion
    }
