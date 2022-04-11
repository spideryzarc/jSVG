/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsvg;

import java.util.ArrayList;

/**
 * @author einstein
 */
public class Group implements SVGElement {

    private ArrayList<SVGElement> element = new ArrayList<>();

    public String extra;
    double tx = 0, ty = 0;

    public Group(double tx, double ty, String extra) {
        this.extra = extra;
        this.tx = tx;
        this.ty = ty;
    }

    public Group() {
        this.extra = "";
    }

    public boolean add(SVGElement e) {
        return element.add(e);
    }

    public boolean remove(Object o) {
        return element.remove(o);
    }

    public void clear() {
        element.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object o : element) {
            sb.append("\t" + o.toString().replaceAll("\n", "\n\t"));
            sb.append("\n");
        }
        if (tx == 0 && ty == 0)
            return String.format("<g %s>\n%s</g>", extra, sb);
        return String.format("<g transform=\"translate(%.2f,%.2f)\" %s>\n%s</g>", tx, ty, extra, sb);
    }

}
