package com.example.donatekaro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "sub_category")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class SubCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subCategoryId;

    @Column(nullable = false)
    private String subCategoryName;


    @JoinColumn(name = "category_id", referencedColumnName = "categoryId")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Category categoryId;



    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subCategoryId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Product> productList;


    private Boolean isDeleted=false;




    @PrePersist
    protected void prePersist() {
        if (this.createdAt == null) createdAt = new Date();
        if (this.updatedAt == null) updatedAt = new Date();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = new Date();
    }

}
