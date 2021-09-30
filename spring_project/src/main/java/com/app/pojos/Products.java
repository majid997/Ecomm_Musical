package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="my_products")
public class Products extends BaseEntity {

  @Column(length = 30)
  private String tiltle;
	
  @Column(length = 30)
  private String description;
  private int unitprice;
  @Column(length = 30)
  private String status;
  
}
