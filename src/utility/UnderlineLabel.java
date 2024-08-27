package utility;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class UnderlineLabel extends JLabel {

    public UnderlineLabel(String text) {
        super(text);
        setUnderlineFont();
    }

    private void setUnderlineFont() {
        Font originalFont = getFont();
        if (originalFont == null) {
            originalFont = FontUtils.getPoppinsFont(20f);
        }
        Map<TextAttribute, Object> attributes = new HashMap<>(originalFont.getAttributes()); // Create a new map
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        setFont(originalFont.deriveFont(attributes));
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        setUnderlineFont(); // Reapply underline when text changes
    }

    @Override
    public void setFont(Font font) {
        super.setFont(font);
        // Do not call setUnderlineFont() here to avoid recursion
    }
}
