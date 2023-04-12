/* This software is licensed under the GNU General Public License.
   The experience created while using this software may vary by system
   and the author takes no responsibility for correct use of this software.

   @author Maximilian Rottmann
 */
package de.whs.ibci.mrottmann.main;

import de.whs.ibci.mrottmann.control.Control;

public class Main {
    public static void main(String[] args) {
        try {
            Control control = new Control();
            control.startViewSmiles();
        } catch (Exception initException) {

        }
    }
}
