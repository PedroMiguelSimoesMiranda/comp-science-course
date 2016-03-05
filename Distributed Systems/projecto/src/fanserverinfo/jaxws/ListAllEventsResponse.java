
package fanserverinfo.jaxws;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "listAllEventsResponse", namespace = "http://fanserverinfo/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listAllEventsResponse", namespace = "http://fanserverinfo/")
public class ListAllEventsResponse {

    @XmlElement(name = "return", namespace = "")
    private ArrayList<fanzone.EventInfo> _return;

    /**
     * 
     * @return
     *     returns ArrayList<EventInfo>
     */
    public ArrayList<fanzone.EventInfo> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(ArrayList<fanzone.EventInfo> _return) {
        this._return = _return;
    }

}
