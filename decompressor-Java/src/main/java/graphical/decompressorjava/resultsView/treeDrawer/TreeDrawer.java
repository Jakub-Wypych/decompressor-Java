package graphical.decompressorjava.resultsView.treeDrawer;

import decompressor.dictionary.Node;
import graphical.decompressorjava.resultsView.nodeView.NodeViewSwitcher;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TreeDrawer {

    private final Object root;
    private int offsetRight;
    private final AnchorPane canvas;
    private final Options options;
    private int nodeNumber;

    public TreeDrawer(Object root, AnchorPane canvas, Options options) {
        this.canvas = canvas;
        this.root = root;
        this.options = options;
    }

    public void drawTree() {
        if (!canvas.getChildren().isEmpty())
            canvas.getChildren().clear();
        setUp();
        draw((Node) root);
    }

    private void setUp() {
        setNodePos((Node) root, 0);
    }

    private void setNodePos(Node node, int depth) {
        if (node.left() == null && node.right() == null) {
            node.setPos(++offsetRight, depth);
            return;
        }
        if (node.left() != null)
            setNodePos((Node) node.left(), depth+1);
        if (node.right() != null)
            setNodePos((Node) node.right(), depth+1);
    }

    private void draw(Node currentNode) {
        if (options.waitTime() == 0) {
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

    private void drawNode(Node node) {
        Button button = new Button();
        button.setPrefWidth(options.buttonWidth());
        button.setLayoutX(xModifier(node.getPos().offsetRight()));
        button.setLayoutY(yModifier(node.getPos().depth()));
        if(options.nodeText().equals("/enum"))
            button.setText(String.valueOf(nodeNumber++));
        else if (!options.nodeText().isEmpty())
            button.setText(options.nodeText());
        button.setOnAction(event -> {
            NodeViewSwitcher nodeViewSwitcher = new NodeViewSwitcher(node, options.symbolInBinary());
            nodeViewSwitcher.switchToMe(new Stage());
        });
        canvas.getChildren().add(button);
    }

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

    private double yModifier(int y) {
        return y* options.yMultiplier()+ options.yAddition();
    }

    private double xModifier(double x) {
        return x* options.xMultiplier()+ options.xAddition();
    }
}
