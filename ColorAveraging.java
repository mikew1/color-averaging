
import java.awt.Color;
/**
 * ColorAveraging.java - inspired by Plasma.java, but focussing solely on color averaging.
 * author: Mike Walker
 */
public class ColorAveraging {

    public static void quad(double x, double y, double size,
                            double c1, double c2, double c3, double c4, int i, String debug) {

        // Compute quadrant's centroid color cM from its corner colors.
        double cM = (c1 + c2 + c3 + c4) / 4.0;

        // Compute midpoint color of each edge
        double cT = (c1 + c2) / 2.0;                       // color of new top center point
        double cB = (c3 + c4) / 2.0;                       // color of new bottom center point
        double cL = (c2 + c3) / 2.0;                       // color of new left center point
        double cR = (c1 + c4) / 2.0;                       // color of new right center point

        // We only draw anything in the base case; i.e. when a smallest quad is hit.
        if (i == 0) {
            // Color of quadrant is its centroid color cM:
            StdDraw.setPenColor(Color.getHSBColor((float) cM, 0.8f, 0.8f));
            // Draw quadrant as a solid square of centroid (x,y) & 'radius' size
            StdDraw.filledSquare(x, y, size);
            // Visualisation aids, useful only on runs which terminate hugely supra-pixel
            if (debug.equals("c")) {
                showCornerColors(c1, c2, c3, c4, x, y, size, false);
            }
            if (debug.equals("cm")) {
                showCornerColors(c1, c2, c3, c4, x, y, size, false);
                showMidpointColors(cT, cB, cL, cR, x, y, size, false);
            }
            if (debug.equals("cmt")) {
                showCornerColors(c1, c2, c3, c4, x, y, size, true);
                showMidpointColors(cT, cB, cL, cR, x, y, size, true);
            }
            return;
        }

        // Draw subquadrants using colors computed above
        quad(x+size/2, y+size/2, size/2, c1, cT, cM, cR, i-1, debug);  // draw quad I   (top right)
        quad(x-size/2, y+size/2, size/2, cT, c2, cL, cM, i-1, debug);  // draw quad II  (top left)
        quad(x-size/2, y-size/2, size/2, cM, cL, c3, cB, i-1, debug);  // draw quad III (bottom left)
        quad(x+size/2, y-size/2, size/2, cR, cM, cB, c4, i-1, debug);  // draw quad IV  (bottom right)
    }


    public static void main(String args[]) {

        double c1;      // top right corner color,    in range (0,1)
        double c2;      // top left corner color,     in range (0,1)
        double c3;      // bottom left corner color,  in range (0,1)
        double c4;      // bottom right corner color, in range (0,1)

        // Set corner colors for starting square from args - if "r" given, choose it randomly. precision to 0.01.
        if(args[0].equals("r")) { c1 = StdRandom.uniform(100)/100.0; } else { c1 = Double.parseDouble(args[0]); }
        if(args[1].equals("r")) { c2 = StdRandom.uniform(100)/100.0; } else { c2 = Double.parseDouble(args[1]); }
        if(args[2].equals("r")) { c3 = StdRandom.uniform(100)/100.0; } else { c3 = Double.parseDouble(args[2]); }
        if(args[3].equals("r")) { c4 = StdRandom.uniform(100)/100.0; } else { c4 = Double.parseDouble(args[3]); }

        System.out.println("c1: " + c1);
        System.out.println("c2: " + c2);
        System.out.println("c3: " + c3);
        System.out.println("c4: " + c4);

        int i = Integer.parseInt(args[4]);           // iteration count on which to stop recursion & draw

        String debug = "";                           // whether to draw corner & midpoint colors or not
        if (args.length == 6) { debug = args[5]; }   // hardwired to detect "c", "cm", & "cmt" as options.

        double x_center    = 0.5;
        double y_center    = 0.5;
        double size        = 0.5;                    // half width/'radius'.

        quad(x_center, y_center, size, c1, c2, c3, c4, i, debug);

    }


    // plot the corner colors, which are used to compute the centroid display color, but not normally shown.
    public static void showCornerColors(double c1, double c2, double c3, double c4,
                                          double x, double y, double size, boolean withText)
    {
        plotSmallSquare(c1, x+.9*size, y+.9*size, .08*size, withText);
        plotSmallSquare(c2, x-.9*size, y+.9*size, .08*size, withText);
        plotSmallSquare(c3, x-.9*size, y-.9*size, .08*size, withText);
        plotSmallSquare(c4, x+.9*size, y-.9*size, .08*size, withText);
    }

    // plot the midpoint colors, which constitute corner colors of sub-quadrants, not normally shown.
    public static void showMidpointColors(double cT, double cB, double cL, double cR,
                                            double x, double y, double size, boolean withText)
    {
        plotSmallSquare(cT, x,         y+.9*size, .08*size, withText);
        plotSmallSquare(cB, x,         y-.9*size, .08*size, withText);
        plotSmallSquare(cL, x-.9*size, y,         .08*size, withText);
        plotSmallSquare(cR, x+.9*size, y,         .08*size, withText);
    }

    public static void plotSmallSquare(double c, double x, double y, double size, boolean withText)
    {
        StdDraw.setPenColor(Color.getHSBColor((float) c, 0.8f, 0.8f));
        StdDraw.filledSquare(x, y, size);
        if (withText) {
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.text(x, y, Double.toString(c));
        }
    }

}