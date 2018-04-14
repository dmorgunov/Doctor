
package com.company.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AppointmentData" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="Day" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                 &lt;attribute name="Visitor" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                 &lt;attribute name="Comment" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Exp" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "appointmentData"
})
@XmlRootElement(name = "DoctorData")
public class DoctorData {

    @XmlElement(name = "AppointmentData", required = true)
    protected List<DoctorData.AppointmentData> appointmentData;
    @XmlAttribute(name = "Name", required = true)
    protected String name;
    @XmlAttribute(name = "Exp", required = true)
    protected int exp;

    /**
     * Gets the value of the appointmentData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the appointmentData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAppointmentData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DoctorData.AppointmentData }
     * 
     * 
     */
    public List<DoctorData.AppointmentData> getAppointmentData() {
        if (appointmentData == null) {
            appointmentData = new ArrayList<DoctorData.AppointmentData>();
        }
        return this.appointmentData;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the exp property.
     * 
     */
    public int getExp() {
        return exp;
    }

    /**
     * Sets the value of the exp property.
     * 
     */
    public void setExp(int value) {
        this.exp = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="Day" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
     *       &lt;attribute name="Visitor" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
     *       &lt;attribute name="Comment" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class AppointmentData {

        @XmlAttribute(name = "Day", required = true)
        protected int day;
        @XmlAttribute(name = "Visitor", required = true)
        protected int visitor;
        @XmlAttribute(name = "Comment", required = true)
        protected String comment;

        /**
         * Gets the value of the day property.
         * 
         */
        public int getDay() {
            return day;
        }

        /**
         * Sets the value of the day property.
         * 
         */
        public void setDay(int value) {
            this.day = value;
        }

        /**
         * Gets the value of the visitor property.
         * 
         */
        public int getVisitor() {
            return visitor;
        }

        /**
         * Sets the value of the visitor property.
         * 
         */
        public void setVisitor(int value) {
            this.visitor = value;
        }

        /**
         * Gets the value of the comment property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getComment() {
            return comment;
        }

        /**
         * Sets the value of the comment property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setComment(String value) {
            this.comment = value;
        }

    }

}
