/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.client.Entity;
import java.util.List;

/**
 *
 * @author kowal
 */
public class Main {

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();

        //Wyswietlanie wszystkich
        displayAll(getAll(client));
        //Wyswietlanie jednej otwartej
        Complaint complaint = getOpenComplaint(client, 7);
        if (complaint != null) {
            System.out.println("Open Complaint:\n");
            display(complaint);
        }
        //Modyfikacja skargi otwartej na zamkniętą
        if (complaint != null) {
            changeStatusToClosed(client, complaint);
        }
        //Wyswietlanie wszystkich otwartych
        displayAll(getAllOpened(client));

        client.close();
    }

    private static List<Complaint> getAll(Client client) {
        return client.target("http://localhost:8080/Complaints/"
                + "resources/complaints/")
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class)
                .readEntity(new GenericType<List<Complaint>>() {
                });
    }

    private static Complaint getOpenComplaint(Client client, int id) {
        try {
            Complaint complaint = client.target("http://localhost:8080/Complaints/"
                    + "resources/complaints/" + id)
                    .request(MediaType.APPLICATION_JSON)
                    .get(Complaint.class);
            if (complaint.getStatus().equals("open")) {
                return complaint;
            } else {
                System.out.println("This complaint is closed\n");
            }
        } catch (Exception e) {
            System.out.println("ID not found\n");
        }
        return null;
    }

    private static void changeStatusToClosed(Client client, Complaint complaint) {
        try {
            complaint.setStatus("closed");
            client.target("http://localhost:8080/Complaints/"
                    + "resources/complaints/" + complaint.getId().toString())
                    .request()
                    .put(Entity.entity(complaint, MediaType.APPLICATION_JSON));
            System.out.println("Status changed\n");
        } catch (Exception e) {
            System.out.println("Operation failed");
        }
    }

    private static List<Complaint> getAllOpened(Client client) {
        return client.target("http://localhost:8080/Complaints/"
                + "resources/complaints?status=open")
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class)
                .readEntity(new GenericType<List<Complaint>>() {
                });
    }

    private static void displayAll(List<Complaint> complaints) {
        System.out.println("COMPLAINTS: \n");
        for (Complaint complaint : complaints) {
            display(complaint);
        }
    }

    private static void display(Complaint complaint) {
        System.out.println(complaint.toString());
    }

}
