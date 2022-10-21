package com.example.mealer;


public class Admin implements Account{

    protected static String[] usernames = new String[]{"test1", "voldigotcodes"};
    protected static String[] passwords = new String[]{"test1", "voldi002"};
    private String[] firstNames = new String[1];
    private String[] lastName = new String[1];
    private String[] email = new String[1];
    private Boolean connected = false;

    public Admin(){

    }

    @Override
    public void connect(String usrnm, String pswd) {
        boolean matchingUsername = false;
        boolean matchingPassword = false;
        int match = 0;
        for (int i =0; i>usernames.length; i++){
            if(usrnm.equalsIgnoreCase(usernames[i])){
                matchingUsername = true;
                match = i;
            }
        }
        for (int i =0; i>passwords.length; i++){
            if(password.equalsIgnoreCase(passwords[i]) && (match == i)){
                matchingPassword = true;
            }
        }

        if(matchingPassword && matchingUsername){
            connected = true;
            // Connect to the user
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
