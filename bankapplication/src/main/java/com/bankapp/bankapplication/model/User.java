// package com.bankapp.bankapplication.model;

// import java.time.LocalDateTime;

// import org.hibernate.annotations.CreationTimestamp;
// import org.hibernate.annotations.UpdateTimestamp;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Entity
// @Data 
// @Getter 
// @Setter 
// @AllArgsConstructor 
// @NoArgsConstructor 

// public class User {

//     @Id 
//     @GeneratedValue (strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String firstName;
//     private String middleName;
//     private String lastName;

//     private String email;
//     private String phone;

//     @CreationTimestamp 
//     @Column(name="created_at")
//     private LocalDateTime createdAt;
//     @UpdateTimestamp
//     @Column(name="updated_at")
// 	private LocalDateTime updatedAt;
    
    
// }
