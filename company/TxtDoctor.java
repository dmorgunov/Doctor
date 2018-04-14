package com.company;

import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class TxtDoctor extends AbstractDoctor{
    private String name;
    private int exp;
    private Appointment[] appointments = new Appointment[0];

    public TxtDoctor(String name, int exp) {
        this.name = name;
        this.exp = exp;
    }

    public TxtDoctor() {}

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getExp() {
        return exp;
    }

    @Override
    public void setExp(int exp) {
        this.exp = exp;
    }

    @Override
    public Appointment getAppointment(int i) {
        return appointments[i];
    }

    @Override
    public void addAppointment(Appointment appointment) {
        Appointment[] a = new Appointment[appointments.length+1];
        System.arraycopy(appointments,0,a,0,appointments.length);
        a[a.length-1] = appointment;
        appointments = a;
    }

    @Override
    public void clearAppointments() {
        Appointment[] a = new Appointment[0];
        appointments = a;
    }

    @Override
    public int appointmentsCount() {
        return appointments.length;
    }

    @Override
    public boolean readFromFile(String fileName) {
        try (Scanner scanner = new Scanner(new FileReader(fileName))) {
            setName(scanner.next());
            setExp(scanner.nextInt());
            while (scanner.hasNext()) {
                int day = scanner.nextInt();
                int vis = scanner.nextInt();
                String comments = scanner.nextLine().trim();
                addAppointment(new Appointment(day, vis, comments));
            }
        }
        catch (IOException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean writeToFile(String fileName) {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            out.println(getName() + " " + getExp());
            for (Appointment a : appointments) {
                out.print(a.getDay() + " " + a.getVisitots()+" ");
                out.println(a.getComment());
            }
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public void sortByVis() {
        Arrays.sort(appointments,(a,b)-> {return Integer.compare(a.getVisitots(),b.getVisitots());});
    }

    @Override
    public void sortByComLen() {
        Arrays.sort(appointments,(a,b)-> {return Integer.compare(a.getComment().length(),b.getComment().length());});
    }

    @Override
    public String toString() {
        return "TxtDoctor{" +
                "name='" + name + '\'' +
                ", exp=" + exp +
                ", appointments=" + Arrays.toString(appointments) +
                '}';
    }
}
