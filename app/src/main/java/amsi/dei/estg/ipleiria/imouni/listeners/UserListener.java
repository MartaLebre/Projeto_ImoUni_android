package amsi.dei.estg.ipleiria.imouni.listeners;

public interface UserListener {
    public void onUserRegistado();

    public void onDatePickerSelected(String data);

    void onValidateLogin(String username, String password);


}
