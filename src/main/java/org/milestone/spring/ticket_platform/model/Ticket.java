package org.milestone.spring.ticket_platform.model;

import java.time.LocalDate;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "tickets")
public class Ticket {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Title must not be null or blank")
    private String title;

    @Lob
    @NotBlank(message = "ISBN must not be null or blank")
    private String description;

    @NotNull(message = "Date must not be null")
    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate createdAt;

    @NotNull(message = "Date must not be null")
    private LocalDate updatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private TicketCategory category;
    
    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private TicketState state;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User operator;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public TicketCategory getCategory() {
        return this.category;
    }

    public void setCategory(TicketCategory category) {
        this.category = category;
    }

    public TicketState getState() {
        return this.state;
    }

    public void setState(TicketState state) {
        this.state = state;
    }

    public User getOperator() {
        return this.operator;
    }

    public void setOperator(User operator) {
        this.operator = operator;
    }

    @Override
    public String toString(){
        return "{" +
            " id='" + getId() + "'" +
            ", creationDate='" + getCreatedAt() + "'" +
            ", title='" + getTitle() + "'" +
            ", text='" + getDescription() + "'" +
            "}";
    }
}

