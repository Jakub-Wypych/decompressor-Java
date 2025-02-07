package graphical.decompressorjava.resultsView.options;

import graphical.decompressorjava.Switcher;
import graphical.decompressorjava.resultsView.ResultsViewController;

/**
 * Switches to options view
 */
public class OptionsViewSwitcher extends Switcher {
    private final Options options;
    private final ResultsViewController controller;

    public OptionsViewSwitcher(Options options, ResultsViewController controller) {
        this.options = options;
        this.controller = controller;
    }

    /**
     * Mainly sets all options to current options values,
     * i.e. if buttonWidth is 15, set buttonWidth text field to write 15
     */
    @Override
    public void setUp() {
        getStage().setTitle("Options");
        getStage().setResizable(false);
        OptionsViewController optionsViewController = (OptionsViewController) getController();
        optionsViewController.xAddition.setText(String.valueOf(options.xAddition()));
        optionsViewController.xMultiplier.setText(String.valueOf(options.xMultiplier()));
        optionsViewController.yAddition.setText(String.valueOf(options.yAddition()));
        optionsViewController.yMultiplier.setText(String.valueOf(options.yMultiplier()));
        optionsViewController.buttonWidth.setText(String.valueOf(options.buttonWidth()));
        optionsViewController.waitTime.setText(String.valueOf(options.waitTime()));
        optionsViewController.nodeText.setText(options.nodeText());
        optionsViewController.symbolBinary.setSelected(options.symbolInBinary());
        optionsViewController.resizeStage.setSelected(options.stageResizable());
        optionsViewController.setController(controller);
    }

    @Override
    public String getUrl() {
        return "options-view.fxml";
    }
}
