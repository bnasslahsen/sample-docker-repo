package fr.training.samples.spring.shop.domain.customer.entity;

import javax.validation.Valid;

import fr.training.samples.spring.shop.domain.common.entity.AbstractBaseEntity;
import fr.training.samples.spring.shop.domain.customer.CustomerVO;

/**
 * @author bnasslahsen
 *
 */
public class CustomerEntity extends AbstractBaseEntity {

    /**
     * customerVO of type CustomerVO
     */
    @Valid
    private CustomerVO customerVO;

    /**
     * 
     */
    public CustomerEntity() {
        super();
    }

    /**
     * @param customerVO
     */
    public CustomerEntity(final CustomerVO customerVO) {
        this.customerVO = customerVO;
    }

    /**
     * @return
     */
    public CustomerVO getCustomerVO() {
        return customerVO;
    }

    /**
     * @param customerVO
     */
    public void setCustomerVO(final CustomerVO customerVO) {
        this.customerVO = customerVO;
    }

    /*
     * (non-Javadoc)
     * @see fr.training.samples.spring.shop.common.AbstractBaseEntity#toString()
     */
    @Override
    public String toString() {
        return "CustomerEntity [customerVO=" + customerVO + ", toString()=" + super.toString() + "]";
    }


}