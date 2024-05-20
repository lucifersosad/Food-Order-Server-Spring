//package spring.api.uteating.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class Comment {
//    @Id
//    private String id;
//
//    private String commentDetail;
//
//    private float rating;
//
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name="product_id")
//    private Product product;
//
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name="user_id")
//    private User user;
//}
