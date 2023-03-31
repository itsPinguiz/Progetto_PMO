/********************
 * IMPORT AND PACKAGE
 *******************/

package Item.Tools.Interface;

import java.io.Serializable;

import Item.Tools.Interface.AbstractTool.ToolStatus;

public interface ToolInterface extends Serializable {
    public ToolStatus useTool();
}
