package Config;
import java.awt.Color;
public enum ColorPalette {
    PRIMARY(new Color(0x0C0A0B)),
    SECONDARY(new Color(0xC19D08)),
    BACKGROUND(new Color(0xB6B6B6)),
    SELECTED (new Color(0x705A04)),
    HOVER (new Color(0x79705A04, true)),
    TABLE (new Color(0x494846))
    ;

    private final Color color;

    ColorPalette(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}

