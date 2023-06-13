public class PasswordObj {
    String serviceName;
    String userName;
    String password;

    PasswordObj(String serviceName, String userName, String password) {
        this.serviceName = serviceName;
        this.userName = userName;
        this.password = password;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
