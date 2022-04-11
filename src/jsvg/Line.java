/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsvg;

/**
 *
 * @author einstein
 */
public class Line implements SVGElement {

    double x1, y1, x2, y2;
    String extra;

    public Line(double x1, double y1, double x2, double y2, String extra) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.extra = extra;
    }

    @Override
    public String toString() {
        return String.format("<line x1=\"%.2f\" y1=\"%.2f\" x2=\"%.2f\" y2=\"%.2f\" %s />", x1, y1, x2, y2, extra);
    }

}
