package graphical.decompressorjava.resultsView;

import decompressor.Results;
import graphical.decompressorjava.Switcher;
import graphical.decompressorjava.resultsView.options.Options;

/**
 * Switches to results-view.fxml
 */
public class ResultsViewSwitcher extends Switcher {

    private final Results results;
    private final Options options;

    public ResultsViewSwitcher(Results results, Options options) {
        this.results = results;
        this.options = options;
    }

    /**
     * Sets up the results, stage and options for the results view
     */
    @Override
    public void setUp() {
        ((ResultsViewController) getController()).setResults(results);
        ((ResultsViewController) getController()).setStage(getStage());
        ((ResultsViewController) getController()).setAll(options);
    }

    @Override
    public String getUrl() {
        return "results-view.fxml";
    }
}
