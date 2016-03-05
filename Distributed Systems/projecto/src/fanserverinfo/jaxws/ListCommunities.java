
package fanserverinfo.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "listCommunities", namespace = "http://fanserverinfo/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listCommunities", namespace = "http://fanserverinfo/")
public class ListCommunities {

    @XmlElement(name = "arg0", namespace = "")
    private fanzone.User arg0;

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

}
