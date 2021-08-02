package com.julia.sets.domain.matrix;

import com.julia.sets.presentation.complex.plane.Complex;
import com.julia.sets.presentation.complex.plane.JuliaPicture;

/**
 * This class is meant to populate a matrix where each element in a matrix
 * represents a point in the complex plane and the value of the matrix is
 * determined by {@link JuliaPicture#iterationsToDiverge(Complex)}.
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 */
public class FillMatrix implements Runnable {

   private JuliaPicture jp;

   private MatrixThreadTracker mtt;

   /**
    * constructor for this class with the fields needed to run threads.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param jp
    *           a {@link JuliaPicture} object with the details needed to populate
    *           the matrix
    * @param mtt
    *           a {@link MatrixThreadTracker} object that allows us to populate the
    *           matrix in a way that's thread-safe.
    */
   public FillMatrix(JuliaPicture jp, MatrixThreadTracker mtt) {
      this.jp = jp;
      this.mtt = mtt;
   }

   /**
    * Fills out the matrix with results from
    * {@link JuliaPicture#iterationsToDiverge(Complex)}.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   @Override
   public void run() {
      Boolean keepGoing = true;

      while (Boolean.TRUE.equals(keepGoing)) {
         Integer threadLoc;

         synchronized (mtt) {
            threadLoc = mtt.getNextThreadLocation();
         }

         if (threadLoc >= mtt.size) {
            // finish thread if the matrix is filled
            keepGoing = false;
            continue;
         }

         int column = threadLoc % mtt.columns;
         int row = threadLoc / mtt.columns;

         var jw = jp.getJuliaWindow();
         Double realComponent = jw.getXValues().get(column);
         Double imaginaryComponent = jw.getYValues().get(mtt.rows - row - 1);

         var z = new Complex(realComponent, imaginaryComponent);

         Integer i = jp.iterationsToDiverge(z);

         mtt.matrix[row][column] = i;
      }
   }
}
