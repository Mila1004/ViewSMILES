/* This software is licensed under the GNU General Public License.
   The experience created while using this software may vary by system
   and the author takes no responsibility for correct use of this software.

   @author Maximilian Rottmann
 */
package de.whs.ibci.mrottmann.model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.openscience.cdk.depict.Depiction;
import org.openscience.cdk.depict.DepictionGenerator;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.smiles.SmilesParser;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Model {

    //region Model Constructor
    public Model() {

    }
    //endregion

    //region Public Methods
    public Image setModelInputSmilesString(String aSmilesInputString, Dimension aNeededImageSize) {
        if (canParse(aSmilesInputString)) {
            Image tmpMoleculeImage = parseSmiles(aSmilesInputString, aNeededImageSize);
            return tmpMoleculeImage;
        } else {
            return null;
        }
    }

    public boolean getModelCanParse(String anInputSmilesString) {
        return canParse(anInputSmilesString);
    }
    //endregion

    //region Private Methods
    /** Parses javafx Image for given SMILES String.
     * converses the generated BufferedImage from java.awt to desired javafx Image,
     * this Image is then returned. */
    private Image parseSmiles(String aSmilesString, Dimension aResolution) {
        Image tmpMoleculeImage;
        try {
            SmilesParser smilesParser = new SmilesParser(SilentChemObjectBuilder.getInstance());
            IAtomContainer tmpAtomContainer = smilesParser.parseSmiles(aSmilesString);
            DepictionGenerator depictionGenerator = new DepictionGenerator().withZoom(5).
                    withSize(aResolution.getWidth(), aResolution.getHeight());
            Depiction tmpDepiction = depictionGenerator.depict(tmpAtomContainer);
            tmpMoleculeImage = SwingFXUtils.toFXImage(tmpDepiction.toImg(),null);
        } catch (Exception parseSmilesException) {
            throw new RuntimeException(parseSmilesException);
        }
        return tmpMoleculeImage;
    }

    private boolean canParse(String anInputSmiles) {
        boolean tmpIsStringContainsNonSmilesCharacter;
        try {
            Pattern pattern = Pattern.compile("[^a-ik-pr-zA-IK-PR-Z0-9*#.={}()+-]");
            Matcher matcher = pattern.matcher(anInputSmiles);
            tmpIsStringContainsNonSmilesCharacter = matcher.find();
            tmpIsStringContainsNonSmilesCharacter = !tmpIsStringContainsNonSmilesCharacter;
        } catch (Exception canParseException) {
            throw new RuntimeException(canParseException);
        }
        return tmpIsStringContainsNonSmilesCharacter;
    }
    //endregion
}
