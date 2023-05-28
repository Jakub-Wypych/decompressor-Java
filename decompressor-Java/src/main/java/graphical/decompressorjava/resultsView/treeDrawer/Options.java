package graphical.decompressorjava.resultsView.treeDrawer;

public record Options(double xAddition, // by how much to shift the tree on x-axis
                      double xMultiplier, // how much space between nodes on x-axis
                      double yAddition, // by how much to shift the tree on y-axis
                      double yMultiplier, // how much space between nodes on y-axis
                      double buttonWidth, // the x-axis node size
                      double waitTime, // how long to wait between drawing nodes/branches
                      String nodeText, // what to write on nodes ('/enum' will enumerate nodes from 0, 1, 2, ...)
                      boolean symbolInBinary) // should symbols be written in binary or char form
{}
