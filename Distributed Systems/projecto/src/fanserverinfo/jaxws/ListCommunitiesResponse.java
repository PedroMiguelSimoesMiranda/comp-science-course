
package fanserverinfo.jaxws;

import java.util.TreeSet;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "listCommunitiesResponse", namespace = "http://fanserverinfo/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listCommunitiesResponse", namespace = "http://fanserverinfo/")
public class ListCommunitiesResponse {

    @XmlElement(name = "return", namespace = "")
    private TreeSet<String> _return;

    /**
     * 
     * @return
     *     returns TreeSet<String>
     */
    public TreeSet<String> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(TreeSet<String> _return) {
        this._return = _return;
    }

}
