import java.net.*;


public class Url {

    private int port;
    private String url;
    private String host;
    private String request;
    private String protocol;
    
    public Url(String url) {
        this.url = url;
        parseUrl();
    }

    /**
     * Exemplo: URL: "http://asc.di.fct.unl.pt/index.html"
     * 
     * Devolve o protocolo indicado no URL : "http"
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Exemplo: URL: "http://asc.di.fct.unl.pt/index.html"
     * 
     * Devolve a máquina/ip indicada no URL : "asc.di.fct.unl.pt"
     */
    public String getHost() {
        return host;
    }

    /**
     * Exemplo: URL: "http://asc.di.fct.unl.pt:1234/index.html"
     * 
     * Devolve o porto indicado no URL ou -1 caso não conste nenhum porto : "1234"
     */
    public int getPort() {
        return port;
    }

    /**
     * Exemplo: URL: "http://asc.di.fct.unl.pt/index.html"
     * 
     * Devolve o porto indicado no URL, caso não conste nenhum porto devolve o valor por defeito passado como argumento : "argumento de entrada"
     */
    public int getPort( int defaultPort) {
        return port < 0 ? defaultPort : port;
    }

    /**
     * Exemplo: URL: "http://asc.di.fct.unl.pt/index.html"
     * 
     * Devolve o pedido correspondente ao URL : "index.html"
     */
    public String getRequest() {
    	return request ;
    }
    
    private void parseUrl() {
        try {
            int i = url.indexOf(':');
            protocol = url.substring(0, i);

            String dummyUrl = "http" + url.substring(i);
            URL x = new URL(dummyUrl);
            host = x.getHost();
            port = x.getPort() ;
            int j = url.substring(i+3).indexOf('/') ;
            request = j < 0 ? "/" : url.substring(i+3).substring(j) ;            
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}
