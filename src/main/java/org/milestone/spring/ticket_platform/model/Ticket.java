package org.milestone.spring.ticket_platform.model;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @NotBlank(message = "Text must not be null or blank")
    private String text;

    @NotNull(message = "Date must not be null")
    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate creationDate;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private TicketCategory category;
    
    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private TicketState state;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User operator;

    @OneToMany(mappedBy = "ticket", orphanRemoval = true)
    private Set<Note> notes;


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

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
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

    public Set<Note> getNotes() {
        return this.notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    @Override
    public String toString(){
        return "{" +
            " id='" + getId() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", title='" + getTitle() + "'" +
            ", text='" + getText() + "'" +
            "}";
    }
}

