package com.julia.sets.presentation.complex.plane;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.julia.sets.presentation.colors.ColorPalette;
import com.julia.sets.presentation.colors.ColorPaletteException;

/**
 * This class is designed to handle the details needed to generate an image of a
 * Julia set. Julia sets will be associated to functions of the form <br>
 * <br>
 * f(z) = z<sup>2</sup> + c <br>
 * <br>
 * where c is a complex number (the field juliaNum). This class also contains
 * the following fields
 * <ul>
 * <li>iterations: the maximum number of times we will apply the Julia function
 * f(z) above</li>
 * <li>maxModulus: a value we'll use to compare successive iterations of f(z)
 * against to determine if the sequence is diverging to infinity.</li>
 * <li>juliaWindow: the {@link JuliaWindow} that will be used for the
 * image.</li>
 * <li>colorPalette: a color palette to be used in the image generated. Colors
 * at the beginning of the list represent points that diverge to infinity
 * quickly. Colors at the end of the list require more iterations. The color
 * black will be used for points that do not exceed the maxModulus within the
 * set number of iterations.</li>
 * </ul>
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 */
public class JuliaPicture {

   /**
    * The default value used for iterations when none has been set.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   public static final Integer DEFAULT_ITERATIONS = 100;

   /**
    * Upper limit for which iterations can be set
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   public static final Integer ITERATIONS_UPPER_BOUND = 10000;

   /**
    * The default value used for maxModulus when none has been set.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   public static final double DEFAULT_MAX_MODULUS = 1000;

   /**
    * Limit set for upper bound for maxModulus
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   public static final double MAX_MODULUS_UPPER_BOUND = 1000000;

   /**
    * The default value used by colors when it has not been set.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   protected static final Color[] DEFAULT_COLORS = { Color.BLUE, Color.WHITE, Color.RED };

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return a copy of DEFAULT_COLORS
    */
   public static Color[] getDefaultColors() {
      return Arrays.copyOf(DEFAULT_COLORS, DEFAULT_COLORS.length);
   }

   /**
    * The default value used for juliaWindow when it has not been set.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    */
   protected static final JuliaWindow DEFAULT_JULIA_WINDOW = new JuliaWindow();

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return a copy of the default {@link JuliaWindow}
    * @throws JuliaException
    *            if defaults are set to bad values
    */
   public static JuliaWindow getDefaultJuliaWindow() throws JuliaException {

      return new JuliaWindow(DEFAULT_JULIA_WINDOW.getMinXValue(), DEFAULT_JULIA_WINDOW.getMaxXValue(),
            DEFAULT_JULIA_WINDOW.getPictureWidth(), DEFAULT_JULIA_WINDOW.getMinYValue(),
            DEFAULT_JULIA_WINDOW.getMaxYValue(), DEFAULT_JULIA_WINDOW.getPictureHeight());
   }

   /**
    * Default color to use for the interior of the Julia Sets. That is, if
    * |f<sub>n</sub>(z)| <= maxModulus, for 1 <= n <= iterations, then we'll assign
    * the pixel corresponding with z to be this default color.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   public static final Color DEFAULT_INTERIOR_COLOR = Color.BLACK;

   private Complex juliaNum;

   private Integer iterations = DEFAULT_ITERATIONS;

   private double maxModulus = DEFAULT_MAX_MODULUS;

   private JuliaWindow juliaWindow = DEFAULT_JULIA_WINDOW;

   private ColorPalette colorPalette;

   private Color interiorColor = DEFAULT_INTERIOR_COLOR;

   /**
    * Create a JuliaPicture with default values set for
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param juliaNum
    *           The number c in the funciton f(z) = z<sup>2</sup> + c
    * @throws ColorPaletteException
    * @throws JuliaException
    */
   public JuliaPicture(Complex juliaNum) throws ColorPaletteException, JuliaException {
      super();

      setJuliaNum(juliaNum);
      this.colorPalette = new ColorPalette(DEFAULT_COLORS, iterations);
   }

