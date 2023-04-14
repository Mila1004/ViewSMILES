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

    /**
     * Class Constructor of Control Class.
     *
     * no variables given when constructed
     */
    public Control() {
    }
    //endregion

    //region Override Public Methods

    /**
     * Starts JavaFX Application with Overridden start method.
     *
     * @param aPrimaryStage  the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws RuntimeException if new View {@link View} cannot be initialized
     */
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

    /**
     * Launches JavaFX Application with JavaFX method: Application.launch.
     *
     * @throws RuntimeException if Application cannot be launched
     */
    public void startViewSmiles() {
        try {
            Application.launch();
        } catch (Exception startViewSmilesException) {
            throw new RuntimeException(startViewSmilesException);
        }
    }

    /**
     * Gets Bool from Model.canParse {@link Model} for given String.
     * @param anInputSmiles  SMILES String given when method called, null returns Exception
     * @return boolean whether CDK can parse given String or not
     */
    public boolean getCanParse(String anInputSmiles) {
        return initModel().getModelCanParse(anInputSmiles);
    }

    /**
     * Sets Model setModelInput{@link Model#setModelInputSmilesString(String, Dimension)} as given
     * @param aSmilesInputString  SMILES String, null returns null
     * @param aStageSize  Resolution of current JavaFX Stage , null returns null
     * @return JavaFX Image from Model, null if not processed
     */
    public Image setParser(String aSmilesInputString, Dimension aStageSize) {
        try {
            return initModel().setModelInputSmilesString(aSmilesInputString, aStageSize);
        } catch (Exception setParserException) {
            return null;
        }
    }

    /**
     * Gets screen resolution for further use.
     *
     * @return Screen resolution, not  null
     */
    public Dimension getScreenResolution() {
        return screenResolution();
    }
    //endregion

    //region Private Methods

    /**
     * Calls Toolkit to return current screen size/resolution.
     *
     * @return Resolution, null if not processed
     */
    private Dimension screenResolution() {
        try {
            Dimension tmpResolution = Toolkit.getDefaultToolkit().getScreenSize();
            return tmpResolution;
        } catch (Exception screenResolutionException) {
            return null;
        }
    }

    /**
     * Initializes Model as tmpParseSmilesModel.
     *
     * @return tmpParseSmilesModel, null if not processed
     * @throws RuntimeException if Model initialization fails
     */
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
