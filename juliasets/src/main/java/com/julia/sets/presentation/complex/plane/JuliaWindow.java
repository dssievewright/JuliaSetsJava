package com.julia.sets.presentation.complex.plane;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents a rectangle in the complex plane of the form <br>
 * <br>
 * {z &isin; &Copf;: minXValue <= Re(z) <= maxXValue <br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; and
 * minYValue <= Im(z) <= maxYValue} <br>
 * <br>
 * The resolution of a {@link JuliaPicture} is controlled by the pictureWidth
 * and pictureHeight in this class. <br>
 * <br>
 * Pixels in this window are formed from the cross product of the xValues and
 * yValues lists. In other words, each pixel corresponds to a point in the
 * following set <br>
 * <br>
 * {z &isin; &Copf;: Re(z) &isin; xValues and Im(z) &isin; yValues}
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 */
public class JuliaWindow {

   /**
    * Default value to be used for:
    * <ul>
    * <li>the value along the real axis on the left hand side of a picture</li>
    * <li>the value along the imaginary axis at the bottom of a picture</li>
    * </ul>
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   public static final double DEFAULT_MIN_VALUE = -2;

   /**
    * Default value to be used for:
    * <ul>
    * <li>the value along the real axis on the right hand side of a picture</li>
    * <li>the value along the imaginary axis at the top of a picture</li>
    * </ul>
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   public static final double DEFAULT_MAX_VALUE = 2;

   /**
    * Default value to be used for both the width and height of a picture (in
    * pixels). Currently, default resolution is set to generate a 480 x 480 pixel
    * image.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   public static final Integer DEFAULT_RESOLUTION = 480;

   /**
    * Maximum value to be used for pictureWidth or pictureHeight
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   public static final Integer RESOLUTION_UPPER_BOUND = 10000;

   private double minXValue = DEFAULT_MIN_VALUE;

   private double maxXValue = DEFAULT_MAX_VALUE;

   private Integer pictureWidth = DEFAULT_RESOLUTION;

   private double minYValue = DEFAULT_MIN_VALUE;

   private double maxYValue = DEFAULT_MAX_VALUE;

   private Integer pictureHeight = DEFAULT_RESOLUTION;

   private List<Double> xValues = new ArrayList<>();

   private List<Double> yValues = new ArrayList<>();

   /**
    * Initializes a JuliaWindow with default values
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   JuliaWindow() {
      super();
      populateXValues();
      populateYValues();
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param minXValue
    *           the value along the real axis that the left side of the picture
    *           will represent
    * @param maxXValue
    *           the value along the real axis that the right side of the picture
    *           will represent
    * @param pictureWidth
    *           width in pixels that the final image will be
    * @param minYValue
    *           the value along the imaginary axis that the bottom of the picture
    *           will represent
    * @param maxYValue
    *           the value along the imaginary axis that the top of the picture will
    *           represent
    * @param pictureHeight
    *           height in pixels that the final image will be
    * @throws JuliaException
    */
   public JuliaWindow(double minXValue, double maxXValue, Integer pictureWidth, double minYValue,
         double maxYValue, Integer pictureHeight)
         throws JuliaException {
      super();

      // Do validations
      if (minXValue >= maxXValue) {
         throw new JuliaException(JuliaException.POSITIVE_WIDTH_REQ);
      }
      if (minYValue >= maxYValue) {
         throw new JuliaException(JuliaException.POSITIVE_HEIGHT_REQ);
      }
      validatePictureWidth(pictureWidth);
      validatePictureHeight(pictureHeight);

      // set fields
      this.minXValue = minXValue;
      this.maxXValue = maxXValue;
      this.pictureWidth = pictureWidth;
      this.minYValue = minYValue;
      this.maxYValue = maxYValue;
      this.pictureHeight = pictureHeight;

      // fill derived fields
      populateXValues();
      populateYValues();
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return the value along the real axis that the left side of the picture will
    *         represent
    */
   public double getMinXValue() {
      return minXValue;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param minXValue
    *           the value along the real axis that the left side of the picture
    *           will represent
    * @throws JuliaException
    *            if setting this value results in a non-positive width
    */
   public void setMinXValue(double minXValue) throws JuliaException {
      if (minXValue >= maxXValue) {
         throw new JuliaException(JuliaException.POSITIVE_WIDTH_REQ);
      }
      populateXValues();

      this.minXValue = minXValue;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return the value along the real axis that the right side of the picture will
    *         represent
    */
   public double getMaxXValue() {
      return maxXValue;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param maxXValue
    *           the value along the real axis that the right side of the picture
    *           will represent
    * @throws JuliaException
    *            if setting this value results in a non-positive width
    */
   public void setMaxXValue(double maxXValue) throws JuliaException {
      if (maxXValue <= minXValue) {
         throw new JuliaException(JuliaException.POSITIVE_WIDTH_REQ);
      }
      populateXValues();

      this.maxXValue = maxXValue;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return width in pixels of the image to be generated
    */
   public Integer getPictureWidth() {
      return pictureWidth;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param pictureWidth
    *           number of pixels used for the width of the image
    * @throws JuliaException
    *            if pictureWidth is not positive
    */
   public void setPictureWidth(Integer pictureWidth) throws JuliaException {
      validatePictureWidth(pictureWidth);
      populateXValues();
      this.pictureWidth = pictureWidth;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param pictureWidth
    *           the width to validate
    * @throws JuliaException
    *            if width is too small or too large
    */
   private void validatePictureWidth(Integer pictureWidth) throws JuliaException {
      if (pictureWidth == null || pictureWidth <= 0) {
         throw new JuliaException(JuliaException.POSITIVE_WIDTH_REQ);
      }
      if (pictureWidth > RESOLUTION_UPPER_BOUND) {
         throw new JuliaException("Picture width cannot exceed " + RESOLUTION_UPPER_BOUND);
      }
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return the value along the imaginary axis that the bottom of the picture
    *         will represent
    */
   public double getMinYValue() {
      return minYValue;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param minYValue
    *           the value along the imaginary axis that the bottom of the picture
    *           will represent
    * @throws JuliaException
    *            if setting this value forces the window size to be non-negative
    */
   public void setMinYValue(double minYValue) throws JuliaException {
      if (minYValue >= maxYValue) {
         throw new JuliaException(JuliaException.POSITIVE_HEIGHT_REQ);
      }
      populateYValues();
      this.minYValue = minYValue;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return the value along the imaginary axis that the top of the picture will
    *         represent
    */
   public double getMaxYValue() {
      return maxYValue;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param maxYValue
    *           the value along the imaginary axis that the top of the picture will
    *           represent
    * @throws JuliaException
    *            if setting this value forces the window size to be non-negative
    */
   public void setMaxYValue(double maxYValue) throws JuliaException {
      if (maxYValue <= minYValue) {
         throw new JuliaException(JuliaException.POSITIVE_HEIGHT_REQ);
      }
      populateYValues();
      this.maxYValue = maxYValue;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return number of pixels to be used in the height of a picture
    */
   public Integer getPictureHeight() {
      return pictureHeight;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param pictureHeight
    *           number of pixels to be used in the height of a picture
    * @throws JuliaException
    *            if pictureHeight is not positive
    */
   public void setPictureHeight(Integer pictureHeight) throws JuliaException {
      validatePictureHeight(pictureHeight);
      populateYValues();
      this.pictureHeight = pictureHeight;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param pictureHeight
    *           the height to validate
    * @throws JuliaException
    *            if height is too small or too large
    */
   private void validatePictureHeight(Integer pictureHeight) throws JuliaException {
      if (pictureHeight == null || pictureHeight <= 0) {
         throw new JuliaException(JuliaException.POSITIVE_HEIGHT_REQ);
      }
      if (pictureHeight > RESOLUTION_UPPER_BOUND) {
         throw new JuliaException("Picture height cannot exceed " + RESOLUTION_UPPER_BOUND);
      }
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return {@link List}{@code <}{@link Double}{@code >} of xValues that will
    *         make up the real components of the complex numbers used to represent
    *         pixels in the final image.
    */
   public List<Double> getXValues() {
      return xValues;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return {@link List}{@code <}{@link Double}{@code >} of yValues that will
    *         make up the imaginary components of the complex numbers used to
    *         represent pixels in the final image.
    */
   public List<Double> getYValues() {
      return yValues;
   }

   /**
    * Fill the xValues list. These values will be used to determine the lower
    * left-hand real value of a pixel in the image that gets generated.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   private void populateXValues() {
      xValues = populateValues(minXValue, maxXValue, pictureWidth);
   }

   /**
    * Fill the yValues list. These values will be used to determine the lower
    * left-hand imaginary value of a pixel in the image that gets generated.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   private void populateYValues() {
      yValues = populateValues(minYValue, maxYValue, pictureHeight);
   }

   /**
    * Fill the values list so that the elements match the value of either the real
    * or imaginary value in the lower left-hand corner of the pixel in the image to
    * be generated.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @param valuesList
    *           desired list to populate with values
    * @param minValue
    *           minimum value along either the Real or Imaginary axis
    * @param maxValue
    *           maximum value along either the Real or Imaginary axis
    * @param listSize
    *           desired number of pixels along this axis
    */
   private List<Double> populateValues(double minValue, double maxValue, Integer listSize) {
      // reinitialize values list
      List<Double> values = new ArrayList<>();
      values.add(minValue);

      // determine the size that one pixel will represent
      double stepSize = (maxValue - minValue) / listSize;
      // placeholder for last value added to list
      double lastValue = minValue;

      // i represents current size of values list
      // stop when size of valuesList will reach desired size
      for (Integer i = 1; i < listSize; i++) {
         lastValue += stepSize;
         values.add(lastValue);
      }

      return values;
   }

   @Override
   public int hashCode() {
      return Objects.hash(maxXValue, maxYValue, minXValue, minYValue, pictureHeight, pictureWidth);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (!(obj instanceof JuliaWindow)) {
         return false;
      }
      JuliaWindow other = (JuliaWindow) obj;
      return Double.doubleToLongBits(maxXValue) == Double.doubleToLongBits(other.maxXValue)
            && Double.doubleToLongBits(maxYValue) == Double.doubleToLongBits(other.maxYValue)
            && Double.doubleToLongBits(minXValue) == Double.doubleToLongBits(other.minXValue)
            && Double.doubleToLongBits(minYValue) == Double.doubleToLongBits(other.minYValue)
            && Objects.equals(pictureHeight, other.pictureHeight)
            && Objects.equals(pictureWidth, other.pictureWidth);
   }

   @Override
   public String toString() {
      return "JuliaWindow [minXValue=" + minXValue + ", maxXValue=" + maxXValue + ", pictureWidth="
            + pictureWidth + ", minYValue=" + minYValue + ", maxYValue=" + maxYValue + ", pictureHeight="
            + pictureHeight + "]";
   }

}
