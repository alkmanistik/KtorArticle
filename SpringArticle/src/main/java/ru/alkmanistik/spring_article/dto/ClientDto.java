package ru.alkmanistik.spring_article.dto;

import ru.alkmanistik.spring_article.model.Client;

public class ClientDto {

    public static class ClientResponse {
        private Integer id;
        private String name;

        public ClientResponse() {}

        public ClientResponse(Client client) {
            this.id = client.getId();
            this.name = client.getName();
        }

        public ClientResponse(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class CreateClientRequest {
        private String name;

        public CreateClientRequest() {}

        public CreateClientRequest(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class UpdateClientRequest {
        private Integer id;
        private String name;

        public UpdateClientRequest() {}

        public UpdateClientRequest(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class SearchClientRequest {
        private String name;

        public SearchClientRequest() {}

        public SearchClientRequest(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}