package com.julia.sets.domain.matrix;

import java.util.Arrays;
import java.util.Objects;

import com.julia.sets.presentation.complex.plane.JuliaException;
import com.julia.sets.presentation.complex.plane.JuliaWindow;

/**
 * Class to hold shared values among {@link FillMatrix} threads
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 */
public class MatrixThreadTracker {

   /**
    * Number to track the number of matrix positions that have been calculated for
    * the {@link FillMatrix} threads
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   private Integer threadsLocation = -1;

   /**
    * The matrix to be populated by the FillMatrix threads
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   public final Integer[][] matrix;

   /**
    * Number of rows in matrix. This field is added for code clarity instead of
    * instead matrix.length.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   public final Integer rows;

   /**
    * Number of columns in matrix. This field is added for code clarity instead of
    * using matrix[].length.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   public final Integer columns;

   /**
    * Size of matrix. This field is added for code clarity and to avoid its
    * repeated calculation in {@link FillMatrix#run}.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   public final Integer size;

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param jw
    *           the {@link JuliaWindow} being used to create the finished image
    * @throws JuliaException
    *            if an invalid {@link JuliaWindow} is passed in
    */
   public MatrixThreadTracker(JuliaWindow jw) throws JuliaException {
      if (jw == null) {
         throw new JuliaException(JuliaException.NULL_JULIA_WINDOW);
      }

      rows = jw.getPictureHeight();
      if (rows <= 0) {
         throw new JuliaException(JuliaException.POSITIVE_HEIGHT_REQ);
      }
      columns = jw.getPictureWidth();
      if (columns <= 0) {
         throw new JuliaException(JuliaException.POSITIVE_WIDTH_REQ);
      }
      size = rows * columns;
      this.matrix = new Integer[rows][columns];
   }

   /**
    * Increments the threadsLocation and returns the value. Make sure calls to this
    * are synchronized across threads.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return the next value of threadsLocation
    */
   public Integer getNextThreadLocation() {
      threadsLocation++;
      return threadsLocation;
   }

   @Override
   public int hashCode() {
      final var prime = 31;
      var result = 1;
      result = prime * result + Arrays.deepHashCode(matrix);
      result = prime * result + Objects.hash(threadsLocation);
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (!(obj instanceof MatrixThreadTracker)) {
         return false;
      }
      MatrixThreadTracker other = (MatrixThreadTracker) obj;
      return Arrays.deepEquals(matrix, other.matrix)
            && Objects.equals(threadsLocation, other.threadsLocation);
   }

   @Override
   public String toString() {
      return "MatrixThreadTracker [threadsLocation=" + threadsLocation + ", matrix=" + Arrays.toString(matrix)
            + "]";
   }

}
