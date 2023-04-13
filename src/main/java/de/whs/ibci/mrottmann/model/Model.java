/* This software is licensed under the GNU General Public License.
   The experience created while using this software may vary by system
   and the author takes no responsibility for correct use of this software.

   @author Maximilian Rottmann
 */
package de.whs.ibci.mrottmann.model;

/** imports */
//region imports

import de.whs.ibci.mrottmann.control.Control;
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


//endregion

public class Model {

    Control modelControl;

    //region Model Constructor
    public Model() {

    }
    //endregion

    //region Public Methods
    /** Parses javafx Image for given SMILES String molecule by converting
     * the generated BufferedImage from java.awt to desired javafx Image,
     * this Image is then returned. */
    private Image parseSmiles(String aSmilesString, Dimension aResolution) {
        Image tmpMoleculeImage;
        try {
            SmilesParser smilesParser = new SmilesParser(SilentChemObjectBuilder.getInstance());


            IAtomContainer tmpAtomContainer = smilesParser.parseSmiles(aSmilesString);
            DepictionGenerator depictionGenerator = new DepictionGenerator().withZoom(5).withSize(aResolution.getWidth(), aResolution.getHeight());
            Depiction tmpDepiction = depictionGenerator.depict(tmpAtomContainer);

            tmpMoleculeImage = SwingFXUtils.toFXImage(tmpDepiction.toImg(),null);
        } catch (Exception parseSmilesException) {
            throw new RuntimeException("parseSmiles failed");
        }
        System.out.println("molecule image");
        return tmpMoleculeImage;
    }
    //endregion

    //region Private Methods
    private boolean canParse(String inputSmiles) {
        //checks Smiles input for correct notation
        boolean tmpIsStringContainsNonSmilesCharacter = false;
        try {
            Pattern pattern = Pattern.compile("[^a-ik-pr-zA-IK-PR-Z0-9*#.={}()+-]");
            //compiles a pattern with chars in brackets
            Matcher matcher = pattern.matcher(inputSmiles);
            //init matcher with Smiles input
            tmpIsStringContainsNonSmilesCharacter = matcher.find();
            if(tmpIsStringContainsNonSmilesCharacter == false) {
                tmpIsStringContainsNonSmilesCharacter = true;
            } else {
                tmpIsStringContainsNonSmilesCharacter = false;
            }
            System.out.println(tmpIsStringContainsNonSmilesCharacter);

            //bool check for pattern
            //bool isParse gets negated value of isStringContainsNonSmilesCharacter

        } catch (Exception canParseException) {

        }
        return tmpIsStringContainsNonSmilesCharacter;
    }

    public Image setModelInputSmilesString(String aSmilesInputString, Dimension aNeededImageSize) {
        System.out.println("model reached");
        if (canParse(aSmilesInputString) == true) {
            System.out.println("canParse true");
            Image tmpMoleculeImage = parseSmiles(aSmilesInputString, aNeededImageSize);
            System.out.println("smiles parsed to image");
            return tmpMoleculeImage;
        } else {
            System.out.println("canParse false");
            Control modelControl = new Control();
            modelControl.setErrorText();
            return null;
        }
    }

    public boolean getModelCanParse(String aInputSmilesString) {
        if (canParse(aInputSmilesString) == true) {
            return true;
        } else {
            return false;
        }

    }

    //endregion
}
