/* This software is licensed under the GNU General Public License.
   The experience created while using this software may vary by system
   and the author takes no responsibility for correct use of this software.

   @author Maximilian Rottmann
 */
package de.whs.ibci.mrottmann.control;

import de.whs.ibci.mrottmann.model.Model;
import de.whs.ibci.mrottmann.view.View;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;

public class Control extends Application{

    //region Class Constructor
    public Control() {

    }
    //endregion

    //region Override Public Methods
    @Override
    public void start(Stage aPrimaryStage) {
        try {
            Stage primaryStage = new Stage();
            View smilesView = new View(primaryStage);
        } catch (Exception initViewException) {
            throw new RuntimeException(initViewException);
        }
    }
    //endregion

    //region Public Methods
    public void startViewSmiles() {
        try {
            Application.launch();
        } catch (Exception startViewSmilesException) {
            throw new RuntimeException(startViewSmilesException);
        }
    }

    public boolean getCanParse(String anInputSmiles) {
        return initModel().getModelCanParse(anInputSmiles);
    }

    public Image setParser(String aSmilesInputString, Dimension aStageSize) {
        try {
            return initModel().setModelInputSmilesString(aSmilesInputString, aStageSize);
        } catch (Exception setParserException) {
            return null;
        }
    }

    public Dimension getScreenResolution() {
        return screenResolution();
    }
    //endregion

    //region Private Methods
    private Dimension screenResolution() {
        try {
            Dimension tmpResolution = Toolkit.getDefaultToolkit().getScreenSize();
            return tmpResolution;
        } catch (Exception screenResolutionException) {
            return null;
        }
    }

    private Model initModel() {
        try {
            Model tmpParseSmilesModel = new Model();
            return tmpParseSmilesModel;
        } catch (Exception initModelException) {
            throw new RuntimeException(initModelException);
        }
    }
    //endregion
}
