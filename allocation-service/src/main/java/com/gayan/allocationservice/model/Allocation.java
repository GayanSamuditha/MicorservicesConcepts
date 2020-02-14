package com.gayan.allocationservice.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
public class Allocation {

    /*
    When Hibernate loads objects from the DB, it returns proxied objects which look like your Advertisment or SessionT but have more "stuff" in them (to handle their relationship to the session, the internal state of lazy loaded collections etc.).

    This throws off the Jackson serializer since it relies on introspection to find our the properties of the objects.
     */

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer empId;
    private String startDate;
    private String endDate;
    private String projectCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }
}
