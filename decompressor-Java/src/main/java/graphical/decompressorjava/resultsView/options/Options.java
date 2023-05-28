package graphical.decompressorjava.resultsView.options;

/**
 * Stores the options
 * @param xAddition by how much to shift the tree on x-axis
 * @param xMultiplier how much space between nodes on x-axis
 * @param yAddition by how much to shift the tree on y-axis
 * @param yMultiplier how much space between nodes on y-axis
 * @param buttonWidth the x-axis node size
 * @param waitTime how long to wait between drawing nodes/branches
 * @param nodeText what to write on nodes ('/enum' will enumerate nodes from 0, 1, 2, ...)
 * @param symbolInBinary should symbols be written in binary or char form
 * @param stageResizable should the stage be resizable
 */
public record Options(
        double xAddition, double xMultiplier, double yAddition, double yMultiplier, double buttonWidth, double waitTime,
        String nodeText, boolean symbolInBinary, boolean stageResizable
) {}
