/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item.tools;

import java.io.Serializable;
import model.item.ItemType.Tools.Material;
import model.item.tools.AbstractTool.ToolStatus;

/**
 * The interface ToolInterface represents a tool in the game and provides methods related to a tool,
 * such as using the tool, getting the tool status, and getting the material of the tool.
 */
public interface ToolInterface extends Serializable {
    /**
     * Uses the tool and returns the updated tool status.
     *
     * @return the updated tool status
     */
    public ToolStatus useTool();

    /**
     * Returns the status of the tool.
     *
     * @return the status of the tool
     */
    public ToolStatus getToolStatus();

    /**
     * Returns the material of the tool.
     *
     * @return the material of the tool
     */
    public Material getMaterial();
}
