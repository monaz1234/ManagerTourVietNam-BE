package com.ManagerTourVietNam.model.Book;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sequence_generator")
public class SequenceGenerator {

    @Id
    @Column(name = "Name_sequence_generator", length = 50)
    private String seq_name;

    @Column(name = "seq_value_sequence_generator")
    private int seq_value;

    public SequenceGenerator() {
    }

    public SequenceGenerator(String seq_name, int seq_value) {
        this.seq_name = seq_name;
        this.seq_value = seq_value;
    }

    public String getSeq_name() {
        return seq_name;
    }

    public void setSeq_name(String seq_name) {
        this.seq_name = seq_name;
    }

    public int getSeq_value() {
        return seq_value;
    }

    public void setSeq_value(int seq_value) {
        this.seq_value = seq_value;
    }
}