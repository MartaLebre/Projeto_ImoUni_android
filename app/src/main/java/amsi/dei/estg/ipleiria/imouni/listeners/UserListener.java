package amsi.dei.estg.ipleiria.imouni.listeners;

public interface UserListener {

    public void onUserRegistado(String response);

    void onRefreshDetalhes(String response);

    void onApagarConta(String response);

    void onValidateLogin(String token, String username);


}
