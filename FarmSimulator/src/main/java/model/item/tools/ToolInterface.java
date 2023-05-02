/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item.tools;

import java.io.Serializable;

import model.item.ItemType.Tools.Material;
import model.item.tools.AbstractTool.ToolStatus;

public interface ToolInterface extends Serializable {
    public ToolStatus useTool();
    public ToolStatus getToolStatus();
    public Material getMaterial();
}
