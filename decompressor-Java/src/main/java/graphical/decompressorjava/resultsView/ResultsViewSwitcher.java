package graphical.decompressorjava.resultsView;

import decompressor.Results;
import graphical.decompressorjava.Switcher;

public class ResultsViewSwitcher extends Switcher {

    private final Results results;

    public ResultsViewSwitcher(Results results) {
        this.results = results;
    }

    @Override
    public void setUp() {
        ((ResultsViewController) getController()).setAll(results);
    }

    @Override
    public String getUrl() {
        return "results-view.fxml";
    }
}
