
package fanserverinfo.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "doNothing", namespace = "http://fanserverinfo/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "doNothing", namespace = "http://fanserverinfo/", propOrder = {
    "arg0",
    "arg1"
})
public class DoNothing {

    @XmlElement(name = "arg0", namespace = "")
    private fanzone.RemoteResourceInfo arg0;
    @XmlElement(name = "arg1", namespace = "")
    private fanzone.LocalResourceInfo arg1;

    /**
     * 
     * @return
     *     returns RemoteResourceInfo
     */
    public fanzone.RemoteResourceInfo getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(fanzone.RemoteResourceInfo arg0) {
        this.arg0 = arg0;
    }

    /**
     * 
     * @return
     *     returns LocalResourceInfo
     */
    public fanzone.LocalResourceInfo getArg1() {
        return this.arg1;
    }

    /**
     * 
     * @param arg1
     *     the value for the arg1 property
     */
    public void setArg1(fanzone.LocalResourceInfo arg1) {
        this.arg1 = arg1;
    }

}
