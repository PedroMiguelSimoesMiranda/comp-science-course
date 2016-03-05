
package clt.ws;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "FanServerWS", targetNamespace = "http://fanserverinfo/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface FanServerWS {


    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "login", targetNamespace = "http://fanserverinfo/", className = "clt.ws.Login")
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "http://fanserverinfo/", className = "clt.ws.LoginResponse")
    public boolean login(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "createCommunity", targetNamespace = "http://fanserverinfo/", className = "clt.ws.CreateCommunity")
    @ResponseWrapper(localName = "createCommunityResponse", targetNamespace = "http://fanserverinfo/", className = "clt.ws.CreateCommunityResponse")
    public boolean createCommunity(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "joinCommunity", targetNamespace = "http://fanserverinfo/", className = "clt.ws.JoinCommunity")
    @ResponseWrapper(localName = "joinCommunityResponse", targetNamespace = "http://fanserverinfo/", className = "clt.ws.JoinCommunityResponse")
    public boolean joinCommunity(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "leaveCommunity", targetNamespace = "http://fanserverinfo/", className = "clt.ws.LeaveCommunity")
    @ResponseWrapper(localName = "leaveCommunityResponse", targetNamespace = "http://fanserverinfo/", className = "clt.ws.LeaveCommunityResponse")
    public boolean leaveCommunity(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<java.lang.String>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "listCommunities", targetNamespace = "http://fanserverinfo/", className = "clt.ws.ListCommunities")
    @ResponseWrapper(localName = "listCommunitiesResponse", targetNamespace = "http://fanserverinfo/", className = "clt.ws.ListCommunitiesResponse")
    public List<String> listCommunities(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "importEvent", targetNamespace = "http://fanserverinfo/", className = "clt.ws.ImportEvent")
    @ResponseWrapper(localName = "importEventResponse", targetNamespace = "http://fanserverinfo/", className = "clt.ws.ImportEventResponse")
    public boolean importEvent(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        EventClass arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<clt.ws.EventInfo>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "listAllEvents", targetNamespace = "http://fanserverinfo/", className = "clt.ws.ListAllEvents")
    @ResponseWrapper(localName = "listAllEventsResponse", targetNamespace = "http://fanserverinfo/", className = "clt.ws.ListAllEventsResponse")
    public List<EventInfo> listAllEvents(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addEventToCommunity", targetNamespace = "http://fanserverinfo/", className = "clt.ws.AddEventToCommunity")
    @ResponseWrapper(localName = "addEventToCommunityResponse", targetNamespace = "http://fanserverinfo/", className = "clt.ws.AddEventToCommunityResponse")
    public boolean addEventToCommunity(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        EventClass arg2);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.util.List<clt.ws.EventInfo>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "listEventsFromCommunity", targetNamespace = "http://fanserverinfo/", className = "clt.ws.ListEventsFromCommunity")
    @ResponseWrapper(localName = "listEventsFromCommunityResponse", targetNamespace = "http://fanserverinfo/", className = "clt.ws.ListEventsFromCommunityResponse")
    public List<EventInfo> listEventsFromCommunity(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.util.List<clt.ws.EventInfo>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "importEventFromLastFM", targetNamespace = "http://fanserverinfo/", className = "clt.ws.ImportEventFromLastFM")
    @ResponseWrapper(localName = "importEventFromLastFMResponse", targetNamespace = "http://fanserverinfo/", className = "clt.ws.ImportEventFromLastFMResponse")
    public List<EventInfo> importEventFromLastFM(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addResourceToEvent", targetNamespace = "http://fanserverinfo/", className = "clt.ws.AddResourceToEvent")
    @ResponseWrapper(localName = "addResourceToEventResponse", targetNamespace = "http://fanserverinfo/", className = "clt.ws.AddResourceToEventResponse")
    public boolean addResourceToEvent(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        ResourceInfo arg3);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.util.List<java.lang.String>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getPeer", targetNamespace = "http://fanserverinfo/", className = "clt.ws.GetPeer")
    @ResponseWrapper(localName = "getPeerResponse", targetNamespace = "http://fanserverinfo/", className = "clt.ws.GetPeerResponse")
    public List<String> getPeer(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.util.List<clt.ws.EventInfo>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "searchPhotos", targetNamespace = "http://fanserverinfo/", className = "clt.ws.SearchPhotos")
    @ResponseWrapper(localName = "searchPhotosResponse", targetNamespace = "http://fanserverinfo/", className = "clt.ws.SearchPhotosResponse")
    public List<EventInfo> searchPhotos(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2);

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "tellIHaveResource", targetNamespace = "http://fanserverinfo/", className = "clt.ws.TellIHaveResource")
    @ResponseWrapper(localName = "tellIHaveResourceResponse", targetNamespace = "http://fanserverinfo/", className = "clt.ws.TellIHaveResourceResponse")
    public void tellIHaveResource(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "doNothing", targetNamespace = "http://fanserverinfo/", className = "clt.ws.DoNothing")
    @ResponseWrapper(localName = "doNothingResponse", targetNamespace = "http://fanserverinfo/", className = "clt.ws.DoNothingResponse")
    public void doNothing(
        @WebParam(name = "arg0", targetNamespace = "")
        RemoteResourceInfo arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        LocalResourceInfo arg1);

}
