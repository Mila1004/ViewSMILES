/* This software is licensed under the GNU General Public License.
   The experience created while using this software may vary by system
   and the author takes no responsibility for correct use of this software.

   @author Maximilian Rottmann
 */
package de.whs.ibci.mrottmann.control;


import de.whs.ibci.mrottmann.model.Model;
import de.whs.ibci.mrottmann.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

import java.awt.*;

public class Control extends Application{
    //class variables
    double resolutionHeight;
    double resolutionWidth;
    String inputSmiles;
    //user Smiles input
    Dimension resolution;
    //screen resolution in java.awt Dimension
    boolean isParse;
    //bool if Smiles code parsable by CDK
    Model parseSmilesModel = new Model();
    //View smilesView;

    //class control constructor
    public Control() {

    }

    public boolean getCanParse(String aInputSmiles) {
        if(this.parseSmilesModel.getModelCanParse(aInputSmiles) == true) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void start(Stage aPrimaryStage) {
        //overwritten start method from javafx
        try {
            Stage primaryStage = new Stage();
            //primaryStage.setTitle("ViewSMILES");
            View smilesView = new View(primaryStage, screenResolution().height, screenResolution().width);
        } catch (Exception initViewException) {

        }
        try {
            //parseSmilesModel = new Model();
            //System.out.println("new model");
        } catch (Exception initParseSmilesModelException) {

        }
    }

    //region Private Methods

    private Dimension screenResolution() {
        try {
            resolution = Toolkit.getDefaultToolkit().getScreenSize();
        } catch (Exception screenResolutionException) {

        } return resolution;
    }

    private String getInputSmiles() {
        inputSmiles = "C";
        return inputSmiles;
    }

    public void startViewSmiles() {
        //parseSmilesModel = new Model();
        Application.launch();

    }

    public void setParser(String aSmilesInputString, Dimension aStageSize) {
        this.parseSmilesModel.setModelInputSmilesString(aSmilesInputString, aStageSize);
        System.out.println("setControlInputSmilesString");
    }

    public void setErrorText() {

    }
}
