package com.julia.sets.presentation.controllers;

import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import com.julia.sets.domain.matrix.ImageThreadTimeoutException;

/**
 * Handler for exceptions
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
@Controller
public class ExceptionHandlingController extends AbstractHandlerExceptionResolver {

   private static final String EXCEPTION = "exception";

   private static final String ERROR_MESSAGE = "errorMessage";

   /**
    * Initialize Handler as first exception handler
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    */
   public ExceptionHandlingController() {
      // have this come before any other exception handler resolvers
      setOrder(LOWEST_PRECEDENCE - 1);
   }

   /**
    * Handler for exceptions arising in this app.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    */
   @Override
   protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
         Object handler, Exception ex) {
      logger.error("error thrown to controller = " + ex.getMessage(), ex);

      if (ex instanceof ImageThreadTimeoutException) {
         return handleImageThreadTimeout(ex);
      } else if (ex instanceof FileNotFoundException) {
         return handleFileNotFound(ex);
      } else {
         return handleOthers(ex);
      }
   }

   /**
    * Handler for {@link ImageThreadTimeoutException}s
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param ex the exception
    * @return {@link ModelAndView} for the front end
    */
   private ModelAndView handleImageThreadTimeout(Exception ex) {
      var mav = new ModelAndView(EXCEPTION);
      mav.setStatus(HttpStatus.REQUEST_TIMEOUT);
      mav.addObject(ERROR_MESSAGE, ex.getMessage());
      return mav;
   }

   /**
    * Handler for {@link FileNotFoundException}s
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param ex the exception
    * @return {@link ModelAndView} for the front end
    */
   private ModelAndView handleFileNotFound(Exception ex) {
      var mav = new ModelAndView(EXCEPTION);
      mav.setStatus(HttpStatus.NOT_FOUND);
      mav.addObject(ERROR_MESSAGE, ex.getMessage());
      return mav;
   }

   /**
    * Handler for all other {@link Exception}s
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param ex the exception
    * @return {@link ModelAndView} for the front end
    */
   private ModelAndView handleOthers(Exception ex) {
      var mav = new ModelAndView(EXCEPTION);
      mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      var errorMessage = new StringBuilder("Critical Server Error: ");
      if (ex.getMessage() == null || ex.getMessage().isBlank()) {
         errorMessage.append("null");
      }
      errorMessage.append(ex.getMessage());
      mav.addObject(ERROR_MESSAGE, errorMessage);
      return mav;
   }

}
