package com.julia.sets.presentation.colors;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class for retrieving a color palette
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 */
public class ColorPalette {

   private Color[] colors;

   private int colorPaletteSize;

   private List<Color> filledColorPalette;

   /**
    * This method returns a list of colors that starts with the first color in
    * colors, then contains colors in between colors[i] and colors[i+1], for 0 <= i
    * < colors.size - 1. <br>
    * <br>
    * Notes:
    * <ul>
    * <li>If colors.size > colorPaletteSize, then the returned color palette will
    * be a subset of colors.</li>
    * <li>If colors.size is much larger than colors (intended use of this), then
    * we'll receive a linear color gradient between each pair of successive
    * {@link Color}s in colors.</li>
    * </ul>
    * 
    * As an example if colors = [Blue, White, Red] and colorPaletteSize = 100, then
    * this method returns a list that
    * <ul>
    * <li>starts with Blue,</li>
    * <li>contains progressively lighter shades of Blue in elements 1-49,</li>
    * <li>contains White as the 50th element,</li>
    * <li>contains progressively darker shades of Red in elements 51-99, and</li>
    * <li>contains Red as the last element.</li>
    * </ul>
    * 
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param colors
    *           a small base set of {@link Color}s to start with
    * @param colorPaletteSize
    *           the final size of the color palette
    * @return the resulting color palette
    * @throws ColorPaletteException
    *            if colors is null/empty or if the desired colorPaletteSize is not
    *            positive
    */
   private List<Color> createColorPalette() {

      // initialize answer with first color
      List<Color> answer = new ArrayList<>();
      answer.add(colors[0]);

      for (var i = 0; i < colors.length - 1; i++) {
         // calculate number of color palettes remaining to be found
         var colorIntervalsRemaining = colors.length - (i + 1);

         // calculate remaining palette size
         // include remaining intervals since endpoint needs to be double counted
         int remainingPaletteSize = colorPaletteSize - answer.size() + colorIntervalsRemaining;

         int nextPaletteSize = remainingPaletteSize / colorIntervalsRemaining;

         List<Color> tmpAnswer = getColorPalette(colors[i], colors[i + 1], nextPaletteSize);

         answer.addAll(tmpAnswer);
      }

      return answer;
   }

   /**
    * This creates a color palette between two pairs of colors that undergoes a
    * sequence of linear gradient differences between the two.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param startColor
    *           the first color in the color palette
    * @param endColor
    *           the last color in the color palette
    * @param paletteSize
    *           the desired size of the color palette
    * @param removeStart
    *           {@link Boolean} to indicate if the start color should be removed.
    *           If true, the response will have size = paletteSize - 1
    * @return the color palette
    * @throws ColorPaletteException
    *            if any passed in parameter is null
    */
   private List<Color> getColorPalette(Color startColor, Color endColor, int paletteSize) {

      int redStart = startColor.getRed();
      int greenStart = startColor.getGreen();
      int blueStart = startColor.getBlue();

      int redEnd = endColor.getRed();
      int greenEnd = endColor.getGreen();
      int blueEnd = endColor.getBlue();

      int redDiff = redEnd - redStart;
      int greenDiff = greenEnd - greenStart;
      int blueDiff = blueEnd - blueStart;

      List<Color> answer = new ArrayList<>();

      while (answer.size() < paletteSize) {
         int newRed = redStart + (redDiff * answer.size()) / (paletteSize - 1);
         int newGreen = greenStart + (greenDiff * answer.size()) / (paletteSize - 1);
         int newBlue = blueStart + (blueDiff * answer.size()) / (paletteSize - 1);
         answer.add(new Color(newRed, newGreen, newBlue));
      }

      answer.remove(0);

      return answer;
   }

   /**
    * Main constructor for ColorPalette
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param colors
    *           the base set of colors to use in
    *           {@link ColorPalette#createColorPalette()}
    * @param colorPaletteSize
    *           desired size of the colorPalette
    * @throws ColorPaletteException
    *            if colors is empty or the palette size is not positive
    */
   public ColorPalette(Color[] colors, int colorPaletteSize) throws ColorPaletteException {
      validateColors(colors);
      validateColorPaletteSize(colorPaletteSize);

      this.colors = colors;
      this.colorPaletteSize = colorPaletteSize;
      filledColorPalette = createColorPalette();
   }

   /**
    * Returns the color palette with the desired size
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return the full color palette
    */
   public List<Color> getFilledColorPalette() {
      return filledColorPalette;
   }

   /**
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return the colors
    */
   public Color[] getColors() {
      return colors;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param colors
    *           the desired base colors to use for the palette
    * @throws ColorPaletteException
    *            if colors is empty
    */
   public void setColors(Color[] colors) throws ColorPaletteException {
      validateColors(colors);

      var resetPalette = true;
      if (Arrays.equals(this.colors, colors)) {
         resetPalette = false;
      }

      this.colors = colors;

      if (resetPalette) {
         filledColorPalette = createColorPalette();
      }
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param colors
    *           the colors to validate
    * @throws ColorPaletteException
    *            if colors is empty or any of the colors are undefined
    */
   private void validateColors(Color[] colors) throws ColorPaletteException {
      if (colors == null || colors.length == 0) {
         throw new ColorPaletteException(ColorPaletteException.INVALID_COLORS);
      }

      for (var i = 0; i < colors.length; i++) {
         if (colors[i] == null) {
            throw new ColorPaletteException(ColorPaletteException.INVALID_COLORS);
         }
      }
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return the colorPaletteSize
    */
   public int getColorPaletteSize() {
      return colorPaletteSize;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param colorPaletteSize
    *           the desired size of the color palette
    * @throws ColorPaletteException
    *            if the size is not positive
    */
   public void setColorPaletteSize(int colorPaletteSize) throws ColorPaletteException {
      validateColorPaletteSize(colorPaletteSize);

      var resetPalette = true;
      if (this.colorPaletteSize == colorPaletteSize) {
         resetPalette = false;
      }

      this.colorPaletteSize = colorPaletteSize;

      if (resetPalette) {
         filledColorPalette = createColorPalette();
      }
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param colorPaletteSize
    *           the palette size to validate
    * @throws ColorPaletteException
    *            if size is not positive
    */
   private void validateColorPaletteSize(int colorPaletteSize) throws ColorPaletteException {
      if (colorPaletteSize <= 0) {
         throw new ColorPaletteException(ColorPaletteException.INVALID_PALETTE_SIZE);
      }
   }

   @Override
   public int hashCode() {
      final var prime = 31;
      var result = 1;
      result = prime * result + colorPaletteSize;
      result = prime * result + Arrays.hashCode(colors);
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      ColorPalette other = (ColorPalette) obj;

      return colorPaletteSize == other.colorPaletteSize && Arrays.equals(colors, other.colors);
   }

   @Override
   public String toString() {
      return "ColorPalette [colors=" + Arrays.toString(colors) + ", colorPaletteSize=" + colorPaletteSize
            + "]";
   }

}
