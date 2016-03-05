
package fanserverinfo.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "addEventToCommunity", namespace = "http://fanserverinfo/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addEventToCommunity", namespace = "http://fanserverinfo/", propOrder = {
    "arg0",
    "arg1",
    "arg2"
})
public class AddEventToCommunity {

    @XmlElement(name = "arg0", namespace = "")
    private fanzone.User arg0;
    @XmlElement(name = "arg1", namespace = "")
    private String arg1;
    @XmlElement(name = "arg2", namespace = "")
    private fanzone.EventClass arg2;

    /**
     * 
     * @return
     *     returns User
     */
    public fanzone.User getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(fanzone.User arg0) {
        this.arg0 = arg0;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getArg1() {
        return this.arg1;
    }

    /**
     * 
     * @param arg1
     *     the value for the arg1 property
     */
    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    /**
     * 
     * @return
     *     returns EventClass
     */
    public fanzone.EventClass getArg2() {
        return this.arg2;
    }

    /**
     * 
     * @param arg2
     *     the value for the arg2 property
     */
    public void setArg2(fanzone.EventClass arg2) {
        this.arg2 = arg2;
    }

}
