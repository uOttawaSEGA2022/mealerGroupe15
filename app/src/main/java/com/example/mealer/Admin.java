package com.example.mealer;


public class Admin implements Account{

    protected static String[] emails = new String[]{"test1"};
    protected static String[] passwords = new String[]{"test1"};
    private String[] firstNames;
    private String[] lastNames;
    private String[] usernames;
    private Boolean connected;
    private static int id;

    public Admin(){
        id = 0;
        firstNames = new String[1];
        lastNames = new String[1];
        usernames = new String[1];
        connected = false;

    }
    public void setInfo(String email, String password){
        emails[id] = email;
        passwords[id] = password;
    }

    @Override
    public void connect(String email, String pswd) {
        for (int i =0; i>emails.length; i++) {
            if (email.equalsIgnoreCase(emails[i])) {
                id = i;
            }
        }

        if(pswd.equals(passwords[id])) {
            connected = true;
            // Connect to the user
        }else{
            connected = false;
        }

    }

    @Override
    public void disconnect() {
        connected = false;
        // Disconnect the user
    }

    @Override
    public Boolean isConnected() {
        return  connected;
    }

    private void suspend(Cuisinier c){

    }

    private void fetchComplaints(){

    }

    private void validateComplaint(int id){
        // accept or refuse complaints;
    }
}
