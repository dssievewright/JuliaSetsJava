package com.julia.sets.domain.matrix;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.julia.sets.config.ApplicationProperties;
import com.julia.sets.domain.services.ImageFileServiceException;
import com.julia.sets.presentation.complex.plane.JuliaPicture;

/**
 * Matrix calculator to do the mathematics before being converted to colors.
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
@Component
public class JuliaSetMatrixCalculator {

   @Autowired
   private ApplicationProperties props;

   /**
    * Empty constructor to create the bean
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    */
   JuliaSetMatrixCalculator() {
      super();
   }

   /**
    * This method creates, sets up the threads, runs them, and returns the matrix
    * computed.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param jp
    *           {@link JuliaPicture} for the image we want to create
    * @return the matrix with the values all determined
    * @throws InterruptedException
    *            if a thread is interrupted
    * @throws ImageFileServiceException
    *            if a bad {@link JuliaWindow} is used for the picture or the
    *            threads ran too long and were cancelled.
    */
   public Integer[][] fillMatrix(JuliaPicture jp) throws InterruptedException, ImageFileServiceException {

      // create the matrix thread tracker
      var jw = jp.getJuliaWindow();
      var mtt = new MatrixThreadTracker(jw);

      // set up thread pool
      var executorService = Executors.newFixedThreadPool(props.getMaxThreads());
      executorService.execute(new FillMatrix(jp, mtt));
      executorService.shutdown();

      // wait for finish and throw exception if timeout elapsed before termination
      if (!executorService.awaitTermination(props.getMaxWaitTime(), TimeUnit.SECONDS)) {
         var msg = new StringBuilder("Threads took too long and were terminated.");
         msg.append(" Please consider adjusting input parameters.");

         throw new ImageThreadTimeoutException(msg.toString());
      }

      // return the answer
      return mtt.matrix;
   }
}
