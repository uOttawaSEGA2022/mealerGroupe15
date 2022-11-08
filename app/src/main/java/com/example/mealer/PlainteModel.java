package com.example.mealer;

public class PlainteModel {

        private String nameOfClient;
        private String nameOfCuisinier;
        private String Description;
        public PlainteModel(String nameOfClient, String nameOfCuisinier, String Description){
            this.nameOfClient=nameOfClient;
            this.nameOfCuisinier=nameOfCuisinier;
            this.Description=Description;
        }



        public String getNameOfClient() {
            return nameOfClient;
        }

        public String getNameOfCuisinier() {
            return nameOfCuisinier;
        }

        public String getDescription() {
            return Description;
        }

        public void setNameOfClient(String nameOfClient) {
            this.nameOfClient = nameOfClient;
        }

        public void setNameOfCuisinier(String nameOfCuisinier) {
            this.nameOfCuisinier = nameOfCuisinier;
        }

        public void setDescription(String description) {
            Description = description;
        }
    }



