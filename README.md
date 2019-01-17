# How it works

Program is invoked with four arguments:

`java-introcs ColorAveragingStack 0.30 0.83 0.46 0.06`

Args are the four starting corner colors. Range is the interval (0,1).

0 is red, 0.1 orangeish, 0.2 yellowish, 0.3 greenish, 0.6 mid blueish, 
0.7 purple, 0.9 pinkish, 1.0 red again. All you need to start the program
is pick these four initial corner colors.

The first image it generates is this:

![screen shot canvas](output/stacks/i0.3-0.83-0.46-0.06--0.jpg?raw=true "")

Most interesting Watson. The above square's color is just average of the four colors you chose.

But it's a bit hard to understand average of four colours in our head, so...

This image prints your four corner colors, too (this is a hack to demonstrate
how the algorithm works, it doesn't need to print those colors).

![screen shot canvas](output/stacks/i0.3-0.83-0.46-0.06--0c.jpg?raw=true "")

The next thing the algorithm needs is to compute the midpoint colors for each
side. Lets show those. This is also a hack. These aren't printed out this way
later, as we'll see.

![screen shot canvas](output/stacks/i0.3-0.83-0.46-0.06--0cm.jpg?raw=true "")

You can probably get some idea that the midpoint colors are in fact the averages
of the corner colors, at least in a few cases, like royal blue being the color
in between sky blue and purple, on the left side.

But it's still a bit hard verifying the computation. So, the next image prints
the color numbers on top. This is just for verification, obviously we don't
want an actual output image like this. It's to help us understand what's going
on. Now you can convince yourself it really is just averaging the color numbers.

![screen shot canvas](output/stacks/i0.3-0.83-0.46-0.06--0cmt.jpg?raw=true "")

At this point, we are fully set up to start the recursion.

Please observe, we may now consider the four quadrants/quarters of the large square above
as simply smaller versions of itself (all the quarters of the large square now 
have corner colors of their own, because we added the midpoint colors).
Let's consider the bottom left quadrant/quarter of the large square above. 
Its top left corner is the royal blue, its bottom left is the sky blue, its top right
is the same as the background color of the main square (we didn't plot that one as a small
square, it would be the same color as its background, so invisible), its bottom right is 
the yellow-green. Those are the *input* to another level of *exactly the same* computation. 
You can see in the image below we now __a)__ draw those quadrants now
using a background color of their newly computed midpoint colors (average of their
corner colors), then __b)__ compute their new midpoints, simply from their corner colors,
exactly as we did before.

We do this for all four subquadrants:

![screen shot canvas](output/stacks/i0.3-0.83-0.46-0.06--1cm.jpg?raw=true "")

And let's go one more level down:

![screen shot canvas](output/stacks/i0.3-0.83-0.46-0.06--2cm.jpg?raw=true "")

And again:

![screen shot canvas](output/stacks/i0.3-0.83-0.46-0.06--3cm.jpg?raw=true "")

At this point the corner squares are getting hard to see, plus we don't really
need them anymore, so lets drop them:

![screen shot canvas](output/stacks/i0.3-0.83-0.46-0.06--4.jpg?raw=true "")

And another level down:

![screen shot canvas](output/stacks/i0.3-0.83-0.46-0.06--5.jpg?raw=true "")

And again:

![screen shot canvas](output/stacks/i0.3-0.83-0.46-0.06--6.jpg?raw=true "")

Finally we hit the pixel level so there's no point going further:

![screen shot canvas](output/stacks/i0.3-0.83-0.46-0.06--7.jpg?raw=true "")



I'm re-remebering in building this that color space is far from a simple thing,
our representing colors here in just 1D, as interval (0,1) is not the full story.


Finally,  I'm not sure it's terribly interesting conceptually, but since the java color
class used here allows overflow, you can pass color values bigger than 1, and 
if we do that, then the averaging effect over the larger numbers is a bit different,
giving results which tend to cycle the full space, creating waves.

E.g., using these _illegal color values causing overflow_: `ji ColorAveragingStack 10.0 3.5 6.7 4.3` 

We get:

![screen shot canvas](output/stacks/i10.0-3.5-6.7-4.3--0cmt.jpg?raw=true "")

and its final image is:

![screen shot canvas](output/stacks/i10.0-3.5-6.7-4.3--7.jpg?raw=true "")

All that from four numbers and some recursive averaging...

___The verdict? ...Not bad but it ain't no Monet!___

---

Here's a Manet.

Ain't got no algo for that.

![screen shot canvas](Manet.jpg?raw=true "")

