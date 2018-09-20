package no.marcello.example.persister.model;

import javax.persistence.*;

@Entity
public class Data {
    @Id
    @Column(name = "DATA_ID")
    @TableGenerator(name = "DATA_GEN",
            table = "DATA_SEQ",
            pkColumnName = "SEQ_NAME",
            valueColumnName = "SEQ_NUMBER",
            pkColumnValue = "DATA_SEQ",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DATA_GEN")
    private Long id;

    @Column(name = "DATA_VALUE", nullable = false)
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
