package com.example.teststrategy.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserInfo {
    @Id
    int id;
    String name;
    int age;
    int loginId;
}
