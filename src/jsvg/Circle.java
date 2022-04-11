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
public class Circle implements SVGElement {


    public String extra = "";
    public double cx,cy,r;

    public Circle(double cx, double cy, double r, String extra) {
        this.extra = extra;
        this.cx = cx;
        this.cy = cy;
        this.r = r;
    }

    @Override
    public String toString() {
        return String.format("<circle cx=\"%.2f\" cy=\"%.2f\" r=\"%.2f\"  %s />", cx, cy, r, extra);
    }

}
