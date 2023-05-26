package graphical.decompressorjava.resultsView.treeDrawer;

import decompressor.dictionary.Node;
import graphical.decompressorjava.resultsView.nodeView.NodeViewSwitcher;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class TreeDrawer {

    private final Object root;
    private int offsetRight;
    private final AnchorPane canvas;
    private TreePosModifier modifier;
    private int nodeNumber;

    public TreeDrawer(Object root, AnchorPane canvas, TreePosModifier modifier) {
        this.canvas = canvas;
        this.root = root;
        this.modifier = modifier;
        offsetRight = 0;
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

    public void drawTree() {
        setUp();
        draw((Node) root);
    }

    private void draw(Node currentNode) {
        drawNode(currentNode);
        if(currentNode.left() != null) {
            drawLeftBranch(currentNode);
            draw((Node) currentNode.left());
        }
        if(currentNode.right() != null) {
            drawRightBranch(currentNode);
            draw((Node) currentNode.right());
        }
    }

    private void drawNode(Node node) {
        Button button = new Button();
        button.setPrefWidth(modifier.buttonWidth());
        button.setLayoutX(xModifier(node.getPos().offsetRight()));
        button.setLayoutY(yModifier(node.getPos().depth()));
        button.setText(String.valueOf(nodeNumber++));
        button.setOnAction(event -> {
            NodeViewSwitcher nodeViewSwitcher = new NodeViewSwitcher(node);
            nodeViewSwitcher.switchToMe(new Stage());
        });
        canvas.getChildren().add(button);
    }

    private void drawLeftBranch(Node node) {
        Line verticaLine = new Line();
        verticaLine.setStartX(xModifier(node.getPos().offsetRight()));
        verticaLine.setEndX(xModifier(((Node) node.left()).getPos().offsetRight()) + modifier.buttonWidth()/2);
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
        verticaLine.setStartX(xModifier(node.getPos().offsetRight())+modifier.buttonWidth());
        verticaLine.setEndX(xModifier(((Node) node.right()).getPos().offsetRight()) + modifier.buttonWidth()/2);
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
        return y*modifier.yMultiplier()+modifier.yAddition();
    }

    private double xModifier(double x) {
        return x*modifier.xMultiplier()+modifier.xAddition();
    }

    public void setModifier(TreePosModifier modifier) {
        this.modifier = modifier;
    }
}
