
package clt.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the clt.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ImportEventFromLastFMResponse_QNAME = new QName("http://fanserverinfo/", "importEventFromLastFMResponse");
    private final static QName _Login_QNAME = new QName("http://fanserverinfo/", "login");
    private final static QName _CreateCommunityResponse_QNAME = new QName("http://fanserverinfo/", "createCommunityResponse");
    private final static QName _ListCommunities_QNAME = new QName("http://fanserverinfo/", "listCommunities");
    private final static QName _GetPeerResponse_QNAME = new QName("http://fanserverinfo/", "getPeerResponse");
    private final static QName _TellIHaveResource_QNAME = new QName("http://fanserverinfo/", "tellIHaveResource");
    private final static QName _ImportEventFromLastFM_QNAME = new QName("http://fanserverinfo/", "importEventFromLastFM");
    private final static QName _CreateCommunity_QNAME = new QName("http://fanserverinfo/", "createCommunity");
    private final static QName _DoNothingResponse_QNAME = new QName("http://fanserverinfo/", "doNothingResponse");
    private final static QName _AddEventToCommunityResponse_QNAME = new QName("http://fanserverinfo/", "addEventToCommunityResponse");
    private final static QName _SearchPhotos_QNAME = new QName("http://fanserverinfo/", "searchPhotos");
    private final static QName _SearchPhotosResponse_QNAME = new QName("http://fanserverinfo/", "searchPhotosResponse");
    private final static QName _TellIHaveResourceResponse_QNAME = new QName("http://fanserverinfo/", "tellIHaveResourceResponse");
    private final static QName _DoNothing_QNAME = new QName("http://fanserverinfo/", "doNothing");
    private final static QName _ListEventsFromCommunityResponse_QNAME = new QName("http://fanserverinfo/", "listEventsFromCommunityResponse");
    private final static QName _ListAllEventsResponse_QNAME = new QName("http://fanserverinfo/", "listAllEventsResponse");
    private final static QName _ImportEvent_QNAME = new QName("http://fanserverinfo/", "importEvent");
    private final static QName _JoinCommunityResponse_QNAME = new QName("http://fanserverinfo/", "joinCommunityResponse");
    private final static QName _ListAllEvents_QNAME = new QName("http://fanserverinfo/", "listAllEvents");
    private final static QName _AddEventToCommunity_QNAME = new QName("http://fanserverinfo/", "addEventToCommunity");
    private final static QName _JoinCommunity_QNAME = new QName("http://fanserverinfo/", "joinCommunity");
    private final static QName _ImportEventResponse_QNAME = new QName("http://fanserverinfo/", "importEventResponse");
    private final static QName _LeaveCommunity_QNAME = new QName("http://fanserverinfo/", "leaveCommunity");
    private final static QName _LeaveCommunityResponse_QNAME = new QName("http://fanserverinfo/", "leaveCommunityResponse");
    private final static QName _ListCommunitiesResponse_QNAME = new QName("http://fanserverinfo/", "listCommunitiesResponse");
    private final static QName _ListEventsFromCommunity_QNAME = new QName("http://fanserverinfo/", "listEventsFromCommunity");
    private final static QName _AddResourceToEvent_QNAME = new QName("http://fanserverinfo/", "addResourceToEvent");
    private final static QName _GetPeer_QNAME = new QName("http://fanserverinfo/", "getPeer");
    private final static QName _AddResourceToEventResponse_QNAME = new QName("http://fanserverinfo/", "addResourceToEventResponse");
    private final static QName _LoginResponse_QNAME = new QName("http://fanserverinfo/", "loginResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: clt.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddEventToCommunityResponse }
     * 
     */
    public AddEventToCommunityResponse createAddEventToCommunityResponse() {
        return new AddEventToCommunityResponse();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link GetPeer }
     * 
     */
    public GetPeer createGetPeer() {
        return new GetPeer();
    }

    /**
     * Create an instance of {@link AddResourceToEventResponse }
     * 
     */
    public AddResourceToEventResponse createAddResourceToEventResponse() {
        return new AddResourceToEventResponse();
    }

    /**
     * Create an instance of {@link RemoteResourceInfo }
     * 
     */
    public RemoteResourceInfo createRemoteResourceInfo() {
        return new RemoteResourceInfo();
    }

    /**
     * Create an instance of {@link ListCommunities }
     * 
     */
    public ListCommunities createListCommunities() {
        return new ListCommunities();
    }

    /**
     * Create an instance of {@link DoNothing }
     * 
     */
    public DoNothing createDoNothing() {
        return new DoNothing();
    }

    /**
     * Create an instance of {@link ImportEventFromLastFMResponse }
     * 
     */
    public ImportEventFromLastFMResponse createImportEventFromLastFMResponse() {
        return new ImportEventFromLastFMResponse();
    }

    /**
     * Create an instance of {@link CreateCommunity }
     * 
     */
    public CreateCommunity createCreateCommunity() {
        return new CreateCommunity();
    }

    /**
     * Create an instance of {@link ListAllEventsResponse }
     * 
     */
    public ListAllEventsResponse createListAllEventsResponse() {
        return new ListAllEventsResponse();
    }

    /**
     * Create an instance of {@link ImportEventResponse }
     * 
     */
    public ImportEventResponse createImportEventResponse() {
        return new ImportEventResponse();
    }

    /**
     * Create an instance of {@link GetPeerResponse }
     * 
     */
    public GetPeerResponse createGetPeerResponse() {
        return new GetPeerResponse();
    }

    /**
     * Create an instance of {@link AddEventToCommunity }
     * 
     */
    public AddEventToCommunity createAddEventToCommunity() {
        return new AddEventToCommunity();
    }

    /**
     * Create an instance of {@link LocalResourceInfo }
     * 
     */
    public LocalResourceInfo createLocalResourceInfo() {
        return new LocalResourceInfo();
    }

    /**
     * Create an instance of {@link JoinCommunity }
     * 
     */
    public JoinCommunity createJoinCommunity() {
        return new JoinCommunity();
    }

    /**
     * Create an instance of {@link ImportEventFromLastFM }
     * 
     */
    public ImportEventFromLastFM createImportEventFromLastFM() {
        return new ImportEventFromLastFM();
    }

    /**
     * Create an instance of {@link ListEventsFromCommunityResponse }
     * 
     */
    public ListEventsFromCommunityResponse createListEventsFromCommunityResponse() {
        return new ListEventsFromCommunityResponse();
    }

    /**
     * Create an instance of {@link JoinCommunityResponse }
     * 
     */
    public JoinCommunityResponse createJoinCommunityResponse() {
        return new JoinCommunityResponse();
    }

    /**
     * Create an instance of {@link SearchPhotosResponse }
     * 
     */
    public SearchPhotosResponse createSearchPhotosResponse() {
        return new SearchPhotosResponse();
    }

    /**
     * Create an instance of {@link ListCommunitiesResponse }
     * 
     */
    public ListCommunitiesResponse createListCommunitiesResponse() {
        return new ListCommunitiesResponse();
    }

    /**
     * Create an instance of {@link TellIHaveResourceResponse }
     * 
     */
    public TellIHaveResourceResponse createTellIHaveResourceResponse() {
        return new TellIHaveResourceResponse();
    }

    /**
     * Create an instance of {@link CreateCommunityResponse }
     * 
     */
    public CreateCommunityResponse createCreateCommunityResponse() {
        return new CreateCommunityResponse();
    }

    /**
     * Create an instance of {@link ListAllEvents }
     * 
     */
    public ListAllEvents createListAllEvents() {
        return new ListAllEvents();
    }

    /**
     * Create an instance of {@link LeaveCommunityResponse }
     * 
     */
    public LeaveCommunityResponse createLeaveCommunityResponse() {
        return new LeaveCommunityResponse();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link SearchPhotos }
     * 
     */
    public SearchPhotos createSearchPhotos() {
        return new SearchPhotos();
    }

    /**
     * Create an instance of {@link ImportEvent }
     * 
     */
    public ImportEvent createImportEvent() {
        return new ImportEvent();
    }

    /**
     * Create an instance of {@link DoNothingResponse }
     * 
     */
    public DoNothingResponse createDoNothingResponse() {
        return new DoNothingResponse();
    }

    /**
     * Create an instance of {@link ListEventsFromCommunity }
     * 
     */
    public ListEventsFromCommunity createListEventsFromCommunity() {
        return new ListEventsFromCommunity();
    }

    /**
     * Create an instance of {@link EventClass }
     * 
     */
    public EventClass createEventClass() {
        return new EventClass();
    }

    /**
     * Create an instance of {@link LeaveCommunity }
     * 
     */
    public LeaveCommunity createLeaveCommunity() {
        return new LeaveCommunity();
    }

    /**
     * Create an instance of {@link AddResourceToEvent }
     * 
     */
    public AddResourceToEvent createAddResourceToEvent() {
        return new AddResourceToEvent();
    }

    /**
     * Create an instance of {@link TellIHaveResource }
     * 
     */
    public TellIHaveResource createTellIHaveResource() {
        return new TellIHaveResource();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImportEventFromLastFMResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "importEventFromLastFMResponse")
    public JAXBElement<ImportEventFromLastFMResponse> createImportEventFromLastFMResponse(ImportEventFromLastFMResponse value) {
        return new JAXBElement<ImportEventFromLastFMResponse>(_ImportEventFromLastFMResponse_QNAME, ImportEventFromLastFMResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateCommunityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "createCommunityResponse")
    public JAXBElement<CreateCommunityResponse> createCreateCommunityResponse(CreateCommunityResponse value) {
        return new JAXBElement<CreateCommunityResponse>(_CreateCommunityResponse_QNAME, CreateCommunityResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListCommunities }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "listCommunities")
    public JAXBElement<ListCommunities> createListCommunities(ListCommunities value) {
        return new JAXBElement<ListCommunities>(_ListCommunities_QNAME, ListCommunities.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPeerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "getPeerResponse")
    public JAXBElement<GetPeerResponse> createGetPeerResponse(GetPeerResponse value) {
        return new JAXBElement<GetPeerResponse>(_GetPeerResponse_QNAME, GetPeerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TellIHaveResource }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "tellIHaveResource")
    public JAXBElement<TellIHaveResource> createTellIHaveResource(TellIHaveResource value) {
        return new JAXBElement<TellIHaveResource>(_TellIHaveResource_QNAME, TellIHaveResource.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImportEventFromLastFM }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "importEventFromLastFM")
    public JAXBElement<ImportEventFromLastFM> createImportEventFromLastFM(ImportEventFromLastFM value) {
        return new JAXBElement<ImportEventFromLastFM>(_ImportEventFromLastFM_QNAME, ImportEventFromLastFM.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateCommunity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "createCommunity")
    public JAXBElement<CreateCommunity> createCreateCommunity(CreateCommunity value) {
        return new JAXBElement<CreateCommunity>(_CreateCommunity_QNAME, CreateCommunity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoNothingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "doNothingResponse")
    public JAXBElement<DoNothingResponse> createDoNothingResponse(DoNothingResponse value) {
        return new JAXBElement<DoNothingResponse>(_DoNothingResponse_QNAME, DoNothingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddEventToCommunityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "addEventToCommunityResponse")
    public JAXBElement<AddEventToCommunityResponse> createAddEventToCommunityResponse(AddEventToCommunityResponse value) {
        return new JAXBElement<AddEventToCommunityResponse>(_AddEventToCommunityResponse_QNAME, AddEventToCommunityResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchPhotos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "searchPhotos")
    public JAXBElement<SearchPhotos> createSearchPhotos(SearchPhotos value) {
        return new JAXBElement<SearchPhotos>(_SearchPhotos_QNAME, SearchPhotos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchPhotosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "searchPhotosResponse")
    public JAXBElement<SearchPhotosResponse> createSearchPhotosResponse(SearchPhotosResponse value) {
        return new JAXBElement<SearchPhotosResponse>(_SearchPhotosResponse_QNAME, SearchPhotosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TellIHaveResourceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "tellIHaveResourceResponse")
    public JAXBElement<TellIHaveResourceResponse> createTellIHaveResourceResponse(TellIHaveResourceResponse value) {
        return new JAXBElement<TellIHaveResourceResponse>(_TellIHaveResourceResponse_QNAME, TellIHaveResourceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoNothing }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "doNothing")
    public JAXBElement<DoNothing> createDoNothing(DoNothing value) {
        return new JAXBElement<DoNothing>(_DoNothing_QNAME, DoNothing.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListEventsFromCommunityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "listEventsFromCommunityResponse")
    public JAXBElement<ListEventsFromCommunityResponse> createListEventsFromCommunityResponse(ListEventsFromCommunityResponse value) {
        return new JAXBElement<ListEventsFromCommunityResponse>(_ListEventsFromCommunityResponse_QNAME, ListEventsFromCommunityResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListAllEventsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "listAllEventsResponse")
    public JAXBElement<ListAllEventsResponse> createListAllEventsResponse(ListAllEventsResponse value) {
        return new JAXBElement<ListAllEventsResponse>(_ListAllEventsResponse_QNAME, ListAllEventsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImportEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "importEvent")
    public JAXBElement<ImportEvent> createImportEvent(ImportEvent value) {
        return new JAXBElement<ImportEvent>(_ImportEvent_QNAME, ImportEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JoinCommunityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "joinCommunityResponse")
    public JAXBElement<JoinCommunityResponse> createJoinCommunityResponse(JoinCommunityResponse value) {
        return new JAXBElement<JoinCommunityResponse>(_JoinCommunityResponse_QNAME, JoinCommunityResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListAllEvents }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "listAllEvents")
    public JAXBElement<ListAllEvents> createListAllEvents(ListAllEvents value) {
        return new JAXBElement<ListAllEvents>(_ListAllEvents_QNAME, ListAllEvents.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddEventToCommunity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "addEventToCommunity")
    public JAXBElement<AddEventToCommunity> createAddEventToCommunity(AddEventToCommunity value) {
        return new JAXBElement<AddEventToCommunity>(_AddEventToCommunity_QNAME, AddEventToCommunity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JoinCommunity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "joinCommunity")
    public JAXBElement<JoinCommunity> createJoinCommunity(JoinCommunity value) {
        return new JAXBElement<JoinCommunity>(_JoinCommunity_QNAME, JoinCommunity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImportEventResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "importEventResponse")
    public JAXBElement<ImportEventResponse> createImportEventResponse(ImportEventResponse value) {
        return new JAXBElement<ImportEventResponse>(_ImportEventResponse_QNAME, ImportEventResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LeaveCommunity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "leaveCommunity")
    public JAXBElement<LeaveCommunity> createLeaveCommunity(LeaveCommunity value) {
        return new JAXBElement<LeaveCommunity>(_LeaveCommunity_QNAME, LeaveCommunity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LeaveCommunityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "leaveCommunityResponse")
    public JAXBElement<LeaveCommunityResponse> createLeaveCommunityResponse(LeaveCommunityResponse value) {
        return new JAXBElement<LeaveCommunityResponse>(_LeaveCommunityResponse_QNAME, LeaveCommunityResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListCommunitiesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "listCommunitiesResponse")
    public JAXBElement<ListCommunitiesResponse> createListCommunitiesResponse(ListCommunitiesResponse value) {
        return new JAXBElement<ListCommunitiesResponse>(_ListCommunitiesResponse_QNAME, ListCommunitiesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListEventsFromCommunity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "listEventsFromCommunity")
    public JAXBElement<ListEventsFromCommunity> createListEventsFromCommunity(ListEventsFromCommunity value) {
        return new JAXBElement<ListEventsFromCommunity>(_ListEventsFromCommunity_QNAME, ListEventsFromCommunity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddResourceToEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "addResourceToEvent")
    public JAXBElement<AddResourceToEvent> createAddResourceToEvent(AddResourceToEvent value) {
        return new JAXBElement<AddResourceToEvent>(_AddResourceToEvent_QNAME, AddResourceToEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPeer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "getPeer")
    public JAXBElement<GetPeer> createGetPeer(GetPeer value) {
        return new JAXBElement<GetPeer>(_GetPeer_QNAME, GetPeer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddResourceToEventResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "addResourceToEventResponse")
    public JAXBElement<AddResourceToEventResponse> createAddResourceToEventResponse(AddResourceToEventResponse value) {
        return new JAXBElement<AddResourceToEventResponse>(_AddResourceToEventResponse_QNAME, AddResourceToEventResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fanserverinfo/", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

}
