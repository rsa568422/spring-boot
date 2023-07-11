package udemy.springboot.datajpa.app.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@AllArgsConstructor
@Table(name = "invoices")
public class Invoice implements Serializable {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @NotEmpty
    private String description;

    @Getter
    private String observations;

    @Getter
    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Customer customer;

    @Getter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "invoice_id")
    private List<InvoiceItem> items;

    public Invoice() {
        items = new ArrayList<>();
    }

    @PrePersist
    public void prePersist() {
        createAt = new Date();
    }

    public void addItem(InvoiceItem item) {
        items.add(item);
    }

    public BigDecimal getTotal() {
        return items.stream()
                .map(InvoiceItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @XmlTransient
    public Customer getCustomer() {
        return customer;
    }
}
