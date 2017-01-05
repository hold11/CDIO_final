package GUI.fields;

import java.awt.Color;
import javax.swing.JLabel;
import GUI.board.Center;
import GUI.backend.SwingComponentFactory;

public final class Chance extends Field {
    private static final int TITLEHEIGHT = 47;
    private static final int SUBTEXTHEIGHT = 14;
    private SwingComponentFactory factory = new SwingComponentFactory();
    
    public static class Builder extends Field.Builder<Builder> implements
        iBuilder {
        public Builder() {
            this.title = "<b><font size=\"7\">?";
            this.subText = "Prøv lykken";
            this.description = "Ta' et chancekort.";
            this.bgColor = new Color(204, 204, 204);
        }
        
        @Override
        @SuppressWarnings("synthetic-access")
        public Chance build() {
            return new Chance(this.title, this.subText, this.description,
                this.bgColor, this.fgColor);
        }
    }
    
    private Chance(String title, String subText, String description,
        Color bgColor, Color fgColor) {
        super(bgColor, fgColor, title, subText, description);
        this.titleLabel = makeRoadNameLabel();
        this.subTextLabel = makeBottomLabel();
        this.layered.add(this.titleLabel,
            this.factory.createGridBagConstraints(0, 0));
        this.layered.add(this.subTextLabel,
            this.factory.createGridBagConstraints(0, 1));
    }
    private JLabel makeRoadNameLabel() {
        JLabel roadnameLabel = makeLabel(TITLEHEIGHT);
        roadnameLabel.setText(this.title);
        return roadnameLabel;
    }
    private JLabel makeBottomLabel() {
        JLabel bottomLabel = makeLabel(SUBTEXTHEIGHT);
        bottomLabel.setText(this.subText);
        return bottomLabel;
    }
    @Override
    public void displayOnCenter() {
        super.displayOnCenter();
        Center.label[1].setIcon(this.factory
            .createIcon("/resources/pics/Prøv lykken small.png"));
        Center.label[2].setText("__________________________");
        Center.label[3].setText(this.description);
        super.displayCarOnCenter();
    }
}
