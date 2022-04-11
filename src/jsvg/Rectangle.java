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
public class Rectangle implements SVGElement {

    double h, w, x, y;
    double rx, ry;
    String extra = "#ED6E46";

    public Rectangle(double h, double w, double x, double y, double rx, double ry, String extra) {
        this.h = h;
        this.w = w;
        this.x = x;
        this.y = y;
        this.rx = rx;
        this.ry = ry;
        this.extra = extra;
    }

    @Override
    public String toString() {
        return String.format("<rect width=\"%.2f\" height=\"%.2f\" x=\"%.2f\" y=\"%.2f\" rx=\"%.2f\" ry=\"%.2f\" %s />", w, h, x, y, rx, ry, extra);
    }
}
