/**
 * ColorAveragingStack.java - client to ColorAveraging.java
 * author: Mike Walker
 *
 * ColorAveraging draws only at its lowest depth. So, in that default configuration
 * we can't save images within a single run showing how the recursion builds up the final image.
 * That's fine, as this client circumvents that by calling it with the same parameters
 * multiple times, at each successive depth, then saving the image produced.
 * That does repeat the same computation, and could be optimised away, but at current
 * small image size it doesn't matter.
 *
 * Goal here is generate a stack of images to get an insight into the recursion.
 * Output images can be flipped through or maybe make an animated gif if that's appealing.
 */
public class ColorAveragingStack {

    public static void main(String args[]) {

        double c1 = Double.parseDouble(args[0]);
        double c2 = Double.parseDouble(args[1]);
        double c3 = Double.parseDouble(args[2]);
        double c4 = Double.parseDouble(args[3]);

        double x_center    = 0.5;
        double y_center    = 0.5;
        double size        = 0.5;

        // run at depths 0 up to 7 & save a series, or stack, of images
        for(int i = 0; i <= 7; i++)
        {
            // Save the depth 0 image in 4 versions for illustrative purposes
            if (i == 0) {
                // ordinary image
                ColorAveraging.quad(x_center, y_center, size, c1, c2, c3, c4, i, "");
                StdDraw.save("./output/stacks/i"+c1+"-"+c2+"-"+c3+"-"+c4+"--"+i+".jpg");
                // with corner colors
                ColorAveraging.quad(x_center, y_center, size, c1, c2, c3, c4, i, "c");
                StdDraw.save("./output/stacks/i"+c1+"-"+c2+"-"+c3+"-"+c4+"--"+i+"c.jpg");
                // with corner colors & midpoint colors
                ColorAveraging.quad(x_center, y_center, size, c1, c2, c3, c4, i, "cm");
                StdDraw.save("./output/stacks/i"+c1+"-"+c2+"-"+c3+"-"+c4+"--"+i+"cm.jpg");
                // with corner colors & midpoint colors, plus text
                ColorAveraging.quad(x_center, y_center, size, c1, c2, c3, c4, i, "cmt");
                StdDraw.save("./output/stacks/i"+c1+"-"+c2+"-"+c3+"-"+c4+"--"+i+"cmt.jpg");

                continue;
            }
            // For these depths, include corners & midpoint colors
            if (i == 1 || i == 2 || i == 3) {
                ColorAveraging.quad(x_center, y_center, size, c1, c2, c3, c4, i, "cm");
                StdDraw.save("./output/stacks/i"+c1+"-"+c2+"-"+c3+"-"+c4+"--"+i+"cm.jpg");
                continue;
            }
            // Beyond this depth don't bother, they're too small in any case.
            if (i > 3) {
                ColorAveraging.quad(x_center, y_center, size, c1, c2, c3, c4, i, "");
                StdDraw.save("./output/stacks/i"+c1+"-"+c2+"-"+c3+"-"+c4+"--"+i+".jpg");
            }
        }
    }

}