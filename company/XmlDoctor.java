package com.company;

import com.company.xml.DoctorData;
import com.company.xml.DoctorData.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XmlDoctor extends AbstractDoctor{
    private DoctorData cd;
    @Override
    public String getName() {
        return cd.getName();
    }
    @Override
    public void setName(String name) {
        cd.setName(name);
    }
    @Override
    public int getExp() {
        return cd.getExp();
    }
    @Override
    public void setExp(int exp) {
        cd.setExp(exp);
    }
    @Override
    public Appointment getAppointment(int i) {
        DoctorData.AppointmentData appointmentData = cd.getAppointmentData().get(i);
        return new Appointment(appointmentData.getDay(), appointmentData.getVisitor(), appointmentData.getComment());
    }
    @Override
    public void addAppointment(Appointment a) {
        AppointmentData appointmentData = new AppointmentData();
        appointmentData.setDay(a.getDay());
        appointmentData.setVisitor(a.getVisitots());
        appointmentData.setComment(a.getComment());
        cd.getAppointmentData().add(appointmentData);
    }
    @Override
    public void clearAppointments(){
        cd.getAppointmentData().clear();
    }
    @Override
    public int appointmentsCount(){
        return cd.getAppointmentData().size();
    }
    @Override
    public boolean readFromFile(String fileName) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance("com.company.xml");
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            cd = (DoctorData) unmarshaller.unmarshal(new FileInputStream(fileName));
            return true;
        }
        catch (FileNotFoundException | JAXBException e) {
            return false;
        }
    }
    @Override
    public boolean writeToFile(String fileName) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance("com.company.xml");
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(cd, new FileWriter(fileName));
            return true;
        }
        catch (JAXBException | IOException e) {
            return false;
        }
    }
    @Override
    public String toString() {
        String text = "";
        for(int i = 0; i < cd.getAppointmentData().size();i++)
            text += "\t"+getAppointment(i)+"\n";
        return cd.getName()+" "+cd.getExp()+"\n"+text;
    }
    @Override
    public void sortByVis() {
        Appointment[] arr = new Appointment[cd.getAppointmentData().size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = getAppointment(i);
        }
        Arrays.sort(arr, (a,b)->{return Integer.compare(a.getVisitots(),b.getVisitots());});
        cd.getAppointmentData().clear();
        for (int i = 0; i < arr.length; i++) {
            addAppointment(arr[i]);
        }
    }
    @Override
    public void sortByComLen(){
        Appointment[] arr = new Appointment[cd.getAppointmentData().size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = getAppointment(i);
        }
        Arrays.sort(arr, (a,b)->{return Integer.compare(a.getComment().length(),b.getComment().length());});
        cd.getAppointmentData().clear();
        for (int i = 0; i < arr.length; i++) {
            addAppointment(arr[i]);
        }
    }
}