   /**
    * Constructor to use when any non-default values are desired
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param juliaNum
    *           The number c in the Julia Function f(z) = z<sup>2</sup> + c
    * @param iterations
    *           maximum number of times the Julia Function will be applied
    * @param maxModulus
    *           the number to compare f<sub>n</sub>(z) against to determine
    *           divergence
    * @param juliaWindow
    *           the rectangle in the complex plane used for the resulting image
    * @param colors
    *           a small set of colors to use in the resulting image to
    *           differentiate quick divergence vs slow divergence
    * @param interiorColor
    *           the interior color for the Julia set
    * @throws ColorPaletteException
    *            if colors is empty or iterations <= 0
    * @throws JuliaException
    */
   public JuliaPicture(Complex juliaNum, Integer iterations, double maxModulus, JuliaWindow juliaWindow,
         Color[] colors, Color interiorColor)
         throws ColorPaletteException, JuliaException {
      super();

      setJuliaNum(juliaNum);

      validateIterations(iterations);
      this.iterations = iterations;

      this.maxModulus = maxModulus;
      setJuliaWindow(juliaWindow);
      this.colorPalette = new ColorPalette(colors, iterations);
      setInteriorColor(interiorColor);
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return {@link Complex} value for the Julia Number
    */
   public Complex getJuliaNum() {
      return juliaNum;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param juliaNum
    *           the new Julia Number
    * @throws JuliaException
    */
   public void setJuliaNum(Complex juliaNum) throws JuliaException {
      if (juliaNum == null) {
         throw new JuliaException(JuliaException.NULL_JULIA_NUMBER);
      }

      this.juliaNum = juliaNum;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return the value of iterations
    */
   public Integer getIterations() {
      return iterations;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param iterations
    *           the desired value for iterations
    * @throws ColorPaletteException
    *            if iterations <= 0
    * @throws JuliaException
    */
   public void setIterations(Integer iterations) throws ColorPaletteException, JuliaException {
      validateIterations(iterations);
      this.iterations = iterations;
      colorPalette.setColorPaletteSize(iterations);
   }

   private void validateIterations(Integer iterations) throws JuliaException {
      if (iterations == null) {
         throw new JuliaException(JuliaException.NONNEGATIVE_ITERATIONS);
      }
      if (iterations >= ITERATIONS_UPPER_BOUND) {
         throw new JuliaException("iterations cannot exceed " + ITERATIONS_UPPER_BOUND);
      }
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return the value of maxModulus
    */
   public double getMaxModulus() {
      return maxModulus;
   }

   /**
    * @author Dan Sievewright
    * @version
    * @since 1.0.0
    *
    * @param maxModulus
    *           the desired value for maxModulus
    * @throws JuliaException
    */
   public void setMaxModulus(double maxModulus) throws JuliaException {
      if (maxModulus > MAX_MODULUS_UPPER_BOUND) {
         throw new JuliaException("maxModulus cannot exceed " + MAX_MODULUS_UPPER_BOUND);
      }
      this.maxModulus = maxModulus;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return the {@link JuliaWindow} for the resulting image of the Julia set
    */
   public JuliaWindow getJuliaWindow() {
      return juliaWindow;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param juliaWindow
    *           the desired subset of the complex plane to use to look at the Julia
    *           set
    * @throws JuliaException
    */
   public void setJuliaWindow(JuliaWindow juliaWindow) throws JuliaException {
      if (juliaWindow == null) {
         throw new JuliaException(JuliaException.NULL_JULIA_WINDOW);
      }
      this.juliaWindow = juliaWindow;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return the interiorColor
    */
   public Color getInteriorColor() {
      return interiorColor;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param interiorColor
    *           the desired {@link Color} to use for the interiorColor
    * @throws JuliaException
    */
   public void setInteriorColor(Color interiorColor) throws JuliaException {
      if (interiorColor == null) {
         throw new JuliaException("interiorColor cannot be null");
      }

      this.interiorColor = interiorColor;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return the set of base colors used for the image
    */
   public Color[] getColors() {
      return colorPalette.getColors();
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param colors
    *           the desired base colors to use for the image
    * @throws ColorPaletteException
    *            if colors is set to an empty array or null value
    */
   public void setColors(Color[] colors) throws ColorPaletteException {
      if (colors == null || colors.length == 0) {
         throw new ColorPaletteException(ColorPaletteException.INVALID_COLORS);
      }

      colorPalette.setColors(colors);
   }

   /**
    * applies the Julia Function f(z) = z<sup>2</sup> + juliaNum
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param z
    *           the complex number to pass in to the Julia function f(z) =
    *           z<sup>2</sup> + juliaNum
    * @return the value f(z)
    */
   public Complex applyJuliaFunction(Complex z) {
      // Julia functions in this class are all of the form f(z) = z^2 + juliaNum
      return z.multiply(z).add(juliaNum);
   }

   /**
    * Determines the number of iterations of the Julia Functions we need to exceed
    * the maxModulus. <br>
    * <br>
    * A value of -1 is returned when f<sub>n</sub>(z) never exceeds the maxModulus,
    * for 1 <= n <= iterations.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param z
    *           the value we wish to determine how long it takes to diverge
    * @return the number of iterations needed for f<sub>n</sub>(z) to exceed
    *         maxModulus.
    */
   public Integer iterationsToDiverge(Complex z) {
      for (Integer i = 1; i <= getIterations(); i++) {
         if (z.modulus() - getMaxModulus() > 0) {
            return i;
         } else {
            z = applyJuliaFunction(z);
         }
      }
      return -1;
   }

   /**
    * Get a palette of colors to use in the Julia picture based on the base colors
    * set for this object and the number of iterations we'll be applying the Julia
    * Function. <br>
    * <br>
    * A positive return value for {@link JuliaPicture#iterationsToDiverge} will
    * correspond to an index in the colorPalette. Note that elements within the
    * colorPalette are not necessarily unique.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return a palette of colors
    * @throws ColorPaletteException
    *            if a colorPalette cannot be determined.
    */
   public List<Color> getFilledColorPalette() throws ColorPaletteException {

      return colorPalette.getFilledColorPalette();
   }

   @Override
   public int hashCode() {
      return Objects.hash(colorPalette, interiorColor, iterations, juliaNum, juliaWindow, maxModulus);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (!(obj instanceof JuliaPicture)) {
         return false;
      }
      JuliaPicture other = (JuliaPicture) obj;
      return Objects.equals(colorPalette, other.colorPalette)
            && Objects.equals(interiorColor, other.interiorColor)
            && Objects.equals(iterations, other.iterations) && Objects.equals(juliaNum, other.juliaNum)
            && Objects.equals(juliaWindow, other.juliaWindow)
            && Double.doubleToLongBits(maxModulus) == Double.doubleToLongBits(other.maxModulus);
   }

   @Override
   public String toString() {
      return "JuliaPicture [juliaNum=" + juliaNum + ", iterations=" + iterations + ", maxModulus="
            + maxModulus + ", juliaWindow=" + juliaWindow + ", colorPalette=" + colorPalette
            + ", interiorColor=" + interiorColor + "]";
   }
   
}
