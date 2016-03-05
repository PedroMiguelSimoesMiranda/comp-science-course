
package clt.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for doNothing complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="doNothing">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://fanserverinfo/}remoteResourceInfo" minOccurs="0"/>
 *         &lt;element name="arg1" type="{http://fanserverinfo/}localResourceInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "doNothing", propOrder = {
    "arg0",
    "arg1"
})
public class DoNothing {

    protected RemoteResourceInfo arg0;
    protected LocalResourceInfo arg1;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteResourceInfo }
     *     
     */
    public RemoteResourceInfo getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteResourceInfo }
     *     
     */
    public void setArg0(RemoteResourceInfo value) {
        this.arg0 = value;
    }

    /**
     * Gets the value of the arg1 property.
     * 
     * @return
     *     possible object is
     *     {@link LocalResourceInfo }
     *     
     */
    public LocalResourceInfo getArg1() {
        return arg1;
    }

    /**
     * Sets the value of the arg1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalResourceInfo }
     *     
     */
    public void setArg1(LocalResourceInfo value) {
        this.arg1 = value;
    }

}
