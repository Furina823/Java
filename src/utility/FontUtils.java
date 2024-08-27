package utility;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.HashMap;
import java.awt.FontFormatException;

public class FontUtils {

    private static Font poppinsFont;

    static {
        try {
            // Load the Poppins font
            URL fontURL = FontUtils.class.getResource("/resource/Poppins-Regular.ttf");
            if (fontURL != null) {
                poppinsFont = Font.createFont(Font.TRUETYPE_FONT, fontURL.openStream()).deriveFont(Font.PLAIN, 12f);
            } else {
                poppinsFont = new Font("SansSerif", Font.PLAIN, 12); // Fallback font
            }
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            poppinsFont = new Font("SansSerif", Font.PLAIN, 12); // Fallback font
        }
    }

    public static Font getPoppinsFont(float size) {
        return poppinsFont.deriveFont(size);
    }

    public static Font getPoppinsFontBold(float size) {
        return poppinsFont.deriveFont(Font.BOLD, size);
    }

    public static Font getPoppinsFontItalic(float size) {
        return poppinsFont.deriveFont(Font.ITALIC, size);
    }

    public static Font getPoppinsFontBoldItalic(float size) {
        return poppinsFont.deriveFont(Font.BOLD | Font.ITALIC, size);
    }

    public static Font getPoppinsFontUnderlined(float size) {
        Font font = getPoppinsFont(size);
        Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        return font.deriveFont(attributes);
    }


    public static Font getPoppinsFontWithColor(float size, java.awt.Color color) {
        Font font = getPoppinsFont(size);
        // Create a new AttributedString with the desired color
        Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
        attributes.put(TextAttribute.FOREGROUND, color);
        return font.deriveFont(attributes);
    }

    public static Font getPoppinsFontUnderlinedWithColor(float size, java.awt.Color color) {
        Font font = getPoppinsFont(size);
        // Create a new AttributedString with underline and desired color
        Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        attributes.put(TextAttribute.FOREGROUND, color);
        return font.deriveFont(attributes);
    }

    public static Font getPoppinsFontWithBackground(float size, java.awt.Color background) {
        Font font = getPoppinsFont(size);
        return font;
    }
}
