package textformatter;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class CenteredText extends FormattedText {

    private final int width;
    private int cntLines;
    private static final String regex = "\\s+";
    private List<String> buffer = new ArrayList<>();

    CenteredText(String line, int width) {
        super(getFirst(line, width));
        this.width = width;
        this.cntLines = 1;
        process(line);
        for (int i = 1; i < buffer.size(); i++) {
            super.add(buffer.get(i));
            cntLines++;
        }
        buffer.clear();
    }

    /**
     * Get first line
     * @param line input
     */
    private void process(String line) {
        StringBuilder newLine = new StringBuilder();
        boolean firstBit = true;
        int justify;

        String[] bits = line.split(regex);
        for (int j = 0; j < bits.length; j++) {
            if (firstBit || bits[j].length() > width) {
                newLine.append(bits[j]);
                firstBit = false;
            } else {
                newLine.append(" ");
                newLine.append(bits[j]);
            }

            if (bits.length == j + 1 || (bits[j + 1].length() + newLine.length() + 1) > width) {
                justify = width - newLine.length();
                for (int i = 0; i < Math.floor(justify / 2.0); i++) {
                    newLine.insert(0, " ");
                }
                for (int i = 0; i < Math.ceil(justify / 2.0); i++) {
                    newLine.append(" ");
                }
                buffer.add(newLine.toString());
                newLine = new StringBuilder();
                firstBit = true;
            }
        }
    }

    /**
     * Get first line
     * @param line input
     * @param width width
     * @return first line justified as String
     */
    private static String getFirst(String line, int width) {
        StringBuilder fLine = new StringBuilder();
        boolean firstBit = true;
        int justify;

        for (String bit : line.split(regex)) {
            if (firstBit) {
                fLine.append(bit);
                firstBit = false;
            } else if ((bit.length() + fLine.length() + 1) <= width) {
                fLine.append(" ");
                fLine.append(bit);
            } else {
                break;
            }
        }

        justify = width - fLine.length();

        for (int i = 0; i < Math.floor(justify / 2.0); i++) {
            fLine.insert(0, " ");
        }
        for (int i = 0; i < Math.ceil(justify / 2.0); i++) {
            fLine.append(" ");
        }

        return fLine.toString();
    }

    @Override
    public boolean add(String line){
        if (line == null || line.equals(""))
            return false;

        process(line);
        for (String newLine : buffer) {
            super.add(newLine);
            cntLines++;
        }
        buffer.clear();
        return true;
    }

    @Override
    public int numlines() {
        return cntLines;
    }
}
