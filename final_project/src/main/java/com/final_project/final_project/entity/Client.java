package com.final_project.final_project.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table()
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long client_id;

    private String first_name;
    private String last_name;

    public Client() {}

    public Client(Long client_id, String first_name, String last_name) {
        this.client_id = client_id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Long getClient_id() {
        return client_id;
    }
    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return client_id.equals(client.client_id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(client_id);
    }
}
