package graphical.decompressorjava.resultsView.treeDrawer;

import decompressor.information.dictionary.Node;
import graphical.decompressorjava.resultsView.nodeView.NodeViewSwitcher;
import graphical.decompressorjava.resultsView.options.Options;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Draws the tree based on the options given
 */
public class TreeDrawer {

    private final Object root;
    private final AnchorPane canvas;
    private final Options options;
    /**
     * How far to the right the node is
     */
    private int offsetRight;
    /**
     * If option /enum is on, each node will be numbered
     */
    private int nodeNumber;

    public TreeDrawer(Object root, AnchorPane canvas, Options options) {
        this.canvas = canvas;
        this.root = root;
        this.options = options;
    }

    /**
     * Clears old tree, sets up the new one and draws it
     */
    public void drawTree() {
        if (!canvas.getChildren().isEmpty())
            canvas.getChildren().clear(); // clearing the old tree
        setUp(); // setting positions for all nodes
        draw((Node) root);
    }

    /**
     * Sets up position for all nodes by initializing {@link #setNodePos(Node, int) setNodePos}
     */
    private void setUp() {
        setNodePos((Node) root, 0);
    }

    /**
     * Sets position for current node and the ones below
     * @param node current node we're in
     * @param depth how low in the tree we are
     */
    private void setNodePos(Node node, int depth) {
        if (node.left() == null && node.right() == null) { // it's a leaf
            node.setPos(++offsetRight, depth);
            return;
        }
        if (node.left() != null)
            setNodePos((Node) node.left(), depth+1);
        if (node.right() != null)
            setNodePos((Node) node.right(), depth+1);
    }

    /**
     * Draws currentNode with branches and ones below with functions {@link #drawNode(Node) drawNode}, {@link #drawLeftBranch(Node) drawLeftBranch} and {@link #drawRightBranch(Node) drawRightBranch},
     * draws based on options, which include time to draw each segment (uses TimeLine)
     * @param currentNode currentNode
     */
    private void draw(Node currentNode) {
        if (options.waitTime() == 0) { // timeline with 0 simply doesn't draw, so we use this
            drawNode(currentNode);
            if (currentNode.left() != null) {
                drawLeftBranch(currentNode);
                draw((Node) currentNode.left());
            }
            if (currentNode.right() != null) {
                drawRightBranch(currentNode);
                draw((Node) currentNode.right());
            }
        } else {
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(options.waitTime()), event -> {
                drawNode(currentNode);
                Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(options.waitTime()), event1 -> {
                    if (currentNode.left() != null) {
                        drawLeftBranch(currentNode);
                        draw((Node) currentNode.left());
                    }
                    Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(options.waitTime()), event2 -> {
                        if (currentNode.right() != null) {
                            drawRightBranch(currentNode);
                            draw((Node) currentNode.right());
                        }
                    }));
                    timeline2.play();
                }));
                timeline1.play();
            }));
            timeline.play();
        }
    }

    /**
     * Draws current node as a button
     * @param node currentNode
     */
    private void drawNode(Node node) {
        Button button = new Button();
        button.setPrefWidth(options.buttonWidth());
        button.setLayoutX(xModifier(node.getPos().offsetRight()));
        button.setLayoutY(yModifier(node.getPos().depth()));
        if(options.nodeText().equals("/enum"))
            button.setText(String.valueOf(nodeNumber++));
        else if (!options.nodeText().isEmpty()) // is nodeText() is empty, simply don't write anything
            button.setText(options.nodeText());
        button.setOnAction(event -> {
            NodeViewSwitcher nodeViewSwitcher = new NodeViewSwitcher(node, options.symbolInBinary());
            nodeViewSwitcher.switchToMe(new Stage());
        });
        canvas.getChildren().add(button);
    }

    /**
     * Draws current's node left branch
     * @param node current node
     */
    private void drawLeftBranch(Node node) {
        Line verticaLine = new Line();
        verticaLine.setStartX(xModifier(node.getPos().offsetRight()));
        verticaLine.setEndX(xModifier(((Node) node.left()).getPos().offsetRight()) + options.buttonWidth()/2);
        verticaLine.setStartY(yModifier(node.getPos().depth())+12);
        verticaLine.setEndY(verticaLine.getStartY());

        Line horizontaLine = new Line();
        horizontaLine.setStartX(verticaLine.getEndX());
        horizontaLine.setEndX(horizontaLine.getStartX());
        horizontaLine.setStartY(verticaLine.getStartY());
        horizontaLine.setEndY(yModifier(((Node) node.left()).getPos().depth()));

        canvas.getChildren().add(verticaLine);
        canvas.getChildren().add(horizontaLine);
    }

    /**
     * Draws current's node right branch
     * @param node current node
     */
    private void drawRightBranch(Node node) {
        Line verticaLine = new Line();
        verticaLine.setStartX(xModifier(node.getPos().offsetRight())+ options.buttonWidth());
        verticaLine.setEndX(xModifier(((Node) node.right()).getPos().offsetRight()) + options.buttonWidth()/2);
        verticaLine.setStartY(yModifier(node.getPos().depth())+12);
        verticaLine.setEndY(verticaLine.getStartY());

        Line horizontaLine = new Line();
        horizontaLine.setStartX(verticaLine.getEndX());
        horizontaLine.setEndX(horizontaLine.getStartX());
        horizontaLine.setStartY(verticaLine.getStartY());
        horizontaLine.setEndY(yModifier(((Node) node.right()).getPos().depth()));

        canvas.getChildren().add(verticaLine);
        canvas.getChildren().add(horizontaLine);
    }

    /**
     * @param y the y position
     * @return the modified y position based on option parameters
     */
    private double yModifier(int y) {
        return y* options.yMultiplier()+ options.yAddition();
    }

    /**
     * @param x the x position
     * @return the modified y position based on option parameters
     */
    private double xModifier(double x) {
        return x* options.xMultiplier()+ options.xAddition();
    }
}
