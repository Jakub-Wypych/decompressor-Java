package graphical.decompressorjava.resultsView.options;

import graphical.decompressorjava.Switcher;
import graphical.decompressorjava.resultsView.ResultsViewController;
import graphical.decompressorjava.resultsView.treeDrawer.TreePosModifier;

public class OptionsViewSwitcher extends Switcher {
    private final TreePosModifier modifier;
    private final ResultsViewController controller;

    public OptionsViewSwitcher(TreePosModifier modifier, ResultsViewController controller) {
        this.modifier = modifier;
        this.controller = controller;
    }

    @Override
    public void setUp() {
        getStage().setTitle("Options");
        getStage().setResizable(false);
        OptionsViewController optionsViewController = (OptionsViewController) getController();
        optionsViewController.xAddition.setText(String.valueOf(modifier.xAddition()));
        optionsViewController.xMultiplier.setText(String.valueOf(modifier.xMultiplier()));
        optionsViewController.yAddition.setText(String.valueOf(modifier.yAddition()));
        optionsViewController.yMultiplier.setText(String.valueOf(modifier.yMultiplier()));
        optionsViewController.buttonWidth.setText(String.valueOf(modifier.buttonWidth()));
        optionsViewController.waitTime.setText(String.valueOf(0)); // TODO
        optionsViewController.symbolBinary.setSelected(true); // TODO
        optionsViewController.setController(controller);
    }

    @Override
    public String getUrl() {
        return "options-view.fxml";
    }
}
