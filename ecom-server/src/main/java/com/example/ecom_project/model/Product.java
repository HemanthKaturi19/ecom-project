package com.example.ecom_project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String name;
    public String description;
    public String brand;
    public BigDecimal price;
    public String category;
  //  @JsonFormat(shape =JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
    public Date releaseDate;
    public boolean productAvailable;
    public int stockQuantity;
    public String imageName;
    public String imageType;
    @Lob
    public byte[] imageData;
    public void setImageName(String name){
        this.imageName=name;
    }
    public void setImageType(String type){
        this.imageType=type;
    }
    public void setImageData(byte[] data){
        this.imageData=data;
    }
    public byte[] getImageData(){
        return imageData;
    }
    public String getImageType(){
        return imageType;
    }
}
