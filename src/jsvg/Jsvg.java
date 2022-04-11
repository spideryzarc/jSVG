package jsvg;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

/**
 * @author einstein
 */
public class Jsvg {

    public int w = 1800, h = 1200;
    public int vbXmin = 0, vbYmin = 0, vbWidth = 1800, vbHeight = 1200;
    private ArrayList<SVGElement> element = new ArrayList<>();


    public void setImageSize(int w_px, int h_px) {
        this.w = w_px;
        this.h = h_px;
    }

    public void setViewBox(int x, int y, int w, int h) {
        this.vbXmin = x;
        this.vbYmin = y;
        this.vbWidth = w;
        this.vbHeight = h;
    }

    public SVGElement add(SVGElement e) {
        element.add(e);
        return e;
    }

    public boolean remove(Object o) {
        return element.remove(o);
    }

    public void clear() {
        element.clear();
    }

    public static final String body = "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n"
            + "<svg  width=\"%dpx\" height=\"%dpx\" viewBox=\"%d %d %d %d\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xml:space=\"preserve\">\n%s</svg>";

    public String compile() {
        Locale.setDefault(Locale.US);
        StringBuilder sb = new StringBuilder();
        for (Object o : element) {
            sb.append("\t" + o.toString().replaceAll("\n", "\n\t"));
            sb.append("\n");
        }
        return String.format(body, w, h, vbXmin, vbYmin, vbWidth, vbHeight, sb.toString());
    }

    @Override
    public String toString() {
        return compile();
    }

    public void save(String path) throws IOException {
        FileWriter fw = new FileWriter(path);
        fw.write(compile());
        fw.close();
    }


    public static void main(String arg[]) throws IOException {
        Jsvg svg = new Jsvg();
        svg.setImageSize(800, 800);
        svg.setViewBox(0, 0, 500, 500);

        svg.add(new Style("vrp.css"));

        svg.add(new Line(50, 50, 400, 300, "class=\".route\" stroke=\"#ff0000\""));

        svg.add(new Circle(50, 50, 2, "class=\".client\""));
        svg.add(new Circle(400, 300, 2, "class=\".client\""));

        svg.add(new Text(40, 40, "","olá mundo"));

        System.out.println(svg);

        svg.save("test.svg");

    }

}
