package com.julia.sets.presentation.complex.plane;

/**
 * A class that represents a complex number
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 */
public class Complex {

   private double realComponent;

   private double imaginaryComponent;

   /**
    * Creates a complex number of the form z = a + b<i>i</i>
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param realComponent
    *           the value a in z = a + b<i>i</i>
    * @param imaginaryComponent
    *           the value b in z = a + b<i>i</i>
    */
   public Complex(double realComponent, double imaginaryComponent) {
      this.realComponent = realComponent;
      this.imaginaryComponent = imaginaryComponent;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return the value of the real component for the complex number
    */
   public double getRealComponent() {
      return realComponent;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return the value of the imaginary component of the complex number
    */
   public double getImaginaryComponent() {
      return imaginaryComponent;
   }

   /**
    * Adds the passed in complex number to this complex number. This method does
    * not alter either term in the addition and instead returns a new complex
    * number.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param addend
    *           the complex number to be added to this
    * @return this + addend
    */
   public Complex add(Complex addend) {

      return new Complex(realComponent + addend.realComponent,
            imaginaryComponent + addend.imaginaryComponent);
   }

   /**
    * Applies the function f(z) = <span style="text-decoration:overline">z</span>.
    * This method does not alter the value of this and instead returns a new
    * complex number.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return <span style="text-decoration:overline">z</span> as a new complex
    *         number.
    */
   public Complex bar() {
      return new Complex(realComponent, -imaginaryComponent);
   }

   /**
    * Multiplies the passed in complex number to this complex number. This method
    * does not alter either factor in the multiplication and instead returns a new
    * complex number.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @param other
    *           the complex number to be multiplied to this
    * @return this * other
    */
   public Complex multiply(Complex other) {
      double rc = realComponent * other.realComponent - imaginaryComponent * other.imaginaryComponent;
      double ic = imaginaryComponent * other.realComponent + realComponent * other.imaginaryComponent;
      return new Complex(rc, ic);
   }

   /**
    * Applies the function f(z) = |z|. This method does not alter the value of this
    * and instead returns a new complex number.
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    * @return the value of |z|
    */
   public double modulus() {
      double square = realComponent * realComponent + imaginaryComponent * imaginaryComponent;
      return Math.sqrt(square);
   }

   @Override
   public int hashCode() {
      final var prime = 31;
      var result = 1;
      long temp;
      temp = Double.doubleToLongBits(imaginaryComponent);
      result = prime * result + (int) (temp ^ (temp >>> 32));
      temp = Double.doubleToLongBits(realComponent);
      result = prime * result + (int) (temp ^ (temp >>> 32));
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
      Complex other = (Complex) obj;

      return Double.doubleToLongBits(imaginaryComponent) == Double.doubleToLongBits(other.imaginaryComponent)
            && Double.doubleToLongBits(realComponent) == Double.doubleToLongBits(other.realComponent);
   }

   @Override
   public String toString() {
      return realComponent + " + " + imaginaryComponent + "i";
   }
}
