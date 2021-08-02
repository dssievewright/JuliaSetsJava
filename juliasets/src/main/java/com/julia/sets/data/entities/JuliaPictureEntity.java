package com.julia.sets.data.entities;

import java.util.SortedMap;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.julia.sets.data.entities.converters.BaseColorsConverter;

/**
 * Entity for the julia_picture table.
 * 
 * @author Dan Sievewright
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
@Entity
@Table(name = "julia_picture")
public class JuliaPictureEntity {

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Column(name = "real_component")
   private double realComponent;

   @Column(name = "imaginary_component")
   private double imaginaryComponent;

   @Column(name = "min_x_value")
   private double minXValue;

   @Column(name = "max_x_value")
   private double maxXValue;

   @Column(name = "picture_width")
   private int pictureWidth;

   @Column(name = "min_y_value")
   private double minYValue;

   @Column(name = "max_y_value")
   private double maxYValue;

   @Column(name = "picture_height")
   private int pictureHeight;

   @Column(name = "iterations")
   private int iterations;

   @Column(name = "max_modulus")
   private double maxModulus;

   @Column(name = "interior_color")
   private int interiorColor;

   @Column(name = "base_colors", columnDefinition = "json")
   @Convert(converter = BaseColorsConverter.class)
   private SortedMap<Integer, Integer> baseColors;

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @return the id
    */
   public Integer getId() {
      return id;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @param id
    *           the id to set
    */
   public void setId(Integer id) {
      this.id = id;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @return the realComponent
    */
   public double getRealComponent() {
      return realComponent;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @param realComponent
    *           the realComponent to set
    */
   public void setRealComponent(double realComponent) {
      this.realComponent = realComponent;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @return the imaginaryComponent
    */
   public double getImaginaryComponent() {
      return imaginaryComponent;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @param imaginaryComponent
    *           the imaginaryComponent to set
    */
   public void setImaginaryComponent(double imaginaryComponent) {
      this.imaginaryComponent = imaginaryComponent;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @return the minXValue
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
    *           the minXValue to set
    */
   public void setMinXValue(double minXValue) {
      this.minXValue = minXValue;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @return the maxXValue
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
    *           the maxXValue to set
    */
   public void setMaxXValue(double maxXValue) {
      this.maxXValue = maxXValue;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @return the pictureWidth
    */
   public int getPictureWidth() {
      return pictureWidth;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @param pictureWidth
    *           the pictureWidth to set
    */
   public void setPictureWidth(int pictureWidth) {
      this.pictureWidth = pictureWidth;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @return the minYValue
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
    *           the minYValue to set
    */
   public void setMinYValue(double minYValue) {
      this.minYValue = minYValue;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @return the maxYValue
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
    *           the maxYValue to set
    */
   public void setMaxYValue(double maxYValue) {
      this.maxYValue = maxYValue;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @return the pictureHeight
    */
   public int getPictureHeight() {
      return pictureHeight;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @param pictureHeight
    *           the pictureHeight to set
    */
   public void setPictureHeight(int pictureHeight) {
      this.pictureHeight = pictureHeight;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @return the iterations
    */
   public int getIterations() {
      return iterations;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @param iterations
    *           the iterations to set
    */
   public void setIterations(int iterations) {
      this.iterations = iterations;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @return the maxModulus
    */
   public double getMaxModulus() {
      return maxModulus;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @param maxModulus
    *           the maxModulus to set
    */
   public void setMaxModulus(double maxModulus) {
      this.maxModulus = maxModulus;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @return the interiorColor
    */
   public int getInteriorColor() {
      return interiorColor;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @param interiorColor
    *           the interiorColor to set
    */
   public void setInteriorColor(int interiorColor) {
      this.interiorColor = interiorColor;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @return the baseColors
    */
   public SortedMap<Integer, Integer> getBaseColors() {
      return baseColors;
   }

   /**
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    * 
    * @param baseColors
    *           the baseColors to set
    */
   public void setBaseColors(SortedMap<Integer, Integer> baseColors) {
      this.baseColors = baseColors;
   }

   /**
    * Empty constructor for entity
    * 
    * @author Dan Sievewright
    * @version 1.0.0
    * @since 1.0.0
    *
    */
   public JuliaPictureEntity() {
      super();
   }

}
