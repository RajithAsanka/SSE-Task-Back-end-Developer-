//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.12 at 01:21:48 PM IST 
//


package com.task.schemas.corebank;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="fundTransferType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="fromAccountNO" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="toAccountNO" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fundTransferType",
    "userId",
    "fromAccountNO",
    "toAccountNO",
    "amount"
})
@XmlRootElement(name = "fundTransferRequest")
public class FundTransferRequest {

    @XmlElement(required = true)
    protected String fundTransferType;
    @XmlElement(required = true)
    protected String userId;
    @XmlElement(required = true)
    protected String fromAccountNO;
    @XmlElement(required = true)
    protected String toAccountNO;
    @XmlElement(required = true)
    protected BigDecimal amount;

    /**
     * Gets the value of the fundTransferType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundTransferType() {
        return fundTransferType;
    }

    /**
     * Sets the value of the fundTransferType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundTransferType(String value) {
        this.fundTransferType = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
    }

    /**
     * Gets the value of the fromAccountNO property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromAccountNO() {
        return fromAccountNO;
    }

    /**
     * Sets the value of the fromAccountNO property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromAccountNO(String value) {
        this.fromAccountNO = value;
    }

    /**
     * Gets the value of the toAccountNO property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToAccountNO() {
        return toAccountNO;
    }

    /**
     * Sets the value of the toAccountNO property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToAccountNO(String value) {
        this.toAccountNO = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

}
