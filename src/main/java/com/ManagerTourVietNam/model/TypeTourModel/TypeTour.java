package com.ManagerTourVietNam.model.TypeTourModel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name="type_tour")
public class TypeTour {
    @Id
    private String idtour_type;
    private String name_type;

    public String getIdtour_type() {
        return idtour_type;
    }

    public void setIdtour_type(String idtour_type) {
        this.idtour_type = idtour_type;
    }

    public String getName_type() {
        return name_type;
    }

    public void setName_type(String name_type) {
        this.name_type = name_type;
    }

    public TypeTour(String idtour_type, String name_type) {
        this.idtour_type = idtour_type;
        this.name_type = name_type;
    }

    public TypeTour(){

    }

}
