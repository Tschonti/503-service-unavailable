package observables;

import main.GeneticCode;

import javax.swing.*;

public class ObservableGeneticCode implements IObservable {

    private GeneticCode code;

    public ObservableGeneticCode(GeneticCode gc) {
        code = gc;
    }

    @Override
    public JComponent onPaint() {
        return new JLabel(code.toString());
    }
}
