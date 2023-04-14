/* This software is licensed under the GNU General Public License.
   The experience created while using this software may vary by system
   and the author takes no responsibility for correct use of this software.

   @author Maximilian Rottmann
 */
package de.whs.ibci.mrottmann.main;

import de.whs.ibci.mrottmann.control.Control;

public class Main {

    //region Public Methods

    /**
     * Main method to start ViewSMILES.
     *
     * @param args  needed for correct functionality of main method, not null
     * @throws Error if control.startViewSmiles fails
     */
    public static void main(String[] args) throws Error {
        try {
            Control control = new Control();
            control.startViewSmiles();
        } catch (Exception initException) {
            throw new Error(initException);
        }
    }
    //endregion
}
