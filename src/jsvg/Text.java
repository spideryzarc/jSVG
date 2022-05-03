/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsvg;

/**
 * @author einstein
 */
public class Text implements SVGElement {

    public Text(double x, double y, String text, String... extra) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.extra = String.join(" ", extra);
    }

    double x, y, dy = 1.2f;
    String extra, text;

    @Override
    public String toString() {
        String content = text;
        if (text.contains("\n")) {
            String line[] = text.split("\n");
            StringBuilder sb = new StringBuilder();
            boolean first = true;
            for (String l : line) {
                if (first) {
                    sb.append("\n\t<tspan x=\"" + x + "\">" + l + "</tspan>");
                    first = false;

                } else {
                    sb.append("\n\t<tspan x=\"" + x + "\" dy=\"" + dy + "em\">" + l + "</tspan>");
                }
            }
            sb.append("\n");
            content = sb.toString();
        }
        return String.format("<text x=\"%.2f\" y=\"%.2f\" %s>%s</text>", x, y, extra, content);
    }

}
