package jsvg;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

/**
 * JSVG is a simple SVG image generator, it is not a complete SVG implementation.
 * It is intended to be used in small projects where the SVG image is generated
 * dynamically. More specifically, it was developed to generate SVG images for
 * operations research problems.
 * 
 * @author Albert Einstein Fernandes Muritiba - einstein@ufc.br
 * @version 1.0
 * @since 2016-05-30
 */
public class JSVG {

    /**
     * w,h image base width/height in pixels
     */
    private int w = 1800, h = 1200;

    /**
     * vb____ view box coordinates in domain units
     */
    public int vbXmin = 0, vbYmin = 0, vbWidth = 1800, vbHeight = 1200;


    /**
     * list of SVG tags elements
     */
    private ArrayList<SVGElement> element = new ArrayList<>();

    /**
     * css style
     */
    private Style style;

    /**
     * set base image size in pixels
     *
     * @param w_px width in pixels
     * @param h_px height in pixels
     */
    public void setImageSize(int w_px, int h_px) {
        this.w = w_px;
        this.h = h_px;
    }

    /**
     * Set the view box coordinates in domain units.
     * The final image will be transposed to show this box.
     *
     * @param x left-top coordinate x
     * @param y left-top coordinate y
     * @param w width
     * @param h height
     */
    public void setViewBox(int x, int y, int w, int h) {
        this.vbXmin = x;
        this.vbYmin = y;
        this.vbWidth = w;
        this.vbHeight = h;
    }

    /**
     * Add to the image a svg element
     *
     * @param e a valid SVG tag element
     * @return e
     */
    public SVGElement add(SVGElement e) {
        element.add(e);
        return e;
    }

    /**
     * try to remove an element in the root of SVG image
     *
     * @param o element to be removed
     * @return iff element o was found and removed
     */
    public boolean remove(Object o) {
        return element.remove(o);
    }

    /**
     * reset SVG image
     */
    public void clear() {
        element.clear();
    }

    /**
     * body SVG template
     */
    private static final String body = "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n"
            + "<svg  width=\"%dpx\" height=\"%dpx\" viewBox=\"%d %d %d %d\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xml:space=\"preserve\">\n%s</svg>";

    /**
     * Generate the SVG tags strings
     *
     * @return SVG string
     */
    public String compile() {
        Locale.setDefault(Locale.US);
        StringBuilder sb = new StringBuilder();
        if(style!= null){
            sb.append("\t" + style.toString().replaceAll("\n", "\n\t"));
            sb.append("\n");
        }
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

    /**
     * Write SVG image to a file
     *
     * @param path output file path
     * @throws IOException
     */
    public void save(String path) throws IOException {
        FileWriter fw = new FileWriter(path);
        fw.write(compile());
        fw.close();
    }

    /**
     * set the optional css style
     *
     * @param style css style
     */
    public void setStyle(Style style) {
        this.style = style;
    }

    /**
     * Simple example of Jsvg
     *
     * @param arg
     * @throws IOException
     */
    public static void main(String arg[]) throws IOException {
        JSVG svg = new JSVG();
        svg.setImageSize(800, 800);
        svg.setViewBox(0, 0, 500, 500);

        svg.setStyle(new Style("vrp.css"));

        svg.add(new Line(50, 50, 400, 300, "class=\".route\"", "stroke=\"#ff0000\""));

        svg.add(new Circle(50, 50, 2, "class=\".client\""));
        svg.add(new Circle(400, 300, 2, "class=\".client\""));

        svg.add(new Text(40, 40, "olá mundo"));

        Group g = new Group(120,120);
        g.add(new Text(0, 0, "texto 1"));
        g.add(new Text(20, 20, "texto 2"));

        svg.add(g);

        System.out.println(svg);

        svg.save("test.svg");

    }


}
