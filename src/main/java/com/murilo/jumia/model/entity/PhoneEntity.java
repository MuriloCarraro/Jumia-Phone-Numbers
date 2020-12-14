package com.murilo.jumia.model.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="TBL_PHONES")
public class PhoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID phoneId;

    @NotBlank
    @Column(name="phone_number")
    private String phoneNumber;

    public PhoneEntity(){
    }

    public PhoneEntity(ResultSet rs) throws SQLException {
        this.phoneId = UUID.fromString(rs.getString("phone_id"));
        this.phoneNumber = rs.getString("phone_number");
    }

    public void setPhoneId(UUID phoneId) {
        this.phoneId = phoneId;
    }

    public UUID getPhoneId() {
        return phoneId;
    }

    public void setNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNumber() {
        return phoneNumber.trim();
    }

    @Override
    public String toString() {
        return "Phone{" +
                "phoneId=" + phoneId +
                ", phoneNumber='" + phoneNumber +
                '}';
    }
}
