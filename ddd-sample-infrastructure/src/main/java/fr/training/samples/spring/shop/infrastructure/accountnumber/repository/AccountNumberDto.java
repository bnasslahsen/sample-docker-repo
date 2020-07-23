package fr.training.samples.spring.shop.infrastructure.accountnumber.repository;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Account number from the central system.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountNumberDto implements Serializable {

    private static final long serialVersionUID = 8470639021161608761L;

    @JsonProperty
    private String value;

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(final String value) {
        this.value = value;
    }

}
