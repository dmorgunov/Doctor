package com.company;

public abstract class AbstractDoctor {
    abstract public String getName();
    abstract public void setName(String name);
    abstract public int getExp();
    abstract public void setExp(int exp);
    abstract public Appointment getAppointment(int i);
    abstract public void addAppointment(Appointment a);
    abstract public void clearAppointments();
    abstract public int appointmentsCount();
    abstract public boolean readFromFile(String fileName);
    abstract public boolean writeToFile(String fileName);
    abstract public void sortByVis();
    abstract public void sortByComLen();

    public int getMaxVis(){
        int max = 0;
        for (int i = 0; i < appointmentsCount(); i++)
            if (getAppointment(i).getVisitots()>max)
                max = getAppointment(i).getVisitots();
        return max;
    }

    public String getMaxComment(){
        String max = "";
        for (int i = 0; i < appointmentsCount(); i++)
            if (getAppointment(i).getComment().length()>max.length())
                max = getAppointment(i).getComment();
        return max;
    }
}
