package com.ofss.config.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name="ResourceConfig.getall",query="select e from ResourceConfig e")
@Table(name="ConfigParam")
public class ResourceConfig implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1914183270139125555L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Resource_Type")
	private String resource_type;
	
	@Column(name="Resource_Value")
	private String resource_value;
	
	@Column(name="Modify_Fields")
	private String modify;

	public ResourceConfig() {
		super();
	}

	public ResourceConfig(String resource_type, String resource_value, String modify) {
		super();
		this.resource_type = resource_type;
		this.resource_value = resource_value;
		this.modify = modify;
	}

	public String getResource_type() {
		return resource_type;
	}

	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}

	public String getResource_value() {
		return resource_value;
	}

	public void setResource_value(String resource_value) {
		this.resource_value = resource_value;
	}

	public String getModify() {
		return modify;
	}

	public void setModify(String modify) {
		this.modify = modify;
	}

}
